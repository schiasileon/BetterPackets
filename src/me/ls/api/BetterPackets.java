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
    public static BetterServer createServer(int port){
        try {
            return new BetterServer(port);
        } catch (IOException e) {
            System.out.println("Port already in use");
        }
        return null;
    }

    /**
     *
     * Read ExampleClient for complete documentation and explanation
     *
     * @param host The host (ip) to connect to
     * @param port The port the server is on
     * @return The new created client (not started/connected)
     */
    public static BetterClient createClient(String host, int port){
        return new BetterClient(host, port);
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
