package com.arialclient.mods.implement;

import com.arialclient.gui.hud.ScreenPosition;
import com.arialclient.mods.ModDraggable;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;
import java.util.List;

public class ModCPS extends ModDraggable {

	private List<Long> leftClicks = new ArrayList<Long>();
	private List<Long> rightClicks = new ArrayList<Long>();
	private boolean leftWasPressed;
	private boolean rightWasPressed;
    private long leftLastPress;
    private long rightLastPress;
	
	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public int getWidth() {
		return font.getStringWidth("[CPS: " + this.getLeftCPS() + " | " + this.getRightCPS() + " 0]");
	}

	@Override
	public void render(ScreenPosition pos) {
		super.render(pos);
		final boolean leftPressed = Mouse.isButtonDown(0);
		final boolean rightPressed = Mouse.isButtonDown(1);
        if (leftPressed != this.leftWasPressed) {
            this.leftWasPressed = leftPressed;
            this.leftLastPress = System.currentTimeMillis();
            if (leftPressed) {
                this.leftClicks.add(this.leftLastPress);
            }
        }
        final int leftCps = this.getLeftCPS();
        if (rightPressed != this.rightWasPressed) {
            this.rightWasPressed = rightPressed;
            this.rightLastPress = System.currentTimeMillis();
            if (rightPressed) {
                this.rightClicks.add(this.rightLastPress);
            }
        }
        final int rightCps = this.getRightCPS();
        font.drawStringWithShadow("[CPS: " + leftCps + " | " + rightCps + "]", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
	}

    @Override
    public int render (UnsupportedZipFeatureException.Feature feature, float x, float y) {
        return 0;
    }

    private int getLeftCPS() {
        final long time = System.currentTimeMillis();
        this.leftClicks.removeIf(aLong -> aLong + 1000L < time);
        return this.leftClicks.size();
    }
	
	private int getRightCPS() {
        final long time = System.currentTimeMillis();
        this.rightClicks.removeIf(aLong -> aLong + 1000L < time);
        return this.rightClicks.size();
    }

}