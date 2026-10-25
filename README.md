# Javaweb

完整 JavaWeb 学习代码与运行资源合集，保留本地 **01–20 章和个人 Tilas_project** 的目录结构。

| 目录 | 内容 |
| --- | --- |
| 01–02 | HTML/CSS、JavaScript、Vue、Ajax 示例及图片、字体、音视频素材 |
| 03–06 | Maven、Spring Boot、SQL、JDBC、MyBatis 代码与示例数据 |
| 07–14 | 部门/员工管理各阶段源码、登录认证、AOP、自动配置、分模块与自定义 starter |
| 15–18 | Vue 工程化、ElementPlus、Tlias 各阶段前端与完整前后端源码 |
| 19–20 | Linux/Docker 部署用 SQL、Nginx 配置、静态站点、Dockerfile、Compose |
| Tilas_project | 个人项目、自定义 OSS starter、配置示例及 Tlias 前后端 |

## 提取规则

- 源码 ZIP 解压到同目录下的 `*-source/`，保留课程快照与个人修改版。11 个源码/前端归档已提取。
- 图片、图标、字体、光标、设计原型和 HTML 引用的音视频保留。Hero 示例 `big.rar` 中 20 张图片也已解压保留。
- 保留 SQL、Lua、配置、Dockerfile、依赖锁文件与程序读取的 `user.txt`。
- 排除 PPT、讲义、笔记、视频课程、PDF、IDE 文件、node_modules、target、日志、软件安装包、Nexus 和虚拟机镜像。
- 原始本地目录不会因本次提取被删除或覆盖。

## 软件与运行

各章节是独立教学工程，进入具体包含 `pom.xml` 或 `package.json` 的目录运行，不要把整个仓库当作一个 Maven 工程。

- Java/Maven：按各 `pom.xml` 的 Java 版本准备 JDK（个人 Tlias 使用 JDK 21），通常运行 `mvn -DskipTests package`，再启动生成的 JAR。
- 前端：有锁文件时执行 `npm ci`，再运行 `npm run dev` 或 `npm run build`。
- 数据库：导入相应章节 SQL，根据脚本库名设置连接地址。
- 数据库密码从 `DB_PASSWORD` 读取；OSS 凭证使用 `OSS_ACCESS_KEY_ID` / `OSS_ACCESS_KEY_SECRET`；JWT 示例使用 `JWT_SECRET`。为启动后端的终端设置这些环境变量。教学示例中的数据库地址、库名与 OSS bucket 仍需按自己的环境调整。
- 软件获取：[JDK](https://adoptium.net/)、[Maven](https://maven.apache.org/download.cgi)、[Node.js](https://nodejs.org/)、[MySQL](https://dev.mysql.com/downloads/mysql/)、[Nginx](https://nginx.org/en/download.html)、[Docker Desktop](https://www.docker.com/products/docker-desktop/)。
- 部署 Dockerfile 使用官方 Temurin 17/21 运行时镜像，避免携带 JDK 安装包。先从对应源码构建 JAR，再按 Dockerfile 中的 `app.jar` / `tlias.jar` 命名放入构建目录；JAR 不提交。
- 第 20 章 Compose 是课程 Linux 部署模板，卷路径为 `/usr/local/app/...`，运行前调整成本机路径，并将数据库密码等配置传入相应服务。

## 个人 Tlias 快速运行

进入 `Tilas_project/tilas-web-management`，参见其中 README。该目录补齐 MySQL 初始化 SQL、Compose、环境变量示例与启动说明。

## 验证范围

已验证个人 Tlias 后端打包和 Vue 前端构建。已核对提取代码、SQL 与美术资源的完整性；其他章节为历史教学快照，没有逐个启动、联调或批量修改业务逻辑。
