package me.ls.api.event.events;

import me.ls.api.event.Event;
import me.ls.api.event.EventRegister;
import me.ls.api.netObj.BetterServerClient;

public class ServerClientDisconnect extends Event {

    private BetterServerClient disconnected;

    public ServerClientDisconnect(BetterServerClient client){
        disconnected = client;

        EventRegister.call(this);
    }

    public BetterServerClient getClient() {
        return disconnected;
    }
}
