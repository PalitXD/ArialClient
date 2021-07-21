package com.arialclient.capes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CapeInstances {
    List<UUID> uuids = new ArrayList<>();
    List<UUID> devUUID = new ArrayList<>();

    public CapeInstances() {
        ArialCapes();
        devCapes();
    }

    public void ArialCapes() {
        try {
            URL capesList = new URL("https://pastebin.com/raw/ZVXtqZeR");
            BufferedReader in = new BufferedReader(new InputStreamReader(capesList.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                uuids.add(UUID.fromString(inputLine));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void devCapes() {
        try {
            URL devList = new URL("https://pastebin.com/raw/T6cm1rra");
            BufferedReader in = new BufferedReader(new InputStreamReader(devList.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                devUUID.add(UUID.fromString(inputLine));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean hasCape(UUID id) {
        return uuids.contains(id);
    }

    public boolean hasDevCape(UUID id) {
        return devUUID.contains(id);
    }

}




