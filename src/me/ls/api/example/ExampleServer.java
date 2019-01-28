package me.ls.api.example;

import me.ls.api.BetterPackets;
import me.ls.api.event.Event;
import me.ls.api.event.PacketEventListener;
import me.ls.api.event.events.ServerClientConnect;
import me.ls.api.event.events.ServerClientDisconnect;
import me.ls.api.event.events.ServerPacketReceive;
import me.ls.api.netObj.BetterServer;
import me.ls.api.netObj.BetterServerClient;
import me.ls.api.packet.BetterPacket;

import java.io.IOException;


/**
 *
 * This is an example for creating a Server with this API
 *
 */
public class ExampleServer implements PacketEventListener {

    public static BetterServer server;

    public static void main(String[] args){

        /**
         * First you register your EventListener(s)
         *
         * In this case the ExampleServer class must implement PacketEventListener
         *
         */
        BetterPackets.addEvent(new ExampleServer());

        /**
         * Then you can start the Server by creating a BetterServer object
         *
         * This Server will start with port 333333
         */
        try {
            server = BetterPackets.createServer(33333);
        } catch (IOException e) {
            System.out.println("Port already in use");
            return;
        }
        server.start();

    }

    /**
     *
     * This method is implemented from PacketEventListener
     *
     * It is called every time the server or client is doing some action
     * like receiving a packet or a new client connect.
     *
     * All events are located in me.ls.api.event.events
     *
     * @param e The Event which is fired
     */
    @Override
    public void onEvent(Event e) {
        /**
         * To filter out each event use the instanceof operator
         */
        if(e instanceof ServerClientConnect){
            /**
             * Then you can do some casting
             *
             * BetterServerClient is the client which (in this case) is connecting
             */
            BetterServerClient client = ((ServerClientConnect) e).getClient();

            /**
             * Now we send a little hello-message to the new client.
             *
             * You can create Packets on your own (see ExampleHelloPacket class)
             */
            ExampleHelloPacket p = new ExampleHelloPacket("Server: hello");
            client.sendPacket(p);

        }else if(e instanceof ServerPacketReceive){

            /**
             * Now we listen for receiving a packet
             *
             * Then we cast the incoming packet of the event to a BetterPacket object
             */
            BetterPacket packet = ((ServerPacketReceive) e).getPacket();

            /**
             * To filter out each packet you have to compare the packet-types
             *
             * To keep it simple and not have to worry about misspelling we simply
             * create a new empty packet of the one we want to listen to and then get
             * the type of it
             *
             * Instead of 'new ExampleHelloPacket("").getType()' you could also use
             * 'HELLO' only (because 'HELLO' is the type of ExampleHelloPacket
             */
            if(packet.getType().equalsIgnoreCase(new ExampleHelloPacket("").getType())){

                /**
                 * Then we create the ExampleHelloPacket from the normal packet
                 * and then print out the message of our ExampleHelloPacket we received
                 */
                ExampleHelloPacket p = new ExampleHelloPacket(packet);
                System.out.println(p.getMessage());

            }
        }else if(e instanceof ServerClientDisconnect){
            System.out.println("Client disconnected");
        }
    }
}
