package org.zhuhaihong.IO;


import java.io.*;

/**
 * 缓冲流
 * 缓冲流是一种处理流,是对原始字节/字符流高效读写的一种方式
 *
 * private static final int DEFAULT_BUFFER_SIZE = 8192;
 * */
public class demo3BufferedIO {

    /**
     * 字符缓冲流读取*/
    public static String readFileByBuffer(String filename) throws IOException {
        //返回数据
        String str = null;

        //创建文件
        File file = new File(filename);

        //创建节点流
        FileReader fileReader = new FileReader(file);
        //创建处理流
        BufferedReader reader = new BufferedReader(fileReader);

        //读取数据
        String line;
        while ((line = reader.readLine())!=null){
            str+=line;
        }

        //关闭资源
        reader.close();
        fileReader.close();

        return str;
    }

    /**
     * 字节缓冲流复制文件
     * */
    public static void copyFileByBuffer(String sourceFileName,String targetFileName) throws IOException {

        double size = 0;
        long l = System.currentTimeMillis();

        //创建文件
        File src = new File(sourceFileName);
        File tag = new File(targetFileName);

        //创建流
        BufferedInputStream bufferedReader = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream bufferedWriter = new BufferedOutputStream(new FileOutputStream(tag));

        //复制

        byte[] buf  = new byte[1024];
        int len;
        while ((len = bufferedReader.read(buf))!= -1){
            size+=len;
            bufferedWriter.write(buf,0,len);
        }

        bufferedWriter.close();
        bufferedReader.close();

        long l1 = System.currentTimeMillis();

        double KbSize = size/1024/1024/1024;
        double comsumeTime = (l1-l)/1000.0;

        System.out.printf("复制完成.\t文件大小:%.4fGb\t消耗时间:%.2fs",KbSize,comsumeTime);

    }


}
