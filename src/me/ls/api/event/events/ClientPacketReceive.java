package me.ls.api.event.events;

import me.ls.api.event.Event;
import me.ls.api.event.EventRegister;
import me.ls.api.packet.BetterPacket;

public class ClientPacketReceive extends Event {

    private BetterPacket packet;

    public ClientPacketReceive(BetterPacket packet) {
        this.packet = packet;

        EventRegister.call(this);
    }

    public BetterPacket getPacket() {
        return packet;
    }

}
