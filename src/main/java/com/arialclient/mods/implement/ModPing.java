package com.arialclient.mods.implement;

import com.arialclient.gui.hud.ScreenPosition;
import com.arialclient.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;

public class ModPing extends ModDraggable {

    @Override
    public int getWidth()
    {
        return font.getStringWidth(" ms" +  Minecraft.getMinecraft().getNetHandler().getPlayerInfo(Minecraft.getMinecraft().thePlayer.getUniqueID()).getResponseTime());
    }

    @Override
    public int getHeight()
    {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        font.drawStringWithShadow(Minecraft.getMinecraft().getNetHandler().getPlayerInfo(Minecraft.getMinecraft().thePlayer.getUniqueID()).getResponseTime() + ("  ms"), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, -1);
    }

    @Override
    public int render (UnsupportedZipFeatureException.Feature feature, float x, float y) {
        return 0;
    }

}
