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
     * 鍏抽棴杩炴帴
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
     * 鍙戦�佹暟鎹�
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
     * 鎺ユ敹鏁版嵁
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
     * 鏄剧ず涓昏彍鍗�
     */
    public void showMainMenu() {
        System.out.println("***娆㈣繋浣跨敤鏂囦欢涓婁紶鍣�***");
        System.out.println("---鐧诲綍璇锋寜1---");
        System.out.println("---娉ㄥ唽璇锋寜2---");
        System.out.println("---閫�鍑鸿鎸�3---");
        System.out.println("***********************");

        int choice;
        while (true) {
            try {
                choice = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("璇疯緭鍏ユ鏁存暟");
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
                System.out.println("杈撳叆閿欒");
                System.exit(1);
        }
    }

    /**
     * 鐢ㄦ埛鐧诲綍鐧诲綍
     */
    public void login() {
        User user = new User();
        CommandTransfer transfer = new CommandTransfer();
        System.out.println("娆㈣繋鐧婚檰, 浣犲彲浠ヨ繘琛屼笁娆＄櫥褰曞皾璇�");
        int count = 3;
        while (count-- > 0) {
            System.out.println("璇疯緭鍏ョ敤鎴峰悕:");
            user.setName(scanner.next());
            System.out.println("璇疯緭鍏ュ瘑鐮�:");
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
        System.out.println("鐧诲綍澶辫触");
    }

    /**
     * 鐢ㄦ埛娉ㄥ唽
     */
    public void register() {
        User user = new User();
        CommandTransfer transfer = new CommandTransfer();
        System.out.println("娆㈣繋娉ㄥ唽");
        while (true) {
            System.out.println("璇疯緭鍏ョ敤鎴峰悕:");
            user.setName(scanner.next());
            System.out.println("璇疯緭鍏ュ瘑鐮�:");
            user.setPassword(scanner.next());
            System.out.println("璇峰啀娆¤緭鍏ュ瘑鐮�");
            String password = scanner.next();
            if (!password.equals(user.getPassword())) {
                System.out.println("涓ゆ杈撳叆鐨勫瘑鐮佷笉涓�鏍�,璇烽噸鏂拌緭鍏�");
                continue;
            }

            transfer.setCommand("register");
            transfer.setUserData(user);

            try {
                // 灏嗘暟鎹紶缁欐湇鍔″櫒骞舵帴鏀舵湇鍔″櫒鐨勫搷搴�
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
        System.out.println("娉ㄥ唽鎴愬姛,璇风櫥褰�");
        login();
    }

    /**
     * 鐧诲綍鍚庤彍鍗�,鍙�夋嫨涓婁紶鏂囦欢,鏌ョ湅鏂囦欢鎴栭��鍑�
     */
    public void loginedMenu(CommandTransfer transfer) {
        User user = (User) transfer.getUserData();
        String userName = user.getName();
        System.out.println("---娆㈣繋" + userName + "---");
        System.out.println("***鏌ヨ鏂囦欢璇锋寜1***");
        System.out.println("***涓婁紶鏂囦欢璇锋寜2***");
        System.out.println("***閫�鍑虹櫥褰曡鎸�3***");

        int choice;
        while (true) {
            System.out.println("璇烽�夋嫨:");
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("璇疯緭鍏ヤ互涓婃鏁存暟");
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
                    System.out.println("杈撳叆鏈夎,璇烽噸鏂拌緭鍏�");
            }
        }

    }

    /**
     * 鐧诲綍鍚庣敤鎴锋煡璇㈡枃浠舵煡璇㈡枃浠�
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
                System.out.println("鎮ㄨ繕娌℃湁涓婁紶鏂囦欢");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }


    /**
     * 鐧婚檰鍚庣敤鎴蜂笂浼犳枃浠�
     */
    public void uploadFile(CommandTransfer transfer) {
        System.out.println("璇疯緭鍏ユ枃浠剁殑缁濆璺緞");
        String path;
        path = scanner.next();
        String filename = path.substring(path.lastIndexOf('/') + 1);

        File file = null;
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;

        try {
            // 璇诲彇鏂囦欢
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
            // 灏嗘暟鎹紶閫掔粰鏈嶅姟鍣�
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
