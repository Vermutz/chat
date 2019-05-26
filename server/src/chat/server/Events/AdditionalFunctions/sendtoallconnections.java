package chat.server.Events.AdditionalFunctions;

import chat.server.CurrentConnections;

public class sendtoallconnections extends CurrentConnections {

    public static void sendtoallconnections(String value){

        System.out.println(value);
        final int cnt=connections.size();
        for (int i=0;i<cnt;i++) connections.get(i).SendString(value);
    }
}
