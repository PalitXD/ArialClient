package com.arialclient.event.implement;

import com.arialclient.event.Event;
import net.minecraft.util.IChatComponent;

public class EventChatRecieved extends Event {

    IChatComponent chatComponent;

    public EventChatRecieved(IChatComponent chatComponent) {
        this.chatComponent = chatComponent;
    }

    public IChatComponent getChatComponent() {
        return chatComponent;
    }
}
