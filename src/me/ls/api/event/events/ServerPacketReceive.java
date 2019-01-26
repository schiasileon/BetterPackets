package me.ls.api.event.events;

import me.ls.api.event.Event;
import me.ls.api.event.EventRegister;
import me.ls.api.netObj.BetterServerClient;
import me.ls.api.packet.BetterPacket;

public class ServerPacketReceive extends Event {

    private BetterPacket packet;
    private BetterServerClient client;

    public ServerPacketReceive(BetterPacket packet, BetterServerClient client) {
        this.packet = packet;
        this.client = client;

        EventRegister.call(this);
    }

    public BetterPacket getPacket() {
        return packet;
    }

    public BetterServerClient getClient() {
        return client;
    }
}
