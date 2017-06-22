## 项目介绍
基于Spring Boot + Mybatis开发系统架构。
初次使用Spring Boot开发，作为蜗牛背题APP的后台服务，同时也为了学习新的框架。



## 环境搭建
### 开发工具:

- MySql: 数据库
- Tomcat: 应用服务器
- Git: 版本管理
- IntelliJ IDEA: 开发IDE
- Navicat for MySQL: 数据库客户端

## 开发环境：

- Jdk7
- Mysql 5.7



## 打包
mvn clean package  -Dmaven.test.skip=true

> 如果想打包成jar文件，则删除pom.xml中：
```java
<packaging>war</packaging>
```