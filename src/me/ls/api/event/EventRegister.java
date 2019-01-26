package me.ls.api.event;

import java.util.ArrayList;

public class EventRegister {

    private static ArrayList<PacketEventListener> eventListeners = new ArrayList<>();

    public static void add(PacketEventListener e){
        eventListeners.add(e);
    }

    public static void call(Event e){
        for(PacketEventListener l : eventListeners){
            l.onEvent(e);
        }
    }

}
