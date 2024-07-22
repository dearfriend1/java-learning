# 内容概述
## chapter1 java基础
包括类型、注解、类基础、
## chapter2 面向对象
包括数组、测试的使用、debug、面向对象
## chapter3 错误处理
## chapter4 IO流相关
### demo1 文件字符流
### demo2 文件字节流
### demo3 缓存流
### demo4 转换流
### Person 对象流
**对象流**

- 序列化:ObjectInputStream
- 反序列化:ObjectOutputStream
- 应用场景:不同进程之间通信、客户端(浏览器)与服务器之间的传输数据
- 操作:
		1. 对象类实现Serializable,表示对象可序列化
		2. 指定SerializeVersionUID表示:如果没有serialVersionUID,一旦改变类的任意东西,原始生成的序列化内容无法被反序列化
		3. 成员变量也都是需要保证是可序列化的
### test 所有测试程序+说明

