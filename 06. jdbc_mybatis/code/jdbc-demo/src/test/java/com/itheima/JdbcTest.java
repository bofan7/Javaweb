package com.itheima;

import com.itheima.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class JdbcTest {

    /**
     * JDBC入门程序
     */
    @Test
    public void testUpdate() throws Exception {
        //1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2. 获取数据库连接
        String url = "jdbc:mysql://localhost:3306/web01";
        String username = "root";
        String password = System.getenv("DB_PASSWORD");
        Connection connection = DriverManager.getConnection(url, username, password);

        //3. 获取SQL语句执行对象
        Statement statement = connection.createStatement();

        //4. 执行SQL
        int i = statement.executeUpdate("update user set age = 25 where id = 1");//DML
        System.out.println("SQL updated rows: " + i);

        //5. 释放资源
        statement.close();
        connection.close();
    }


    @Test
    public void testSelect(){
        String URL = "jdbc:mysql://localhost:3306/web01?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
        String USER = "root";
        String PASSWORD = System.getenv("DB_PASSWORD");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet results = null; //封装查询返回的结果

        try {
            // 1. 注册 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 打开链接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 3. 执行查询
            String sql = "SELECT id, username, password, name, age FROM user WHERE username = ? AND password = ?"; //预编译SQL
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, "daqiao");
            stmt.setString(2, "123456");

            results = stmt.executeQuery();

            // 4. 处理结果集
            while (results.next()) {
                User user = new User(
                        results.getInt("id"),
                        results.getString("username"),
                        results.getString("password"),
                        results.getString("name"),
                        results.getInt("age")
                );
                System.out.println(user); // 使用 Lombok 的 @Data 自动生成的 toString 方法
            }
        } catch (SQLException se) {
            // Handle erroresults for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle erroresults for Class.forName
            e.printStackTrace();
        } finally {
            // 5. 关闭资源
            try {
                if (results != null) results.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

}
