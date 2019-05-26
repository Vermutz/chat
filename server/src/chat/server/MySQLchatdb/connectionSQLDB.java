package chat.server.MySQLchatdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class connectionSQLDB {

    private static final String URL="jdbc:mysql://localhost:3306/chatdb?autoReconnect=true&useSSL=false&cccCuseLegacyDatetimeCode=false&serverTimezone=UTC";;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    protected static Connection Msqlconnection;

    public static void Msqlconnect(){
        try {
            //Загрузить в память драйвер
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.err.println("Exception:"+e);
        }
    }

    public static void getMSQLconnect(){
        try{
            Msqlconnection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Connect to MySQL chatdb");
        }catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }
    public static void closeMSQLconnect(){
        try {
            Msqlconnection.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }
}
