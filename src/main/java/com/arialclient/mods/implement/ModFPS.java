package com.arialclient.mods.implement;

import com.arialclient.gui.hud.ScreenPosition;
import com.arialclient.mods.ModDraggable;

import net.minecraft.client.Minecraft;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;

public class ModFPS extends ModDraggable {
	@Override
	public int getWidth() {
		return font.getStringWidth("[FPS: 120]");
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawString("[FPS: 120]", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
	}
	
	@Override
	public void render(ScreenPosition pos) {
		//if (Minecraft.getDebugFPS() > 1000) Gui.drawRect(pos.getAbsoluteX() - 5, pos.getAbsoluteY() - 5, pos.getAbsoluteX() + getWidth() + 4, pos.getAbsoluteY() + getHeight() + 3, 0x8A000000);
		//else {Gui.drawRect(pos.getAbsoluteX() - 5, pos.getAbsoluteY() - 5, pos.getAbsoluteX() + getWidth() - 2, pos.getAbsoluteY() + getHeight() + 3, 0x8A000000);}
		font.drawStringWithShadow("[FPS: " + Minecraft.getDebugFPS() + "]", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
	}

	@Override
	public int render (UnsupportedZipFeatureException.Feature feature, float x, float y) {
		return 0;
	}
}
