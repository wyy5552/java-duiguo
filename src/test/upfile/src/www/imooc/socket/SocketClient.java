package test.upfile.src.www.imooc.socket;

import test.upfile.src.www.imooc.entity.User;
import test.upfile.src.www.imooc.entity.File;
import test.upfile.src.www.imooc.util.CommandTransfer;

import javax.xml.transform.Transformer;
import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by iShu on 16/5/23.
 */
public class SocketClient {

    private Scanner scanner = new Scanner(System.in);
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    /**
     * 关闭连接
     */
    public void close() {
        try {
            if (socket != null)
                socket.close();
            if (oos != null)
                oos.close();
            if (ois != null)
                ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送数据
     * @param transfer
     */
    public void sendData(CommandTransfer transfer) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            oos = new ObjectOutputStream(outputStream);
            oos.writeObject(transfer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收数据
     * @return
     */
    public CommandTransfer getData(){
        CommandTransfer transfer = null;
        try {
            InputStream inputStream = socket.getInputStream();
            ois = new ObjectInputStream(inputStream);
            transfer = (CommandTransfer) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return transfer;
    }

    /**
     * 显示主菜单
     */
    public void showMainMenu() {
        System.out.println("***欢迎使用文件上传器***");
        System.out.println("---登录请按1---");
        System.out.println("---注册请按2---");
        System.out.println("---退出请按3---");
        System.out.println("***********************");

        int choice;
        while (true) {
            try {
                choice = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("请输入正整数");
                scanner.next();
                continue;
            }
        }
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("输入错误");
                System.exit(1);
        }
    }

    /**
     * 用户登录登录
     */
    public void login() {
        User user = new User();
        CommandTransfer transfer = new CommandTransfer();
        System.out.println("欢迎登陆, 你可以进行三次登录尝试");
        int count = 3;
        while (count-- > 0) {
            System.out.println("请输入用户名:");
            user.setName(scanner.next());
            System.out.println("请输入密码:");
            user.setPassword(scanner.next());
            transfer.setCommand("login");
            transfer.setUserData(user);

            try {
                socket = new Socket("localhost", 1346);
                sendData(transfer);
                transfer = getData();
                System.out.println(transfer.getResult());
                if (transfer.getFlag()) {
                    loginedMenu(transfer);
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }
        System.out.println("登录失败");
    }

    /**
     * 用户注册
     */
    public void register() {
        User user = new User();
        CommandTransfer transfer = new CommandTransfer();
        System.out.println("欢迎注册");
        while (true) {
            System.out.println("请输入用户名:");
            user.setName(scanner.next());
            System.out.println("请输入密码:");
            user.setPassword(scanner.next());
            System.out.println("请再次输入密码");
            String password = scanner.next();
            if (!password.equals(user.getPassword())) {
                System.out.println("两次输入的密码不一样,请重新输入");
                continue;
            }

            transfer.setCommand("register");
            transfer.setUserData(user);

            try {
                // 将数据传给服务器并接收服务器的响应
                socket = new Socket("localhost", 1346);
                sendData(transfer);
                transfer = getData();
                System.out.println("" + transfer.getResult());
                if (transfer.getFlag())
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }
        System.out.println("注册成功,请登录");
        login();
    }

    /**
     * 登录后菜单,可选择上传文件,查看文件或退出
     */
    public void loginedMenu(CommandTransfer transfer) {
        User user = (User) transfer.getUserData();
        String userName = user.getName();
        System.out.println("---欢迎" + userName + "---");
        System.out.println("***查询文件请按1***");
        System.out.println("***上传文件请按2***");
        System.out.println("***退出登录请按3***");

        int choice;
        while (true) {
            System.out.println("请选择:");
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("请输入以上正整数");
                scanner.next();
                continue;
            }
            switch (choice) {
                case 1:
                    queryFile(transfer);
                    break;
                case 2:
                    uploadFile(transfer);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入有误,请重新输入");
            }
        }

    }

    /**
     * 登录后用户查询文件查询文件
     * @param transfer
     */
    public void queryFile(CommandTransfer transfer) {
        transfer.setCommand("query");
        try {
            socket = new Socket("localhost", 1346);
            sendData(transfer);
            transfer = getData();
            System.out.println("" + transfer.getResult());
            ArrayList<File> fileArrayList = transfer.getFileArrayList();
            if (fileArrayList.size() != 0) {
                for (File file : fileArrayList) {
                    System.out.println(file.getId() + ", " + file.getName());
                }
            } else {
                System.out.println("您还没有上传文件");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }


    /**
     * 登陆后用户上传文件
     */
    public void uploadFile(CommandTransfer transfer) {
        System.out.println("请输入文件的绝对路径");
        String path;
        path = scanner.next();
        String filename = path.substring(path.lastIndexOf('/') + 1);

        File file = null;
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;

        try {
            // 读取文件
            fileInputStream = new FileInputStream(path);
            byte[] fileContent = new byte[fileInputStream.available()];
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedInputStream.read(fileContent);
            file = new File(filename, fileContent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedInputStream != null)
                    bufferedInputStream.close();
                if (fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        transfer.setCommand("upload");
        transfer.setFileData(file);

        try {
            // 将数据传递给服务器
            socket = new Socket("localhost", 1346);
            sendData(transfer);
            transfer = getData();
            System.out.println(transfer.getResult());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

}
