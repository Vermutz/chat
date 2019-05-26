package chat.server;

import chat.network.TCPConnection;
import chat.network.TCPConnectionListener;
import chat.network.UserData;
import chat.server.Events.CreateLogin;
import chat.server.MySQLchatdb.*;
import com.sun.tools.classfile.Opcode;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ChatServer extends CurrentConnections implements TCPConnectionListener {



    public static void main(String[] args){
        connectionSQLDB.Msqlconnect();
        new ChatServer();
    }


    private ChatServer(){

        System.out.println("Server runnung...");
        try {
            ServerSocket serverSocket= new ServerSocket(54548);
            while (true){
                try {
                    new TCPConnection(this,serverSocket.accept());

                }catch (IOException e){
                    System.out.println("TCPConnection Exception: "+e);
                }
            }
        }catch (IOException e){
            throw new RuntimeException();
        }

    }

    @Override
    public synchronized void onConnectionReady(TCPConnection tcpConnection) {
        connections.add(tcpConnection);;
    }

    @Override
    public synchronized void onDisconnect(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        if(AuthorizedUsersConnections.containsValue(tcpConnection)) {
            String login = getKeyFromValue(AuthorizedUsersConnections, tcpConnection);
            UDM.remove(login);
            AuthorizedUsersConnections.remove(login);
        }
    }

    @Override
    public synchronized void onException(TCPConnection tcpConnection, Exception e) {
        System.out.println("TCPConnection Exception: "+e);
    }


    @Override
    public synchronized void onContainerTransfer(TCPConnection tcpConnection, UserData UD) {
        boolean identification=UD.getidentification();
        if(!identification) {
            Boolean NewUser = UD.getnewUser();
            if (NewUser) {
                identification = CreateLogin.CreateLogin(UD.getLogin(), UD.getPassword());
                UD.setIdentification(identification);
                if (identification) {
                    UD.setNewUser(false);
                    UDM.put(UD.getLogin(), UD);
                    AuthorizedUsersConnections.put(UD.getLogin(),tcpConnection);
                } else {
                    UD.setSystemMesseng("Login already exists");
                }
                tcpConnection.ConteinerTransfer(UD);
            } else {
                identification = Login(UD.getLogin(), UD.getPassword());
                UserData.setIdentification(identification);
                if (identification) {
                    UDM.put(UD.getLogin(), UD);
                    AuthorizedUsersConnections.put(UD.getLogin(),tcpConnection);
                    UD.setMesseng("Authorized");
                } else {
                    UD.setMesseng("Invalid login or password");
                }
                tcpConnection.ConteinerTransfer(UD);
            }
        }else {
            String massenge=UD.getMesseng();

        }
    }


    public boolean Login(String login, String Password) {
        boolean res;
        String pas = GETPASSWORD.getPassword(login);
        if (Password==pas){
            res=true;
        }else {
            res=false;
        }
        return res;
    }


    public void CreateLogin(String login, String Password) {
        CreateUser.CreateUser(login,Password);
    }

    public static String getKeyFromValue(HashMap hm, Object value) {
        Set KEYS = hm.keySet();
        Iterator<String> keys= KEYS.iterator();
        boolean KeyFound=false;
        while (keys.hasNext() & !KeyFound){
            String key = keys.next();
            if (hm.get(key).equals(value)){
                KeyFound=true;
                return key;
            }
        }
        return null;
    }




}
