package com.arialclient.mods;

import com.arialclient.gui.hud.HudManager;
import com.arialclient.mods.implement.*;

public class ModInstances {
	
	private static ModFPS modFPS;
	private static ModToggleSprint modToggleSprint;
	private static ModCPS modCPS;
	private static ModCoords modCoords;
	private static ModOldAnimations modOldAnimations;
	private static ModKeystrokes modKeystrokes;
	private static ModArmorStatus modArmorStatus;
	private static ModPotionStatus modPotionStatus;
	private static ModAutoGG modAutoGG;
	private static ModPing modPing;
	private static FullBright fullBright;



	public static void register(HudManager api) {
		modFPS = new ModFPS();
		modToggleSprint = new ModToggleSprint();
		modCPS = new ModCPS();
		modCoords = new ModCoords();
		modOldAnimations = new ModOldAnimations();
		modKeystrokes = new ModKeystrokes();
		modArmorStatus = new ModArmorStatus();
		modPotionStatus = new ModPotionStatus();
		modAutoGG = new ModAutoGG();
		modPing = new ModPing();
		fullBright = new FullBright();

		api.register(modFPS, modToggleSprint, modCPS, modCoords, modOldAnimations, modKeystrokes, modArmorStatus, modPotionStatus, modAutoGG, modPing);
	}
	
	public static ModFPS getModFPS() {
		return modFPS;
	}

	public static ModToggleSprint getModToggleSprint() {
		return modToggleSprint;
	}

	public static ModAutoGG getModAutoGG() {
		return modAutoGG;
	}

	public static ModOldAnimations getModOldAnimations() {
		return modOldAnimations;
	}
}
