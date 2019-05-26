package chat.server.Events;

import chat.server.Events.AdditionalFunctions.sendtoallconnections;

public class ReceveString {
    public static void ReceveString(String value){
        sendtoallconnections.sendtoallconnections(value);
    }
}
