package com.cj.javaweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

/**
 * @author CJ
 * @date 2021/7/16 9:49
 */
public class PreparedStatementTest1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String username = "root";
        String password = "cj";
        String url = "jdbc:mysql://chenjie.asia:3306/test?useUnicode=true&characterEncoding=utf-8" +
                "&useSSL=true&serverTimezone=UTC&allowMultiQueries=true";
        String driver = "com.mysql.jdbc.Driver";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);
        String sql = "insert into t_user values(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 4);
        preparedStatement.setString(2, "wangwu");
        preparedStatement.setString(3, "nan");
        preparedStatement.setInt(4, 22);
        preparedStatement.setDate(5, new Date(new java.util.Date().getTime()));

        int update = preparedStatement.executeUpdate();
        System.out.println("更新了" + update + "行");

        preparedStatement.close();
        connection.close();
    }
}
