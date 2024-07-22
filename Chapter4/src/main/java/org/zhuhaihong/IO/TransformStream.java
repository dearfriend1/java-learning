package org.zhuhaihong.IO;


import java.io.*;
import java.util.MissingResourceException;

/**
 * 转换流
 * >编码A文件->字节流->解码A读取流(字符流)
 * >字符流->编码码B(字节流)->编码B文件
 * *文件的编码解码必须相同,否则出现乱码
 *
 * <h2><a id="standard">Standard charsets</a></h2>
 *
 *
 *  <p> Every implementation of the Java platform is required to support the
 *   following standard charsets.  Consult the release documentation for your
 *   implementation to see if any other charsets are supported.  The behavior
 *   of such optional charsets may differ between implementations.
 *
 *   <blockquote><table class="striped" style="width:80%">
 *   <caption style="display:none">Description of standard charsets</caption>
 *   <thead>
 *   <tr><th scope="col" style="text-align:left">Charset</th><th scope="col" style="text-align:left">Description</th></tr>
 *   </thead>
 *   <tbody>
 *   <tr><th scope="row" style="vertical-align:top">{@code US-ASCII}</th>
 *       <td>Seven-bit ASCII, a.k.a. {@code ISO646-US},
 *           a.k.a. the Basic Latin block of the Unicode character set</td></tr>
 *   <tr><th scope="row" style="vertical-align:top"><code>ISO-8859-1&nbsp;&nbsp;</code></th>
 *       <td>ISO Latin Alphabet No. 1, a.k.a. {@code ISO-LATIN-1}</td></tr>
 *   <tr><th scope="row" style="vertical-align:top">{@code UTF-8}</th>
 *       <td>Eight-bit UCS Transformation Format</td></tr>
 *   <tr><th scope="row" style="vertical-align:top">{@code UTF-16BE}</th>
 *       <td>Sixteen-bit UCS Transformation Format,
 *           big-endian byte&nbsp;order</td></tr>
 *   <tr><th scope="row" style="vertical-align:top">{@code UTF-16LE}</th>
 *       <td>Sixteen-bit UCS Transformation Format,
 *           little-endian byte&nbsp;order</td></tr>
 *   <tr><th scope="row" style="vertical-align:top">{@code UTF-16}</th>
 *       <td>Sixteen-bit UCS Transformation Format,
 *           byte&nbsp;order identified by an optional byte-order mark</td></tr>
 *   </tbody>
 *   </table></blockquote>
 * */
public class TransformStream {

    /**指定编码方式读取文件
     * */
    public static String readFileByTransformReader(String file,String decode) throws IOException {
        //字符串构建器
        StringBuilder str = new StringBuilder();

        File file1 = new File(file);

        InputStreamReader inputStreamReader;
        if (decode!=null) inputStreamReader = new InputStreamReader(new FileInputStream(file),decode);
        inputStreamReader = new InputStreamReader(new FileInputStream(file));

        int len;
        char[] buf = new char[10];
        while ((len = inputStreamReader.read(buf))!=-1){
            for (int i = 0;i<len;i++){
                str.append(buf[i]);
            }
        }
        inputStreamReader.close();
        return str.toString();
    }


    /**
     * 编码转化
     * */
    public static void transFormCode(String sourceFile,
                                     String sourceEncode,
                                     String targetFile,
                                     String targetEncode) throws Exception {

        //创建文件
        File src = new File(sourceFile);
        File tar = new File(targetFile);

        //创建流
        FileInputStream srcInputStream = new FileInputStream(src);
        FileOutputStream tagOutputStream = new FileOutputStream(targetFile);

        //转换流
        InputStreamReader inputStreamReader = new InputStreamReader(srcInputStream,sourceEncode);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(tagOutputStream, targetEncode);

        //内容转换
        char[] buf = new char[100];
        int len = 0;
        while ((len = inputStreamReader.read(buf))!=-1){
            outputStreamWriter.write(buf,0,len);
        }

        outputStreamWriter.close();
        inputStreamReader.close();
    }
}
