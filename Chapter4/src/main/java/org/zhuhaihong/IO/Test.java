package org.zhuhaihong.IO;

import java.io.IOException;
import java.util.Arrays;

/**
 * <h1>IO流相关操作</h1>
 *
 * @date:2022/7/19
 * @author dearfriend
 *
 *
 * <h2>分类</h2>
 * <ul>
 *     <li>常用的流可分为:字节流(输入输出)、字符流(输入输出)</li>
 *     <li>文本文件主要时字符为单位的,所以一般使用字符流(Reader/Writer)</li>
 *     <li>基本接口:InputStream-OutputStream、Reader-Writer</li>
 * </ul>
 *
 * <h2>文件流实现类</h2>
 * <li>FileInputStream-FileOutputStream、FileReader-FileWriter</li>
 *
 * <h2>缓冲流</h2>
 * <ul>
 *     <li>字节流-BufferedInputStream/BufferedOutputStream</li>
 *     <li>字符流-BufferedReader/BufferedWriter</li>
 *     <li>作用-减少与磁盘的交互次数实现高效读写数据操作</li>
 * </ul>
 *
 * <h2>转换流</h2>
 * <ul>
 *     <li>InputStreamReader-解码</li>
 *     <li>OutputStreamWriter-编码</li>
 *     <li>掌握字符编码解码过程</li>
 * </ul>
 *
 * >转换流实际就是利用字节流为基础,对字节流<->字符流进行编码/解码<p>
 * >文件字符流其实也可以进行编/解码方式的指定<p>
 * >相当于以字节流为节点流(基础数据流),对字节流进行处理<p>
 *
 *
 * <h2>对象流</h2>
 * <ul>
 *     <li>序列化:ObjectInputStream</li>
 *     <li>反序列化:ObjectOutputStream</li>
 *     <li>应用场景:不同进程之间通信、客户端(浏览器)与服务器之间的传输数据</li>
 * </ul>
 *
 * <strong>操作:</strong>
 * <ol>
 *     <li>对象类实现Serializable,表示对象可序列化</li>
 *     <li>指定SerializeVersionUID表示:如果没有serialVersionUID,一旦改变类的任意东西,原始生成的序列化内容无法被反序列化</li>
 *     <li>成员变量也都是需要保证是可序列化的</li>

 * </ol>
 *
 *
 * <h2>标准输入/出流</h2>
 * <ul>
 *     <li>System.in标准输入流:默认从键盘输入</li>
 *     <li>System.out标准输入流:默认从控制台</li>
 * </ul>
 * <strong>注:可以通过setIn/Out修改输入/出流的文职</strong>
 */
public class Test {
    final static String FIL_PATH = "fileResource/";

    //编码
    final static String UTF_8 = "UTF-8";
    final static String GBK = "GBK";
//
//    final static  String UTF_8 = "UTF-8";
//    final static  String UTF_8 = "UTF-8";
//    final static  String UTF_8 = "UTF-8";




    /*=======================================文件字符流=======================================*/

    /**
     * 文件输入流(字符)——实际继承了字节流
     */
    @org.junit.Test
    public void testReader() {
        String s = demo1FileCharStream.fileReaderByCharStream("fileResource/io.txt");
        System.out.println(s);
    }


    /**
     * 文件输出流(字符)
     * 此处使用的时write(str),自动在后面追加
     */
    @org.junit.Test
    public void testFileWriter() {
        demo1FileCharStream.fileWriterByCharStream("testFileWriteByCharStream", "fileResource/fileWriterCharStream.txt");
    }


    /**
     * 文本文件的复制
     * 文本文件主要时字符为单位的,所以一般使用字符流(Reader/Writer)
     */
    @org.junit.Test
    public void testFileCopy() {
        demo1FileCharStream.fileCopyByCharStream("fileResource/io.txt", "fileResource/io_copy.txt");
    }


    /*=======================================文件字节流=======================================*/

