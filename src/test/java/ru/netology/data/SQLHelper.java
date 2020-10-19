package ru.netology.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://192.168.99.100:3306/app", "app", "pass");
    }

    public static void cleanDb() {
        val runner = new QueryRunner();
        val creditRequest = "DELETE FROM credit_request_entity";
        val order = "DELETE FROM order_entity";
        val payment = "DELETE FROM payment_entity";

        try (val conn = getConnection()) {
            runner.update(conn, creditRequest);
            runner.update(conn, order);
            runner.update(conn, payment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getAnyData(String getStatus, String column) {
        try (val conn = getConnection();
             val countStmt = conn.createStatement();
        ) {
            try (val rs = countStmt.executeQuery(getStatus)) {
                if (rs.next()) {
                    val data = rs.getString(column);
                    return data;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCreditStatus() {
        val getStatus = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        val column = "status";
        return SQLHelper.getAnyData(getStatus, column);
    }

    public static String getPaymentStatus() {
        val getStatus = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        val column = "status";
        return SQLHelper.getAnyData(getStatus, column);
    }

    public static String getPaymentID() {
        val getStatus = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        val column = "payment_id";
        return SQLHelper.getAnyData(getStatus, column);
    }

    public static String getCreditID() {
        val getStatus = "SELECT bank_id FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        val column = "bank_id";
        return SQLHelper.getAnyData(getStatus, column);
    }

    public static String getTransactionID() {
        val getStatus = "SELECT transaction_id FROM payment_entity ORDER BY created DESC LIMIT 1";
        val column = "transaction_id";
        return SQLHelper.getAnyData(getStatus, column);
    }

    public static void comparePaymentAndTransactionID() {
        getPaymentID().equals(getTransactionID());
    }

    public static void compareCreditAndTransactionID() {
        getCreditID().equals(getTransactionID());
    }
}
