package com.arialclient.mods.implement;

import com.arialclient.ArialClient;
import com.arialclient.gui.hud.ScreenPosition;
import com.arialclient.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;

import java.text.DecimalFormat;

public class ModToggleSprint extends ModDraggable {
	
    //Settings below
    public boolean flyBoost = true;
    public float flyBoostFactor = 10;
    public int keyHoldTicks = 7;
    public boolean shiftToggled = false;
    
    @Override
    public int getWidth() {
        return font.getStringWidth("[Sprinting (Toggled)]");
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        String textToRender = getDisplayText();
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), true);
        font.drawStringWithShadow(textToRender, pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    @Override
    public int render (UnsupportedZipFeatureException.Feature feature, float x, float y) {
        return 0;
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
    	//Gui.drawRect(pos.getAbsoluteX() - 5, pos.getAbsoluteY() - 5, pos.getAbsoluteX() + getWidth() + 5, pos.getAbsoluteY() + getHeight() + 3, 0x8A000000);
        String textToRenderDummy = "[Sprinting (Toggled)]";
        font.drawStringWithShadow(textToRenderDummy, pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    private static final DecimalFormat df = new DecimalFormat("#.0");
    private float originalFlySpeed = -1.0F;
    private float boostedFlySpeed = 1;

    public String getDisplayText() {

        String displayText = "";

        boolean isFlying = mc.thePlayer.capabilities.isFlying;
        boolean isRiding = mc.thePlayer.isRiding();
        boolean isHoldingSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown();
        boolean isHoldingSprint = Minecraft.getMinecraft().gameSettings.keyBindSprint.isKeyDown();

        if( isFlying) {
            if (originalFlySpeed > 0.0F) {
                displayText += "[Flying (" + df.format(boostedFlySpeed / originalFlySpeed) + "x Boost)]";
            } else {
                displayText += "[Flying]";
            }
        }

        if (isRiding) {
            displayText += "[Riding]  ";
        }


        else if (ArialClient.INSTANCE.sprintToggled && !isFlying && !isRiding ) {
            if (isHoldingSprint) {
                displayText += "[Sprinting (Toggled)]";
            } else {
                displayText += "[Sprinting (Toggled)]";
            }
        }
        return displayText.trim();
    }
}
