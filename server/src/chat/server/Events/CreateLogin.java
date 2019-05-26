package chat.server.Events;

import chat.server.MySQLchatdb.CheckForUniqueness;
import chat.server.MySQLchatdb.CreateUser;

public class CreateLogin {
    public static boolean CreateLogin(String login,String password){
       boolean b = CheckForUniqueness.CheckForUniquenessLogin(login);
       if (b){
           CreateUser.CreateUser(login, password);
           return b;
       }else {
           return b;
       }
    }
}
