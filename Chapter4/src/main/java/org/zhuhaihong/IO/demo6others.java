package org.zhuhaihong.IO;

import lombok.extern.java.Log;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class demo6others {
    /**
     * 改变标准输出位置,编写一个日志
     * */
    public static void Log(String file) throws IOException {
        //创建文件
        File file1 = new File(file);

        //创建打印流
        PrintStream printStream = new PrintStream(file1, StandardCharsets.UTF_8);
        //改变输出位置
        System.setOut(printStream);

        System.out.println("111111");
        System.out.println("211111");
        System.out.println("411111");

    }

}
