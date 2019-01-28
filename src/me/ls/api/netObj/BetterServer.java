package me.ls.api.netObj;

import me.ls.api.event.EventRegister;
import me.ls.api.event.PacketEventListener;
import me.ls.api.event.events.ServerClientConnect;
import me.ls.api.packet.BetterPacket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class BetterServer {

    private ServerSocket serverSocket;
    private Thread listenerThread;

    private static ArrayList<BetterServerClient> clients = new ArrayList<>();

    private int port;

    public BetterServer(int port) throws IOException {

        this.port = port;

        serverSocket = new ServerSocket(port);

    }

    public void start(){
        startListening();
    }

    public void stop(){
        try {
            for(BetterServerClient client : clients){
                client.getSocket().close();
            }
            listenerThread.stop();
            serverSocket.close();
            serverSocket = new ServerSocket(port);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendPacket(BetterServerClient client, BetterPacket packet){
        client.sendPacket(packet);
    }

    private void startListening(){
        try {

            listenerThread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        Socket newClient;
                        while ((newClient = serverSocket.accept()) != null){
                            BetterServerClient c = new BetterServerClient(newClient);
                            clients.add(c);
                            new ServerClientConnect(c);
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            });
            listenerThread.start();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<BetterServerClient> getClients(){
        return clients;
    }

}
