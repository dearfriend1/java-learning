package org.zhuhaihong.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 *
 * IO流相关操作
 * =================================《文件字节流》==================================
 * >字节流主要用来读写非文本文件(同样也可以读写文本文件)
 * >文件的读取/写入都是以字节为标准的,不存在编码/解码操作,因此不存在乱码问题
 * >字节流主要作用是直接复制文件,其返回的byte不具备可读性,所以一般不对byte[]处理
 *
 * @date:2022/7/19 <p>常用的流可分为:字节流(输入输出)、字符流(输入输出)</p>
 * <p>文本文件主要时字符为单位的,所以一般使用字符流(Reader/Writer)</p>
 * <p>基本接口:InputStream-OutputStream、Reader-Writer</p>
 * <p>文件流实现类:FileInputStream-FileOutputStream、FileReader-FileWriter</p>
 * <p>
 * 基本操作:
 * 1.创建文件对象
 * 2.穿件文件的流
 * 3.流的处理(调用流的方法)
 * 4.关闭资源
 */

public class FileByteStream {

    /**
     * 以字节的形式读取文件(测试版,没有循环读取)
     *
     * @param fileName 文件
     * @return 字节数组
     */
    public static byte[] fileInputStreamRead(String fileName) {
        FileInputStream fileInputStream = null;
        try {
            File file = new File(fileName);

            //创建流
            fileInputStream = new FileInputStream(file);

            //读取字节流
            byte[] bytes;
            byte[] buf = new byte[1024];
            fileInputStream.read(buf);
            return buf;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.getStackTrace();
            }
        }


    }


    public static void copyFileByByteStream(String sourceFile,String targetFile){
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            File src = new File(sourceFile);
            File tag = new File(targetFile);

            fileInputStream = new FileInputStream(src);
            fileOutputStream = new FileOutputStream(tag);

            byte[] buf = new byte[1024];
            int len;

            while ((len = fileInputStream.read(buf))!=-1){
                fileOutputStream.write(buf,0,len);
            }

        }catch (IOException e){
            e.getStackTrace();
        }finally {
            try {
                if (fileInputStream!=null) {
                    fileInputStream.close();
                }
            }catch (IOException e){
                e.getStackTrace();
            }

            try {
                if (fileOutputStream!=null){
                    fileOutputStream.close();
                }
            }catch (IOException e){
                e.getStackTrace();
            }
        }
    }


}
