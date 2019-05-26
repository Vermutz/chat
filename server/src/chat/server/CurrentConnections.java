package chat.server;

import chat.network.TCPConnection;
import chat.network.UserData;

import java.util.ArrayList;
import java.util.HashMap;

public class CurrentConnections {

    public static final ArrayList<TCPConnection> connections = new ArrayList<>();
    public static final HashMap<String, UserData> UDM= new HashMap<>();
    public static final HashMap<String,TCPConnection> AuthorizedUsersConnections = new HashMap<>();
}
