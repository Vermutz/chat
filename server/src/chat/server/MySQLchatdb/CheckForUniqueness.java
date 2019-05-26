package chat.server.MySQLchatdb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckForUniqueness extends connectionSQLDB {

    public static boolean CheckForUniquenessLogin(String login){
        boolean b = true;
        getMSQLconnect();
        try{
            Statement statement=Msqlconnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Login from users");
                  int i=0;
                 while (resultSet.next()){
                     String user =resultSet.getString("Login");
                      if (user.equals(login))b=false;
                 }
            resultSet.close();
            statement.close();
            closeMSQLconnect();
        }catch (SQLException e) {
            System.out.println("Exception: " + e);
        }
        return b;
    }
}
