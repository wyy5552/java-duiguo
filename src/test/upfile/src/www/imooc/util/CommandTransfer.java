package test.upfile.src.www.imooc.util;

import test.upfile.src.www.imooc.entity.File;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by iShu on 16/5/23.
 */
public class CommandTransfer implements Serializable {
    private String command;
    private Object userData = null;
    private Object fileData = null;
    private boolean flag;
    private String result;
    private ArrayList<File> fileArrayList = null;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }


    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getUserData() {
        return userData;
    }

    public void setUserData(Object userData) {
        this.userData = userData;
    }

    public Object getFileData() {
        return fileData;
    }

    public void setFileData(Object fileData) {
        this.fileData = fileData;
    }

    public ArrayList<File> getFileArrayList() {
        return fileArrayList;
    }

    public void setFileArrayList(ArrayList<File> fileArrayList) {
        this.fileArrayList = fileArrayList;
    }


    @Override
    public String toString() {
        return "CommandTransfer{" +
                "command='" + command + '\'' +
                ", userData=" + userData +
                ", fileData=" + fileData +
                ", flag=" + flag +
                ", result='" + result + '\'' +
                ", fileArrayList=" + fileArrayList +
                '}';
    }
}
