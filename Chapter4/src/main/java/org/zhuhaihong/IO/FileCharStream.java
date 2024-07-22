package org.zhuhaihong.IO;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * IO流相关操作
 * =================================《文件字符流》==================================
 *
 * @date:2022/7/19 <p>常用的流可分为:字节流(输入输出)、字符流(输入输出)</p>
 * <p>文本文件主要时字符为单位的,所以一般使用字符流(Reader/Writer)</p>
 * <p>基本接口:InputStream-OutputStream、Reader-Writer</p>
 * <p>文件流实现类:FileInputStream-FileOutputStream、FileReader-FileWriter</p>
 * 基本操作:
 * 1.创建文件对象
 * 2.穿件文件的流
 * 3.流的处理(调用流的方法)
 * 4.关闭资源
 * <p>
 * todo:try-catch-finally作用域;并列try-catch执行情况(不管有没有错都会执行?)
 */
public class FileCharStream {

    /**
     * 读取文本文件中的文本内容
     *
     * @param fileName 文本文件
     * @return String 文本内容
     */
    public static String fileReaderByCharStream(String fileName) {
        FileReader fileReader = null;    //为了后面关闭fileReader对代码块可见
        try {
            StringBuilder str = new StringBuilder();
            //创建文件对象
            File file = new File(fileName);  //可能异常

            //创建文件输入流
            fileReader = new FileReader(file);      //可能异常

            //处理流
            char[] buf = new char[3];
            int len;
            while ((len = fileReader.read(buf)) != -1) {
                for (int i = 0; i < len; i++) {
                    str.append(buf[i]);
                }
            }
            return str.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();   //关闭可能失败
                }
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }


    /**
     * 通过文件字符流写文件:FileWriter
     *
     * @param content  待写入内容
     * @param fileName 写入文件名
     */
    public static void fileWriterByCharStream(String content, String fileName) {
        FileWriter fileWriter = null;

        try {
            //创建文件对象
            File file = new File(fileName);

            //创建流
            fileWriter = new FileWriter(file);

            //控制写入
            fileWriter.write(content);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }

    /**
     * 复制文本文件(文件字符流FileReader+FileWriter)
     *
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     */
    public static void fileCopyByCharStream(String sourceFile, String targetFile) {
        FileWriter fileWriter = null;
        FileReader fileReader = null;
        try {
            //创建文件
            File src = new File(sourceFile);
            File tag = new File(targetFile);

            //创建字符流
            fileReader = new FileReader(src);
            fileWriter = new FileWriter(tag);

            //复制文字
            char[] buf = new char[10];
            int len;
            while ((len = fileReader.read(buf)) != -1) {
                fileWriter.write(buf, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {    //关闭资源
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.getStackTrace();
            }

            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.getStackTrace();
            }
        }

    }

}
