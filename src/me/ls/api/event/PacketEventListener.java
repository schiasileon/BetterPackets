package me.ls.api.event;

import me.ls.api.event.events.ClientPacketReceive;
import me.ls.api.event.events.ServerClientConnect;
import me.ls.api.event.events.ServerPacketReceive;

public interface PacketEventListener {

    public void onEvent(Event e);

}
