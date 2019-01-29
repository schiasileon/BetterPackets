package me.ls.api;

import me.ls.api.event.EventRegister;
import me.ls.api.event.PacketEventListener;
import me.ls.api.netObj.BetterClient;
import me.ls.api.netObj.BetterServer;

import java.io.IOException;

public class BetterPackets {

    /**
     *
     * Read ExampleServer for complete documentation and explanation
     *
     * @param port Port to start the server on
     * @return The new created server (not started!)
     */
    public static BetterServer createServer(int port) throws IOException {
        return new BetterServer(port);
    }

    /**
     *
     * Read ExampleClient for complete documentation and explanation
     *
     * @return The new created client (not started/connected)
     */
    public static BetterClient createClient(){
        return new BetterClient();
    }

    /**
     *
     * Adds new event to the event-system
     *
     * Full usage documentation/explanation in ExampleServer/ExampleClient
     *
     * @param e The PacketEventListener to add
     */
    public static void addEvent(PacketEventListener e){
        EventRegister.add(e);
    }

}
