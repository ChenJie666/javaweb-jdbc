package com.cj.javaweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author CJ
 * @date 2021/7/16 15:32
 */
public class TransactionTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String username = "root";
        String password = "cj";
        String url = "jdbc:mysql://chenjie.asia:3306/test?useUnicode=true&characterEncoding=utf-8" +
                "&useSSL=true&serverTimezone=UTC&allowMultiQueries=true";
        String driver = "com.mysql.jdbc.Driver";

        // 1.注册驱动
        Class.forName(driver);
        // 2.创建链接
        Connection connection = DriverManager.getConnection(url, username, password);
        // 3.开启事务
        connection.setAutoCommit(false);
        // 4.获取Statement对象用于提交SQL
        String sql = "update t_user set balance = balance + ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        try {
            // 5.执行SQL
            preparedStatement.setInt(1, 100);
            preparedStatement.setInt(2, 1);
            preparedStatement.executeUpdate();

            int i = 1 / 0;

            preparedStatement.setInt(1, -100);
            preparedStatement.setInt(2, 2);
            preparedStatement.executeUpdate();

            // 6.1提交事务
            connection.commit();
        } catch (Exception e) {
            // 6.2回滚事务
            System.out.println(e.getMessage());
            connection.rollback();
        } finally {
            // 7.关闭连接
            preparedStatement.close();
            connection.close();
        }

    }
}
