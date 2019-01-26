package me.ls.api.example;

import me.ls.api.packet.BetterPacket;
import me.ls.api.packet.Packet;

/**
 * All packets created must extend Packet and implement BettePacket
 */
public class ExampleHelloPacket extends Packet implements BetterPacket {

    /**
     * Define variables you need in your packet
     */
    private String message;

    /**
     *
     *      ! YOU ALWAYS NEED BOTH CONTRUCTORS !
     *
     */
    /**
     * This Constructor is for incoming packets and will parse
     * the data of BetterPacket (Content) to the variables you
     * need and declared earlier
     *
     * @param packetIn  The BetterPacket which is received
     */
    public ExampleHelloPacket(BetterPacket packetIn) {
        message = packetIn.getContent()[0];
    }

    /**
     * This Contructor is for building a new packet of this type
     * which is sendable
     *
     * You would use this constructor for sending out new packets
     *
     * @param message Our hello-message we want to send
     */
    public ExampleHelloPacket(String message) {
        this.message = message;
    }

    /**
     * This method is implemented from BetterPacket
     *
     * You should set the return String to the type you want the
     * packet to be
     *
     * @return In this case our type 'HELLO'
     */
    @Override
    public String getType() {
        return "HELLO";
    }

    /**
     * This method returns the content of our packet. It should always include every information
     * needed for building a new packet with the second constructor.
     *
     * In this example our content is only our message String
     *
     * @return The content of the packet
     */
    @Override
    public String[] getContent() {
        return new String[]{message};
    }

    /**
     *
     * Now declare your getters you need to get all the information from the packet
     *
     */
    public String getMessage(){
        return message;
    }


}
