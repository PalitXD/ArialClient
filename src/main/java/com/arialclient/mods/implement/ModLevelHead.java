package com.arialclient.mods.implement;

import com.arialclient.gui.hud.ScreenPosition;
import com.arialclient.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;

public class ModLevelHead extends ModDraggable {


    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void renderDummy(ScreenPosition pos) {

    }

    public void renderTag(FontRenderer fontrenderer) {

        String str = Minecraft.getMinecraft().getSession().getUsername() == "ISimpWetzkie" ? "Developerr" : null;

        fontrenderer.drawString(str, -fontrenderer.getStringWidth(str) / 2, -8, -1);
    }

    @Override
    public int render (UnsupportedZipFeatureException.Feature feature, float x, float y) {
        return 0;
    }
}
