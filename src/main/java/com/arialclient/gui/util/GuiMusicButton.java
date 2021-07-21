package com.arialclient.gui.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiMusicButton extends GuiButton {

	public GuiMusicButton(int buttonId, int x, int y) {
		super(buttonId, x, y, 200, 200, "♫");
	}
}
