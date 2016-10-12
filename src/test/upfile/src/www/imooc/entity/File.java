package test.upfile.src.www.imooc.entity;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by iShu on 16/5/23.
 */
public class File implements Serializable{
    private String id;
    private String name;
    private byte[] content;

    public File() {}

    public File(String name, byte[] content) {
        this.name = name;
        this.content = content;
    }

    public File(String id, String name, byte[] content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "File{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
