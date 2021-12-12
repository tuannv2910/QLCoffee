/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author CHIEN
 */
public class UtilsHelper {

    public static Connection cnn = null;

    public static synchronized Connection myConnection() {
        try {
            String url = "jdbc:sqlserver://localhost\\DESKTOP-8O064HD\\SQLEXPRESS:1433;databaseName=CF12";
            String user = "sa";
            String pass = "maivantung";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cnn = DriverManager.getConnection(url, user, pass);
            System.out.println("Kết nối thành công");
        } catch (Exception e) {
            System.out.println("Kết nối lỗi");
            cnn = null;
        }
        return cnn;
    }

    public static synchronized void closeConnection() {
        if (cnn != null) {
            try {
                cnn.close();
            } catch (Exception e) {
            } finally {
                cnn = null;
            }
        }
    }
}
