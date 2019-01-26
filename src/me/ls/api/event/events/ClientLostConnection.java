package me.ls.api.event.events;

import me.ls.api.event.Event;
import me.ls.api.event.EventRegister;

public class ClientLostConnection extends Event {

    public ClientLostConnection() {
        EventRegister.call(this);
    }
}
