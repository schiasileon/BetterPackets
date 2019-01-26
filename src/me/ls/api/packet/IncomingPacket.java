package me.ls.api.packet;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.util.ArrayList;

public class IncomingPacket extends Packet implements BetterPacket {

    private String type;
    private ArrayList<String> content = new ArrayList<>();

    public IncomingPacket(String rawIn) {

        String in = new String(Base64.decode(rawIn));

        type = in.split("#")[0];

        String enContent = new String(Base64.decode(new String(Base64.decode(in.split("#")[1]))));

        if(enContent.contains("#")){
            for(String encrypteTile : enContent.split("#")){
                String decrypted = new String(Base64.decode(encrypteTile));
                content.add(decrypted);
            }
        }else{
            content.add(enContent);
        }
    }

    public String getType(){
        return type;
    }

    @Override
    public String[] getContent() {
        return content.toArray(new String[0]);
    }

}
