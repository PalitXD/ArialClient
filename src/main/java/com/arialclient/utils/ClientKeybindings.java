package com.arialclient.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.input.Keyboard;

public class ClientKeybindings {
    Minecraft mc = Minecraft.getMinecraft();
    public KeyBinding CLIENT_GUI_SETTINGS = new KeyBinding("Mod Positioning", Keyboard.KEY_RSHIFT, "Arial Client");
    public KeyBinding CLIENT_PERSPECTIVE = new KeyBinding("Perspective", Keyboard.KEY_LMENU, "Arial Client");

    public ClientKeybindings() {
        mc.gameSettings.keyBindings = ((KeyBinding[])ArrayUtils.add(Minecraft.getMinecraft().gameSettings.keyBindings, CLIENT_GUI_SETTINGS));
        mc.gameSettings.keyBindings = ((KeyBinding[])ArrayUtils.add(Minecraft.getMinecraft().gameSettings.keyBindings, CLIENT_PERSPECTIVE));
    }

}
