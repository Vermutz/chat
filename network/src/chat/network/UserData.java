package chat.network;

import java.io.Serializable;

public class UserData implements Serializable {
    private static String Login,Password, Messeng,SystemMesseng;
    private static boolean newUser,identification=false;
    private static int IdentificationNumber ;

    public static void setLogin (String login){
        Login=login;
    }
    public static String getLogin(){
        return Login;
    }

    public static void setPassword (String password){
        Password=password;
    }
    public static String getPassword(){
        return Password;
    }

    public static void setNewUser(Boolean newuser){
        newUser=newuser;
    }
    public static boolean getnewUser(){
        return newUser;
    }

    public static void setIdentification(Boolean Identification){
        identification=Identification;
    }
    public static boolean getidentification(){
        return identification;
    }

    public static void setMesseng(String messeng){
        Messeng=messeng;
    }
    public static String getMesseng(){
        return Messeng;
    }

    public static void setSystemMesseng(String systemmesseng){
        SystemMesseng=systemmesseng;
    }
    public static String getSystemMesseng(){
        return SystemMesseng;
    }

    public static void setIdentificationNumber (int identificationNumber){
        IdentificationNumber=identificationNumber;
    }
    public static int getIdentificationNumber(){
        return IdentificationNumber;
    }
}
