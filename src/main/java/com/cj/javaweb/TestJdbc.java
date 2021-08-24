package com.cj.javaweb;

import java.sql.*;

/**
 * @author CJ
 * @date 2021/7/14 15:46
 */
public class TestJdbc {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String username = "root";
        String password = "cj";
        String url = "jdbc:mysql://chenjie.asia:3306/test?useUnicode=true&characterEncoding=utf-8" +
                "&useSSL=true&serverTimezone=UTC&allowMultiQueries=true";
        String driver = "com.mysql.jdbc.Driver";

        // 1.加载驱动(通过反射来加载Driver类，加载类时执行静态代码块将Driver注册到DriverManager中)
        Class.forName(driver);
//        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        // 2.连接数据库
        Connection connection = DriverManager.getConnection(url, username, password);
        // 3.获取Statement对象用于发送SQL
        Statement statement = connection.createStatement();
        // 4.执行SQL，返回结果
        String sql = "select * from t_user where id = ";
        String uid = "1 or '0' = '0'";
        ResultSet resultSet = statement.executeQuery(sql + uid);

        while (resultSet.next()) {
            Object id = resultSet.getObject("id");
            Object name = resultSet.getObject("name");
            Object gender = resultSet.getObject("gender");
            Object age = resultSet.getObject("age");
            System.out.println("id:" + id + " -- name:" + name + " -- gender:" + gender + " -- age:" + age);
        }

        // 5.关闭连接
        resultSet.close();
        statement.close();
        connection.close();

    }
}
