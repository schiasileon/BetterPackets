package me.ls.api.netObj;

import me.ls.api.event.events.ServerClientDisconnect;
import me.ls.api.event.events.ServerPacketReceive;
import me.ls.api.packet.BetterPacket;
import me.ls.api.packet.IncomingPacket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BetterServerClient {

    private Socket socket;

    public BetterServerClient(Socket s) {
        this.socket = s;

        startListening();
    }

    private void startListening(){


        BetterServerClient client = this;

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
                        new ServerPacketReceive(p, client);
                    }

                }catch (IOException e){
                    new ServerClientDisconnect(client);
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
                    writer.println(BetterPacket.preparePacket(packet));
                    writer.flush();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public Socket getSocket() {
        return socket;
    }
}
