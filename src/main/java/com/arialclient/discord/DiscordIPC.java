package com.arialclient.discord;

import com.arialclient.ArialClient;
import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.RichPresence;
import com.jagrosh.discordipc.entities.pipe.PipeStatus;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;
import org.json.JSONObject;

import java.time.OffsetDateTime;

public class DiscordIPC implements IPCListener {

    public static final DiscordIPC INSTANCE = new DiscordIPC();
    private IPCClient client;

    public void init() {
        client = new IPCClient(723935841134837801L);
        client.setListener(this);
        try {
            client.connect();
        } catch (NoDiscordClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
/*            Client.error("UNKOWN ERROR");*/
            e.printStackTrace();
        }
/*        Client.info("IPC {} -> {}", client.getStatus(), client.getDiscordBuild());*/
    }

    public void shutdown() {
        if (client != null && client.getStatus() == PipeStatus.CONNECTED) {
            client.close();
/*            Client.info("Discord IPC closed!");*/
        }
    }

    @Override
    public void onReady(IPCClient client) {
        RichPresence.Builder builder = new RichPresence.Builder()
                .setDetails("Playing Minecraft " + ArialClient.MC_VERSION)
                .setLargeImage("large", "Arial Client " + ArialClient.MC_VERSION)
                .setStartTimestamp(OffsetDateTime.now());
        client.sendRichPresence(builder.build());
    }

    public void update(String state, String details) {
        RichPresence.Builder builder = new RichPresence.Builder()
                .setState(state)
                .setDetails(details);
        client.sendRichPresence(builder.build());
    }

    @Override
    public void onClose(IPCClient client, JSONObject json) {

    }

    @Override
    public void onDisconnect(IPCClient client, Throwable t) {

    }

}
