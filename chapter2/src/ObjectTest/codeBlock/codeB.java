package ObjectTest.codeBlock;

public class codeB {
    int ID;

    String Name;

    static boolean tag;
    //代码块初始化属性
    {
        ID = 0;
        Name = "zhuhaihong";
        tag = false;
    }

    static {
        tag = true;
    }

}
