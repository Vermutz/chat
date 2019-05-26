package chat.server.MySQLchatdb;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateUser extends connectionSQLDB {
    public static void CreateUser(String login, String password){
        getMSQLconnect();
        try{
            Statement statement = Msqlconnection.createStatement();
            statement.execute("INSERT INTO users(Login,Password) VALUES (" + login + "," + password + ");");
            statement.close();
            closeMSQLconnect();
        }catch (SQLException e){
            System.out.println("Exception: "+e);
        }
    }
}