    /**
     * 文件字节输入流:FileInputStream
     */
    @org.junit.Test
    public void testFileInputStream() {
        byte[] bytes = demo2FileByteStream.fileInputStreamRead("fileResource/pic.png");
        System.out.println(Arrays.toString(bytes));
    }

    /**
     * 文件字节输出流:FileOutputStream
     * */


    /**
     * 非文本文件的字节流复制(不会乱码,不用管格式读取写入都是字节,不存在兼容)(主要功能)
     */
    @org.junit.Test
    public void testCopyFileByByteStream() {
        demo2FileByteStream.copyFileByByteStream(FIL_PATH + "pic.png", FIL_PATH + "pic_copy.png");
    }


    /*=======================================缓冲字符流=======================================*/

    /**
     * 字符缓冲流读取
     */
    @org.junit.Test
    public void testreadFIleByBugger() throws IOException {
        System.out.println(
                demo3BufferedIO.readFileByBuffer(FIL_PATH + "io.txt")
        );
    }


    /**
     * 大文件复制
     */
    @org.junit.Test
    public void testCopyFileByBuffered() throws IOException {
        demo3BufferedIO.copyFileByBuffer(FIL_PATH + "dzd.mkv", FIL_PATH + "dzd_copy.mkv");
    }

    /**
     * 性能对比
     * 读取buf都设置为1024
     */
    @org.junit.Test
    public void testCompare() throws IOException {
        demo3BufferedIO.copyFileByBuffer(FIL_PATH + "dzd.mkv", FIL_PATH + "dzd_copy1.mkv");

        long l = System.currentTimeMillis();
        demo2FileByteStream.copyFileByByteStream(FIL_PATH + "dzd.mkv", FIL_PATH + "dzd_copy2.mkv");
        long l1 = System.currentTimeMillis();

        double comsumeTime = (l1 - l) / 1000.0;
        System.out.printf("\n复制完成.\t消耗时间:%.2fs", comsumeTime);
    }

    /*========================================转换流========================================*/

    /**
     * 指定编码类型读取文件
     */
    @org.junit.Test
    public void testReadFileByTransformStreamUTF8() throws IOException {
        String s = demo4TransformStream.readFileByTransformReader(FIL_PATH + "encoding.txt", UTF_8);
        System.out.println(s);
    }


