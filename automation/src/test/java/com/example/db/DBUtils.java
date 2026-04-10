package com.example.db;

import com.example.utils.ConfigReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
    private static Connection connection;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null || connection.isClosed()) {
            Class.forName(ConfigReader.getDbDriver());
            connection = DriverManager.getConnection(
                ConfigReader.getDbUrl(),
                ConfigReader.getDbUsername(),
                ConfigReader.getDbPassword()
            );
            System.out.println("Database connection established");
        }
        return connection;
    }

    public static List<Map<String, String>> executeQuery(String query) throws SQLException, ClassNotFoundException {
        List<Map<String, String>> resultList = new ArrayList<>();
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            Map<String, String> rowMap = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                String value = resultSet.getString(i);
                rowMap.put(columnName, value);
            }
            resultList.add(rowMap);
        }

        resultSet.close();
        statement.close();
        return resultList;
    }

    public static int executeUpdate(String query) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        int result = statement.executeUpdate(query);
        statement.close();
        return result;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

    public static int getTransactionCount() throws SQLException, ClassNotFoundException {
        List<Map<String, String>> result = executeQuery("SELECT COUNT(*) as count FROM transactions");
        if (!result.isEmpty()) {
            return Integer.parseInt(result.get(0).get("count"));
        }
        return 0;
    }

    public static String getTotalAmount(String month) throws SQLException, ClassNotFoundException {
        String query = "SELECT SUM(amount) as total FROM transactions WHERE month = '" + month + "'";
        List<Map<String, String>> result = executeQuery(query);
        if (!result.isEmpty() && result.get(0).get("total") != null) {
            return result.get(0).get("total");
        }
        return "0";
    }
}
