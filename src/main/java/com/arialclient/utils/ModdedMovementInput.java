package com.arialclient.utils;

import com.arialclient.ArialClient;
import com.arialclient.mods.ModInstances;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.potion.Potion;
import net.minecraft.util.MovementInput;

import java.text.DecimalFormat;

public class ModdedMovementInput extends MovementInput {

	private final GameSettings gameSettings;
	private int sneakWasPressed = 0;
	private int sprintWasPressed = 0;
	private float originalFlySpeed = -1.0F;
	private float boostedFlySpeed = 1;
	private Minecraft mc;
	
	public ModdedMovementInput(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
		this.mc = Minecraft.getMinecraft();
		
	}
	@Override
	public void updatePlayerMoveState() {

		EntityPlayerSP player = mc.thePlayer;
		moveStrafe = 0.0F;
		moveForward = 0.0F;

		if (gameSettings.keyBindForward.isKeyDown()) {
			moveForward++;
		}

		if (gameSettings.keyBindBack.isKeyDown()) {
			moveForward--;
		}

		if (gameSettings.keyBindLeft.isKeyDown()) {
			moveStrafe++;
		}

		if (gameSettings.keyBindRight.isKeyDown()) {
			moveStrafe--;
		}

		jump = gameSettings.keyBindJump.isKeyDown();

		sneak = gameSettings.keyBindSneak.isKeyDown();


		if (sneak) {
			moveStrafe *= 0.3F;
			moveForward *= 0.3F;
		}

	}
	
	private static final DecimalFormat df = new DecimalFormat("#.0");
	public String getDisplayText() {
		
		String displayText = "";
		
		boolean isFlying = mc.thePlayer.capabilities.isFlying;
		boolean isRiding = mc.thePlayer.isRiding();
		boolean isHoldingSneak = gameSettings.keyBindSneak.isKeyDown();
		boolean isHoldingSprint = gameSettings.keyBindSprint.isKeyDown();
		
		if(isFlying) {
			if(originalFlySpeed > 0.0F) {
				displayText += "[Flying (" + df.format(boostedFlySpeed / originalFlySpeed) + "x Boost)]";
			} else {
				displayText += "[Flying]";
			}
		}
		
		if(isRiding) {
			displayText += "[Riding]  ";
		} 
		
		
		else if(ArialClient.INSTANCE.sprintToggled && !isFlying && !isRiding ) {
			if(isHoldingSprint) {
				displayText += "[Sprinting (Key Held)]";
			} else {
				displayText += "[Sprinting (Key Toggled)]";
			}
		}
		
		
		return displayText.trim();
		
	}
	
}