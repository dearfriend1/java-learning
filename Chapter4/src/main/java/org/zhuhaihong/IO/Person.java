package org.zhuhaihong.IO;

import lombok.Data;

import java.io.*;

@Data
public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;

    String name;
    int age;
    String description;

    /**
     * 对象流写入(序列化)
     */
    public void writeObjectOutputStream(String file) throws IOException {
        File file1 = new File(file);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file1));

        objectOutputStream.writeObject(this);
        objectOutputStream.flush();
        objectOutputStream.close();
    }


    /**
     * 对象流读取(反序列化)
     */
    public static Person readObjectInputStream(String file) throws IOException, ClassNotFoundException {
        File file1 = new File(file);

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file1));

        Person person =(Person) objectInputStream.readObject();

        objectInputStream.close();

        return person;

    }








}
