package com.arialclient.mixins.client.gui;

import com.arialclient.event.implement.EventChatRecieved;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiNewChat.class)
public class MixinGuiNewChat {
    @Inject(method = "printChatMessage", at = @At("TAIL"))
    public void mixin(IChatComponent chatComponent, CallbackInfo ci) {
        new EventChatRecieved(chatComponent).call();
    }
}
