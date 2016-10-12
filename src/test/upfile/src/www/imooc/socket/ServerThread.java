package test.upfile.src.www.imooc.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import test.upfile.src.www.imooc.entity.File;
import test.upfile.src.www.imooc.entity.User;
import test.upfile.src.www.imooc.service.FileService;
import test.upfile.src.www.imooc.service.UserService;
import test.upfile.src.www.imooc.util.CommandTransfer;

/**
 * Created by iShu on 16/5/23.
 */
public class ServerThread extends Thread {
    private Socket socket = null;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    private FileService fileService = new FileService();
    private UserService userService = new UserService();

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            CommandTransfer transfer = (CommandTransfer)objectInputStream.readObject();
            transfer = execute(transfer);
            objectOutputStream.writeObject(transfer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public CommandTransfer execute(CommandTransfer transfer) {
        String commmand = transfer.getCommand();
        File file;
        User user;
        if (commmand.equals("login")) {
            user = (User)transfer.getUserData();
            boolean flag = userService.login(user);
            if (flag) {
                transfer.setFlag(true);
                transfer.setResult("Login success");
            } else {
                transfer.setFlag(false);
                transfer.setResult("Login failed");
            }
        } else if (commmand.equals("register")) {
            user = (User)transfer.getUserData();
            boolean flag = userService.register(user);
            if (flag) {
                transfer.setFlag(true);
                transfer.setResult("Register success");
            } else {
                transfer.setFlag(false);
                transfer.setResult("Register failed");
            }
        } else if (commmand.equals("upload")) {
            file = (File)transfer.getFileData();
            user = (User)transfer.getUserData();
            boolean flag = fileService.save(file, user);
            if (flag) {
                transfer.setFlag(true);
                transfer.setResult("Upload file success");
            } else {
                transfer.setFlag(false);
                transfer.setResult("Upload file failed");
            }
        } else if (commmand.equals("query")) {
            user = (User) transfer.getUserData();
            ResultSet resultSet = fileService.query(user);
            ArrayList<File> fileArrayList;
            if (resultSet != null) {
                transfer.setFlag(true);
                fileArrayList = new ArrayList<>();
                try {
                    file = new File();
                    while (resultSet.next()) {
                        String fid = resultSet.getString("fid");
                        String fname = resultSet.getString("fname");
                        file.setId(fid);
                        file.setName(fname);
                        fileArrayList.add(file);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                transfer.setFileArrayList(fileArrayList);
                transfer.setResult("Query success");
            } else {
                transfer.setFlag(false);
                transfer.setFileArrayList(null);
                transfer.setResult("Query failed");
            }
        }
        return  transfer;
    }
}
