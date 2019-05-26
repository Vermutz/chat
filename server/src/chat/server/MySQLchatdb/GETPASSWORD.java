package chat.server.MySQLchatdb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GETPASSWORD extends connectionSQLDB {
    public static String getPassword(String login){
        String password="";
        getMSQLconnect();
        try {
            Statement statement = Msqlconnection.createStatement();
            ResultSet resultSet = statement.executeQuery("select Password from Users where Login ="+login+";");
            password=resultSet.getString("Password");
            resultSet.close();
            statement.close();
            closeMSQLconnect();
        }catch (SQLException e){
            System.out.println("Exception: "+e);
        }
        return password;
    }
}
