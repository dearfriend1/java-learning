package demo1Comment;

/**
 * @author zhuhaihong
 * @version 1.0
 * This program is about comment,include:单行注释、多行注释、文档注释
 * 注释内容通过javadoc工具进行管理
 * 解析注释：javadoc -d mycomment -auther -version comment.java
 */

/*
//java中的注释方式
//单行注释
//多行注释（不能嵌套）
//以上不包含在编译文件内部
//文档注释（java特有）
*/

/**
 * 注释放在类和函数前面
 */
public class comment {
    /**
     * 该函数输出“文档注释”这几个字
     */
    public static void main(String args[]){

        System.out.println("文档注释");
    }


}
