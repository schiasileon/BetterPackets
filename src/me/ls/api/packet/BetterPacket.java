package me.ls.api.packet;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public interface BetterPacket {

    public String getType();

    public String[] getContent();

    public static String preparePacket(BetterPacket p){

        String content = "";

        if(p.getContent().length > 0) {
            for (String tile : p.getContent()) {
                content += Base64.encode(tile.getBytes()) + " ";
            }
            content = content.trim().replaceAll(" ", "#");
        }
        String contentEnc = Base64.encode(content.getBytes());

        String send = Base64.encode((p.getType()+"#"+contentEnc).getBytes());

        return send;
    }

}
