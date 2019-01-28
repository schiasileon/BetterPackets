package me.ls.api.example;

import me.ls.api.BetterPackets;
import me.ls.api.event.Event;
import me.ls.api.event.PacketEventListener;
import me.ls.api.event.events.ClientLostConnection;
import me.ls.api.event.events.ClientPacketReceive;
import me.ls.api.netObj.BetterClient;
import me.ls.api.packet.BetterPacket;

import java.io.IOException;

public class ExampleClient implements PacketEventListener {

    /**
     *
     *              ! FIRST READ ExampleServer CLASS !
     *
     *
     * First of all you need a global BetterClient variable
     */
    private static BetterClient client;

    public static void main(String[] args){

        /**
         * Then add your PacketEventListener again
         */
        BetterPackets.addEvent(new ExampleClient());

        /**
         * Create the BetterClient object and let it connect to the server
         *
         * In this example it will try to connect to localhost:33333
         *
         */
        client = BetterPackets.createClient("localhost", 33333);
        try {
            client.connect();
        } catch (IOException e) {
            System.out.println("Unknown host");
        }

    }

    /**
     * This method is implemented from PacketEventListener
     *
     * @param e The Event which is fired
     */
    @Override
    public void onEvent(Event e) {
        /**
         * Again check for the type of the event
         */
        if(e instanceof ClientPacketReceive){

            /**
             * Cast the incoming packet
             */
            BetterPacket packet = ((ClientPacketReceive) e).getPacket();

            /**
             * Check for the type of packet
             */
            if(packet.getType().equalsIgnoreCase(new ExampleHelloPacket("").getType())){

                /**
                 * Printing out the message of the received ExampleHelloPacket
                 *
                 * Then sending back a new ExampleHelloPacket to the server
                 */
                ExampleHelloPacket p = new ExampleHelloPacket(packet);
                System.out.println(p.getMessage());
                client.sendPacket(new ExampleHelloPacket("Client: hello"));

            }
        }else if(e instanceof ClientLostConnection){
            System.out.println("Lost connection to Server");
        }
    }
}
