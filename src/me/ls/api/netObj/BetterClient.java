package me.ls.api.netObj;

import me.ls.api.event.events.ClientLostConnection;
import me.ls.api.event.events.ClientPacketReceive;
import me.ls.api.packet.BetterPacket;
import me.ls.api.packet.IncomingPacket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BetterClient {

    private Socket socket;

    private String host;
    private int port;

    public boolean connect(String host, int port) {

        this.host = host;
        this.port = port;
        try {
            socket = new Socket(host, port);
            startListening();
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public void disconnect(){
        try {

            socket.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void startListening(){
        //Incomeing
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    while(true){
                        String line = reader.readLine();
                        if(line == null){
                            throw new IOException();
                        }
                        IncomingPacket p = new IncomingPacket(line);
                        new ClientPacketReceive(p);
                    }

                }catch (IOException e){
                    new ClientLostConnection();
                }
            }
        }).start();
    }

    public void sendPacket(BetterPacket packet){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());

                    String send = BetterPacket.preparePacket(packet);
                    writer.println(send);
                    writer.flush();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
