# Git学习

## Git介绍

分布式版本控制系统

### 基本结构

<img src="/Users/dearfriend/Library/Application Support/typora-user-images/image-20240714173224838.png" alt="image-20240714173224838" style="zoom:50%;" />

### git和代码托管中心

代码托管中心是基于网络服务器的远程代码仓库,一般称之为远程库

- 局域网:gitLab

- 互联网:GitHub、Gitee

  

## Git安装

- 官网:[https://git-scm.com](https://git-scm.com)

## Git命令

### 基本语法

#### 用户签名

```sh
git config --global use.name xxx
git config --global use.email xxx@163.com
```

**注意:**此处的用户签名和姜莲登陆GitHub(或者其他托管中心)的账号没有任何关联,用来确认本次提交是谁做的

#### 库基本操作

```sh
#cd目标项目下
git init   		#初始化本地库

git status    #本地库相关状态

git add  			#建立跟踪——将工作区文件添加到暂存区
git rm --cached 文件  #将文件从暂存区删除(-f)强制删除
```



## Git分支

### 相关命令

```sh
git branch name 		#创建分支

git branch 					#查看分支

git branch -m oldname newname #更改分支命名

git branch -d dog  	#删除分支

git branch -D tiger	#强制删除未合并分支

git checkout tiger	#切换分支

git merge cat				#合并分支

```



## Idea集成Git

## GitHub

1. 创建远程库

2. 代码推送Push

3. 代码拉取Pull

4. 代码克隆Clone

5. SSH免密登录

6. Idea集成GitHub

   

   