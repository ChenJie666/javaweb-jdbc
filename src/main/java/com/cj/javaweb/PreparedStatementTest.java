package com.cj.javaweb;

import java.sql.*;

/**
 * @author CJ
 * @date 2021/7/14 16:09
 */
public class PreparedStatementTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String username = "root";
        String password = "cj";
        String url = "jdbc:mysql://chenjie.asia:3306/test?useUnicode=true&characterEncoding=utf-8" +
                "&useSSL=true&serverTimezone=UTC&allowMultiQueries=true";
        String driver = "com.mysql.jdbc.Driver";

        // 1.加载驱动
        Class.forName(driver);
        // 2.获取数据库连接
        Connection connection = DriverManager.getConnection(url, username, password);
        // 3.获取PreparedStatement对SQL进行预编译
        String sql = "select * from t_user where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"1' or '0'='0");
        System.out.println(preparedStatement.toString());
        // 4.执行SQL，返回结果
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Object id = resultSet.getObject("id");
            Object name = resultSet.getObject("name");
            Object gender = resultSet.getObject("gender");
            Object age = resultSet.getObject("age");
            System.out.println("id:" + id + " -- name:" + name + " -- gender:" + gender + " -- age:" + age);
        }

        // 5.关闭资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
