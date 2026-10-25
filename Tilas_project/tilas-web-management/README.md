# Tlias / JavaWeb 管理系统

从个人 JavaWeb 项目提取的精简版本。保留三模块后端、Vue 前端、数据库初始化脚本与运行配置。

## 依赖

- JDK 21、Maven 3.9+（Spring Boot 3.4.12）
- Node.js 20 LTS（前端 Vite 3 / Vue 3）、MySQL 8.0
- Docker Desktop 可选，用于启动 MySQL。安装包从官方网站获取，不收录进仓库。

## 本地运行（PowerShell）

1. `Copy-Item .env.example .env`，编辑 `.env` 设置本机数据库密码。
2. `docker compose up -d`。首次启动自动导入 `runtime/mysql/init/tilas.sql` 到 `tilas` 库。已有数据库也可手动导入该 SQL。
3. 为后端进程设置变量（Compose 的 `.env` 不会自动注入本机 Java 进程）：

```powershell
$env:DB_PASSWORD = '<与 .env 一致的密码>'
$env:JWT_SECRET = '<随机生成、至少32字符的JWT签名密钥>'
mvn -DskipTests package
java -jar tilas-web/target/tilas-web-0.0.1-SNAPSHOT.jar
```

4. 另开终端：`cd frontend`，执行 `npm ci`、`npm run dev`，打开终端显示的地址。`/api` 会代理至 `http://localhost:8080`。
5. 图片上传需额外设置 `OSS_ACCESS_KEY_ID`、`OSS_ACCESS_KEY_SECRET`、`OSS_BUCKET_NAME`；可通过 `OSS_ENDPOINT`、`OSS_REGION` 覆盖地域。使用自己的 OSS 账号。

MySQL 默认账号为 `root`，可通过 `DB_USERNAME`、`DB_URL` 覆盖。登录账号使用初始化 SQL 中的课程示例数据。

## 整理范围

- 保留 `tilas-pojo`、`tilas-utils`、`tilas-web` 与 `frontend`。
- 移除父目录重复的 `src`、课堂演示工程、PPT、笔记、安装包、IDE 配置、日志与编译产物。
- 数据库密码、OSS 凭证与 JWT 密钥改从环境变量读取。
- SQL 来自课程部署资源，数据库名统一为源码配置使用的 `tilas`。

原有集成测试依赖本地数据库和 OSS；打包命令跳过测试，并不表示已验证外部服务联调。
