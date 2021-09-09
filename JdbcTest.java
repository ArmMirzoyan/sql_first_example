package com.example.tomcattest;

import java.sql.*;

public class JdbcTest {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        final String dbName = "mysterious_database";
        final String url = "jdbc:postgresql://localhost:5432/" + dbName;
        final String user = "postgres";
        final String password = "12345";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (Statement statement = connection.createStatement()) {
                String sql = "select * from item";
                try (ResultSet result = statement.executeQuery(sql)) {
                    while (result.next()) {
                        String name = result.getString("name");
                        int id = result.getInt("id");
                        System.out.println("name = " + name + ", id = " + id);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
