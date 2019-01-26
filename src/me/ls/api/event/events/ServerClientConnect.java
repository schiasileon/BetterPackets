package me.ls.api.event.events;

import me.ls.api.event.Event;
import me.ls.api.event.EventRegister;
import me.ls.api.netObj.BetterServerClient;

public class ServerClientConnect extends Event {

    private BetterServerClient client;

    public ServerClientConnect(BetterServerClient c) {
        this.client = c;

        EventRegister.call(this);
    }

    public BetterServerClient getClient() {
        return client;
    }
}
