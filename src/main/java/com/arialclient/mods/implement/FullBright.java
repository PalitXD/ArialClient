package com.arialclient.mods.implement;

import com.arialclient.gui.hud.ScreenPosition;
import com.arialclient.mods.ModDraggable;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;

public class FullBright extends ModDraggable {
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
    public void Light(){
        mc.gameSettings.gammaSetting = 1000000;
    }

    @Override
    public int render (UnsupportedZipFeatureException.Feature feature, float x, float y) {
        return 0;
    }
}