    @org.junit.Test
    public void testReadFileByTransformStreamGBK() throws IOException {
        String s = demo4TransformStream.readFileByTransformReader(FIL_PATH + "encoding.txt", GBK);
        System.out.println(s);
    }
//    Buffered璇诲啓鏇村揩鐨勫師鍥犱富瑕佸湪浜庡噺灏戜簡鐩存帴鐨処O鎿嶄綔娆℃暟銆傗��
//    棣栧厛锛屸�屾棤璁烘槸Linux绯荤粺鐨凱age Cache鏈哄埗杩樻槸Java涓殑BufferedReader鍜孊ufferedWriter锛屸�屽畠浠兘鍒╃敤浜嗙紦瀛樼殑鍘熺悊鏉ュ噺灏戠鐩業O鐨勮闂鏁帮紝鈥屼粠鑰屾彁楂樿鍐欓�熷害銆傗�屽叿浣撴潵璇达細鈥�
//    Linux绯荤粺鐨凱age Cache鏈哄埗锛氣�岀敱浜庣鐩業O閫熷害杩滀綆浜庡唴瀛橀�熷害锛屸�孡inux绯荤粺閫氳繃瀹炵幇Page Cache鏈哄埗锛屸�屽嵆鍒╃敤鍐呭瓨鏉ョ紦瀛樼鐩樻暟鎹紝鈥屽綋鍐呭瓨鍏呰鏃讹紝鈥屽敖鍙兘鍒╃敤鍐呭瓨鏉ュ噺灏戠鐩業O鐨勮闂鏁帮紝鈥屼互姝ゆ潵鎻愬崌绯荤粺鐨勬暣浣撴�ц兘銆傗�屽綋鍐呮牳鍙戣捣涓�涓璇锋眰鏃讹紝鈥岄鍏堟鏌ユ暟鎹槸鍚﹀瓨鍦ㄤ簬Page Cache涓紝鈥屽鏋滅紦瀛樺懡涓紝鈥屽垯鐩存帴杩斿洖鏁版嵁锛屸�屾棤闇�璁块棶纾佺洏锛涒�屽鏋滄槸鍐欒姹傦紝鈥屽垯鏄厛灏嗘暟鎹啓鍏age Cache锛屸�岀劧鍚庡懆鏈熸�у湴鎴栨牴鎹渶瑕佸皢鍏跺啓鍥炲埌纾佺洏銆傗��
//    Java涓殑BufferedReader鍜孊ufferedWriter锛氣�屽畠浠�氳繃鍦ㄥ唴瀛樹腑璁剧疆涓�涓紦鍐插尯锛屸�屽厛浠嶪O璁惧璇诲彇鏁版嵁鍒扮紦鍐插尯锛屸�岀劧鍚庝粠缂撳啿鍖轰腑璇诲彇鎴栧啓鍏ユ暟鎹紝鈥岃繖鏍峰氨鍙渶瑕佽闂竴娆O璁惧灏卞彲浠ュ畬鎴愭暟鎹殑璇诲啓鎿嶄綔锛屸�屾瀬澶у湴鎻愰珮浜嗚鍐欐晥鐜囥�傗�孊ufferedReader鍜孊ufferedWriter鐨勭紦鍐插尯澶у皬閫氬父涓�8KB锛屸�岀浉姣擲canner绛夌洿鎺ヨ鍐欑被锛屸�屽畠浠兘澶熷噺灏戞洿澶氱殑IO鎿嶄綔娆℃暟锛屸�屼粠鑰屾彁楂樿鍐欓�熷害銆傗��
//    缁间笂鎵�杩帮紝鈥屾棤璁烘槸鎿嶄綔绯荤粺灞傞潰鐨凱age Cache鏈哄埗杩樻槸搴旂敤灞傞潰鐨凚ufferedReader鍜孊ufferedWriter锛屸�屽畠浠兘閫氳繃鍑忓皯鐩存帴鐨処O鎿嶄綔娆℃暟鏉ユ彁楂樿鍐欓�熷害銆傗�岃繖鏄洜涓虹洿鎺ョ殑IO鎿嶄綔閫氬父闇�瑕佽緝闀挎椂闂达紝鈥岃�岄�氳繃缂撳瓨鏈哄埗鍙互鏈夋晥鍦板噺灏戣繖绉嶇洿鎺ユ搷浣滐紝鈥屼粠鑰屾彁楂樻晥鐜�


    /**
     * 文本内容编码转换
     */
    @org.junit.Test
    public void testFileTransformCode() throws Exception {
        demo4TransformStream.transFormCode(FIL_PATH + "encoding.txt", UTF_8,
                FIL_PATH + "GBK.txt", GBK);
    }


    /**========================================对象流========================================*/
    /**
     * 序列化过程*/
    @org.junit.Test
    public void  testObjectWrite() throws IOException {
        Person person = new Person();
        person.setName("Helen");
        person.setAge(34);
        person.setDescription("object stream test-program");

        person.writeObjectOutputStream(FIL_PATH+"object.json");
    }


    /**反序列化过程
     * 从文件中读取对象,返回该对象
    * */
    @org.junit.Test
    public void  testObjectRead() throws IOException, ClassNotFoundException {
        Person person = Person.readObjectInputStream(FIL_PATH + "object.json");

        System.out.println(person);

    }

    /**========================================输入输出流========================================*/
    @org.junit.Test
    public void  testPrint() throws IOException {
        demo6others.Log(FIL_PATH+"log.txt");

    }



}
