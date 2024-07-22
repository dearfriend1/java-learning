# ERDF慧都快速搭建框架

## 备注

测试页面:[测试页面](http://127.0.0.1:8110/doc.html#/home)

网关测试页面:[http://127.0.0.1:8100/doc.html#/home](http://127.0.0.1:8100/doc.html#/home)

nacos中需要配置命名空间,只有命名空间相同的微服务才能同时别查看到

在服务器上部署写localhost还是本机地址(本机地址可)

部署到docker后需要重启



## 环境

- JDK1.8
- Maven3.6.0
- Mysql
- Redis 7.2.5+Another Redis Dedktop Manager1.6.6
- Gateway3.1.0
- nacos

## 使用流程

### 1.启动mysql

- docker 

### 1.启动redis

```shell
redis-server      #启动服务
redis-cli ping    #测试安装是否成功
brew services stop redis   #关闭服务
brew services start redis  #开启服务
redis-cli -h localhost -p 6379 #指定端口

```

### 3.启动Nacos

```shell
cd /Users/dearfriend/huidu/nacos/bin
sh startup.sh -m standalone
```

进入可视化页面http://127.0.0.1:8848/nacos/ ，账号密码都是nacos，进行登录即可，nacos的端口为**8848**

- 需要创建命名空间

![nacos新建命名空间](/Users/dearfriend/Library/Application Support/typora-user-images/image-20240703150116861.png)

和sping中的namespace对应

![image-20240703150251557](/Users/dearfriend/Library/Application Support/typora-user-images/image-20240703150251557.png)

### 4.启动网关gateway

```shell
cd /Users/dearfriend/.m2/repository/com/evget/gateway/3.1.0
java -Dfile.encoding=utf-8 -jar -Xms64m -Xmx256m gateway-3.1.0.jar --spring.config.location=gateway.yaml
```

可视化页面:[http://127.0.0.1:8100/doc.html#/home](http://127.0.0.1:8100/doc.html#/home)

### 5.function-user启动

```shell
/Users/dearfriend/Library/Java/JavaVirtualMachines/corretto-1.8.0_412/Contents/Home/bin/java -Dfile.encoding=utf-8 -jar -Xms64m -Xmx256m function-user-exact-3.1.0.jar --spring.config.location=function-user.yaml
#指定java JDK进行启
```

### 6.启动spring程序



### 7.Docker部署

打包jar(jar包使用外部配置)

docker[部署配置文件](/Users/dearfriend/huidu/document_deploy)(环境)

```yaml
dubbo:
  cloud:
    subscribed-services: ""
  protocols:
    dubbo:
      id: dubbo
      name: dubbo
      port: -1
  provider:
    timeout: 60000
  consumer:
    check: false
    timeout: 60000
  registry:
    address: nacos://192.168.3.150:8848
    parameters[namespace]: promes_dev
    check: false
spring:
  application:
    name: function-document
  profiles:
    active: d
  redis:
    host: 192.168.3.150
    port: 6379
    password: Evegt@2022
    database: 5
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://192.168.3.150:3306/evget?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=CONVERT_TO_NULL&allowPublicKeyRetrieval=true
    username: root
    password: 55646478aas
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    time-zone: GMT+8
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  cache:
    type: redis
  cloud:
    nacos:
      discovery:
        server-addr: 192.168.3.150:8848
        namespace: promes_dev
logging:
  level:
    root: warn
    com.evget: debug
server:
  port: 8209
mybatis:
  configuration:
    map-underscore-to-camel-case: true
config:
  token:
    expire:
      time: 3600
  user:
    password: 123456
  device: 4f2612aa5943a25459e25720a51bc895
  signature: 551f0a4dfcfc5489f7818024f390449924c3eff3f1fcc1e460b680630931a6fbd14c792cae0c6a3643d62ca9bf0f390739e9533534fdb5baeac17b4195da063515b5d7501b07a90f21e44178e0ccff11
  data:
    attribute: false
    dictionary: false
    privilege: false
    
#minio
minio:
  endpoint: http://192.168.8.101:9000
  access-key: pIstT22wIsH6E79NrUgF
  secret-key: oJzrUbjYNINfmhGpym2DRXHygUetR4LQDSrhVgCA
  bucket-name: parentbucket

#文件上传大小限制
spring:
  servlet:
    multipart:
      max-file-size: 200MB
      max-request-size: 200MB
  application:
    name: function-document

```

[部署启动](/Users/dearfriend/huidu/document_deploy/server.sh)

![image-20240715094255423](/Users/dearfriend/Library/Application Support/typora-user-images/image-20240715094255423.png)

```sh
#启动
sh server.sh restart/start

```

修改start.sh配置(文件路径)



启动日志查看

```
docker logs -f palworld         
```

```sh
#!/bin/bash

#author:wting（bug fixed version by suhai）

#这里替换为程序位置  #注意：APP_NAME为jar文件名（而非路径），且"="后面不能有空格
#此处的APP_NAME为打包后jar包名
#注意：如果包名携带日期或其他会变化的内容，例如xxxx-1.0.0.20221110.release.jar，请修改此处的名称为xxxx这类固定且能标识服务的名称，同时替换start方法中的启动命令
APP_NAME=document_deploy
#添加jar包路径，方便在任何目录下都可以运行脚本启动服务
APP_PATH=/Users/dearfriend/huidu/document_deploy

#使用说明，用来提示输入参数
usage() {
    echo "Usage: sh x-pdf.sh [start|stop|restart|status]"
    exit 1
}

#检查程序是否在运行
is_exist(){
  pid=`docker images -q ${APP_NAME}:latest 2> /dev/null`
  #如果不存在返回1，存在返回0     
  if [ -z "${pid}" ]; then
   return 1
  else
    return 0
  fi
}

#启动方法
start(){
  is_exist
  if [ $? -eq 0 ]; then
    echo "${APP_NAME} is already running. pid=${pid}"
  else
    docker-compose -f ${APP_PATH}/docker-compose.yaml build --no-cache
	docker-compose -f ${APP_PATH}/docker-compose.yaml up -d
  fi
}

#停止方法
stop(){
  is_exist
  if [ $? -eq "0" ]; then
    docker stop ${APP_NAME} && docker rm ${APP_NAME} && docker rmi ${APP_NAME}
    echo "${APP_NAME} 已关闭！ pid=${pid}"
  else
    echo "${APP_NAME} is not running"
  fi
}

#输出运行状态
status(){
  is_exist
  if [ $? -eq "0" ]; then
    echo "${APP_NAME} is running. Pid is ${pid}"
  else
    echo "${APP_NAME} is not running."
  fi
}

#重启
restart(){
  stop
  echo "${APP_NAME} 准备重启..."
  sleep 5
  start
}



#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
  "start")
    start
    ;;
  "stop")
    stop
    ;;
  "status")
    status
    ;;
  "restart")
    restart
    ;;
  *)
    usage
    ;;
esac



```

docker 启动 mysql

```sh
docker exec -it mysql mysql -uroot -p123456
#效果等同于：
docker exec -it mysql /bin/bash
mysql -uroot -p123456

```

上传到服务器(配置相关文件)

## 相关配置

### minio部署

```sh
docker run \
   -p 9000:9000 \
   -p 9001:9001 \
   --name minio \
   -v ~/minio/data:/data \
   -e "MINIO_ROOT_USER=a”admin \
   -e "MINIO_ROOT_PASSWORD=admin123" \
   quay.io/minio/minio server /data --console-address ":9001"
```



### maven私服配置

- [setting.xml](/Users/dearfriend/huidu/apache-maven-3.6.0/conf/settings.xml)

```xml
<?xml version="1.0" encoding="UTF-8"?>

<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">

  <pluginGroups>
  </pluginGroups>


  <proxies>
  </proxies>

  <servers>
     <server>
           <id>evget-erdf-maven</id>
		 <username>maven-1711596612316</username>
		 <password>89d5e33a1a39d3203cb2e24b3d7ff48db10bb280</password>
       </server>
  </servers>

  <mirrors>
    <mirror>
        <id>evget-erdf-maven</id>
        <name>maven</name>
        <mirrorOf>*</mirrorOf>
        <url>https://evget-maven.pkg.coding.net/repository/erdf/maven/</url>
    </mirror>
  </mirrors>

  <profiles>
    <profile>
		<id>maven</id>
		<activation>
			<jdk>1.8</jdk>
			<activeByDefault>true</activeByDefault>
		</activation>
		<repositories>
			<repository>
				<id>evget-erdf-maven</id>
				<url>https://evget-maven.pkg.coding.net/repository/erdf/maven</url>
			</repository>

		</repositories>
	</profile>
  </profiles>


</settings>

```

文件根pom.xml配置

```xml
    <distributionManagement>
        <repository>
            <!--必须与 settings.xml 的 id 一致-->
            <id>evget-erdf-maven</id>
            <name>maven</name>
            <url>https://evget-maven.pkg.coding.net/repository/erdf/maven/</url>
        </repository>
    </distributionManagement>
```

配置好后才能下载依赖

### mysql

1. 在mysql创建一个数据库

```sql
create database mydatabase
```

2. spring中application.yaml中配置mysql

```yaml
spring:
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/evget
    username: root
    password: 55646478aas
```

3. 所需服务 

   依赖:function-user-abstract。2.2.0/2.0.0/3.1.0

   服务:function-user-exact 2.2.0/2.0.0/3.1.0

   function-user 2.2.0/3.1.0

   

### redis

主要是密码的配置,如果没有密码就不用写

```yaml
spring:
  redis:
    host: localhost
    port: 6379
    password:
    database: 0
```

   ### Nacos

1. 创建命名空间
2. 配置命名空间——使用的是nacos服务在dubbo中进行注册

```yaml
dubbo:
  registry:
    address: nacos://127.0.0.1:8848
    parameters[namespace]: erdf_d
    check: false

spring:
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
        namespace: erdf_d
```

### gateway

1.导入gateway的配置yaml文件**(上面mysql、nacos、redis更改了这里相关的配置也需要更改)**

```yaml
mybatis:
  configuration:
    map-underscore-to-camel-case: true
logging:
  level:
    root: warn
    com.evget: debug
server:
  port: 8100
spring:
  application:
    name: gateway
  cache:
    type: redis
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
        namespace: erdf_d
  datasource:                             
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/evget
    username: root
    password: 55646478aas
  redis:
    host: 127.0.0.1
    port: 6379
    password: 
    database: 0
```

- gateway相关操作

ssh命令存放于:[gateway.sh](/Users/dearfriend/huidu/gateway.sh)

使用方法:

```shell
cd xxx
sh gateway.sh xxname
```

```sh
#!/bin/bash

# 应用程序JAR文件的路径
APP_JAR="/Users/dearfriend/.m2/repository/com/evget/gateway/2.0.0/gateway-2.0.0.jar"
# 应用程序日志文件的路径（可选）
YAML_NAME="/Users/dearfriend/.m2/repository/com/evget/gateway/2.0.0/gateway.yaml"
JAVA_OPTS="-Xms256m -Xmx512m -Dfile.encoding=utf-8"
APP_LOG="/Users/dearfriend/.m2/repository/com/evget/gateway/2.0.0/opt/logs/gateway/error.log"

# 函数：检查应用程序是否正在运行
is_running() {
    pgrep -f "$APP_JAR" >/dev/null
}

# 函数：启动应用程序
start() {
    if is_running; then
        echo "应用程序已经在运行."
    else
        echo "正在启动应用程序..."
        nohup java -jar "$APP_JAR" "$JAVA_OPTS" -Dconfig.file="$YAML_NAME"  >/dev/null 2>&1 &
        echo "应用程序启动完成."
    fi
}

# 函数：停止应用程序
stop() {
    if is_running; then
        echo "正在停止应用程序.."
        pkill -f "$APP_JAR"
        echo "应用程序停止完成."
    else
        echo "应用程序没有在运行."
    fi
}

# 函数：重启应用程序
restart() {
    stop
	echo "${APP_NAME} 准备重启..."
    sleep 5
    start
}

# 函数：查看应用程序状态
status() {
    if is_running; then
        echo "应用程序正在运行."
        # 可选：显示更多状态信息，如日志的最后几行
        tail -n 100 "$APP_LOG"
    else
        echo "应用程序没有在运行."
    fi
}

# 解析命令行参数
case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        restart
        ;;
    status)
        status
        ;;
    *)
        echo "Usage: $0 [start|stop|restart|status]"
        ;;
esac

# 退出脚本
exit 0
```

### spring配置

```yaml
dubbo:
  cloud:
    subscribed-services: ""
  protocols:
    dubbo:
      id: dubbo
      name: dubbo
      port: -1
  provider:
    timeout: 60000
  consumer:
    check: false
    timeout: 60000
  registry:
    address: nacos://127.0.0.1:8848
    parameters[namespace]: erdf_d
    check: false
---
logging:
  level:
    root: warn
    com.evget: debug
---
mybatis:
  configuration:
    map-underscore-to-camel-case: true
---
server:
  port: 8110
spring:
  application:
    name: function-test
  cache:
    type: redis
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
        namespace: erdf_d
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/evget
    username: root
    password: 55646478aas
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    time-zone: GMT+8
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  redis:
    host: localhost
    port: 6379
    password:
    database: 0
---
config:
  token:
    expire:
      time: 3600
  user:
    password: 123456
  device: 4f2612aa5943a25459e25720a51bc895
  signature: 551f0a4dfcfc5489f7818024f390449924c3eff3f1fcc1e460b680630931a6fbd14c792cae0c6a3643d62ca9bf0f390739e9533534fdb5baeac17b4195da063515b5d7501b07a90f21e44178e0ccff11
  data:
    attribute: false
    dictionary: false
    privilege: false

```



## 环境安装

### 所需服务:gateway、function-user安装

```xml
<!--        <dependency>-->
<!--            <groupId>com.evget</groupId>-->
<!--            <artifactId>gateway</artifactId>-->
<!--            <version>3.1.0</version>-->
<!--        </dependency>-->
<!--        <dependency>-->
<!--            <groupId>com.evget</groupId>-->
<!--            <artifactId>function-user-exact</artifactId>-->
<!--            <version>2.2.0</version>-->
<!--        </dependency>-->
```



### Redis安装

[RDM教程](https://blog.csdn.net/boboJon/article/details/135073969?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-5-135073969-blog-133809206.235^v43^pc_blog_bottom_relevance_base7&spm=1001.2101.3001.4242.4&utm_relevant_index=6)

1.安装

```shell
brew install redis
```

2.启动

```
redis-server
```

3.测试安装是否成功

```shell
redis-cli ping
```

如果返回 "PONG"，则表示安装成功。

4.设置开机自启

```shell
brew services start redis	
```

常用命令

开启服务

```bash
brew services start redis
```

#### 关闭服务

```bash
brew services stop redis
```

#### 指定端口

```bash
redis-cli -h localhost -p 6379 
```

- -h ip地址
- -p 端口号
- -a 密码（如果需要）

- 文件位置:/opt/homebrew/opt
- 配置位置:/opt/homebrew/etc/redis.conf

### Nacos安装

[下载地址](https://nacos.io/download/nacos-server/)

[安装教程](https://blog.csdn.net/gongzi_9/article/details/123359171/)

- 启动服务

```
 sh startup.sh -m standalon
```

进入可视化页面http://127.0.0.1:8848/nacos/ ，账号密码都是nacos，进行登录即可，nacos的端口为**8848**

## ERDF框架学习

### Entity和DTO

com.evget.annotate包

#### Entity实体类

1. 注解

- 实体类注解

  ```java
  EntityAnnotation //实体类动态字段注解
  ```

  

- @FiledAnnotation——字段注解

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FieldAnnotate {
    boolean isRequire() default false;    //是否必填

    String requireComment() default "";   //必填提示语

    boolean isUnique() default false;     //是否唯一

    String uniqueComment() default "";     //唯一提示语

    String pattern() default "";          //正则表达式

    String patternComment() default "";    //正则表达提示语
}  //string错误则提示


//还包括
String table() default "";  //关联表
String field() default "id"; //关联表关联属性
String comment() default ""; //名称
```

用法例子

```java 
//正则表达式
@FieldAnnotation(pattern = "^[0~9]{11}$")    //字段必须是11位的数字
```



#### DTO

1.DTO注解

```
@DTOAnnotation(entity = xxx.class ,comment = "xxx")
```

```java
package com.evget.annotate;

import com.evget.entity.BaseEntity;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface DTOAnnotate {
    Class<? extends BaseEntity> entity();   //指向的实体类

    String comment();           //名称

    boolean isSelectTenant() default false;

    boolean isSelectInsertUser() default false;

    boolean isSelectUpdateUser() default false;
}
```

2. DTO属性注解

- Attribute注解: ***DTO属性添加此注解进入平台通用逻辑(同实体类字段可以不佳)***TIME:21-00

```java
package com.evget.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface AttributeAnnotate {
    String comment() default "";    //名称

    boolean isShow() default true;   

    boolean isShowSave() default false;   //是否支持表单显示

    boolean isShowSaveWrite() default false;   //是否支持表单编辑

    boolean isShowSelects() default false;    //是否支持列表显示

    boolean isShowSelectsSort() default false;    //是否列表排序

    boolean isShowSearch() default false;   //是否高级搜索

    boolean isShowLikes() default false;   //是否模糊搜索

    boolean isImport() default false;   //是否导入

    boolean isExport() default false;    //是否导出
 
    short sort() default 0;
} //设置了支持,则可以使用功能
```

- 数据字典注解

```java
package com.evget.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface DictionaryAnnotate {
    Class<?> enumerate();

    short type() default 1;
}
```

- 关联相关注解

```java
package com.evget.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface JoinAnnotate {
    String field() default "id";  //关联DTO关联属性 和表关联的属性

    String[] fields() default {};   //关联DTO查询属性   需要获得的属性
}
```



### 通用接口(common-config)

control、service卸载business-exact下

#### 自定义Control方法



#### 自定义Service方法



#### 通用接口-term



#### 通用接口-添加



### 数据字典

1. 注解

- DictionaryAnnotation

```java
package com.evget.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface DictionaryAnnotate {
    Class<?> enumerate();   //指定是哪个枚举类

    short type() default 1;    //1-单选或者是2-多选
}
```