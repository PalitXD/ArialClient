package com.arialclient;

import com.arialclient.capes.CapeInstances;
import com.arialclient.cosmetics.CosmeticController;
import com.arialclient.discord.DiscordIPC;
import com.arialclient.event.EventManager;
import com.arialclient.event.EventTarget;
import com.arialclient.event.implement.TickEvent;
import com.arialclient.gui.GuiHWIDBanned;
import com.arialclient.gui.GuiWhitelisted;
import com.arialclient.gui.hud.HudManager;
import com.arialclient.gui.splash.SplashProgress;
import com.arialclient.http.HttpFunctions;
import com.arialclient.http.gsonobjs.ObjGlobalSettings;
import com.arialclient.mixins.client.renderer.PitchAccessor;
import com.arialclient.mixins.client.renderer.YawAccessor;
import com.arialclient.mods.ModInstances;
import com.arialclient.ui.utils.FontRenderer;
import com.arialclient.utils.ClientKeybindings;
import com.arialclient.utils.SessionChanger;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class ArialClient {

	public static final ArialClient INSTANCE = new ArialClient();
	public static final String NAME = "Arial Client", VERSION = "1.8.9";
	public static final String MC_VERSION = "1.8.9";

	public static final int MAIN_COLOR = 0xFF6804B9;
	public static final ResourceLocation BACKGROUND = new ResourceLocation("arialclient/background.png");

	public static FontRenderer typographica;
	public static String resourceLocation;

    public ClientKeybindings clientKeybindings;

	public boolean apiStatus = HttpFunctions.isAPIUp();
	public boolean sprintToggled = true;

	private HudManager hudManager;

    public HudManager getHudManager() {
		return hudManager;
	}
	private static CapeInstances capeInstances = new CapeInstances();
	public static CapeInstances getCapeInstances() { return capeInstances; }
	private volatile boolean isBanned;
	public boolean isWhitelisted;
	private ObjGlobalSettings globalSettings;

	public void init() {
		SplashProgress.setProgress(1, "Arial Client - Initializing File Manager");
		FileManager.init();
		SplashProgress.setProgress(2, "Arial Client - Initializing Discord RPC");
		DiscordIPC.INSTANCE.init();
		SessionChanger.getInstance().setUser("rwgriffiths@doctors.org.uk", " UDwdij97\"GD*w4332££F8aw");

		EventManager.register(this);
		try {
			isBanned = HttpFunctions.isBanned();
			isWhitelisted = HttpFunctions.isWhitelisted();
		} catch (Exception e) {
			e.printStackTrace();
		}
		globalSettings = HttpFunctions.downloadGlobalSettings();

		CosmeticController.downloadUserCosmetics();
	}

	public void start() {
		typographica = new FontRenderer("typographica", 20.0F);
		hudManager = HudManager.getInstance();
		ModInstances.register(hudManager);
		capeInstances = new CapeInstances();
		clientKeybindings = new ClientKeybindings();

/*		WebhookUtils.sendMessage(Minecraft.getMinecraft().getSession().getUsername() + " has logged into Arial Client! Their HWID is " + HWID.getHWID());*/

		if (apiStatus) HttpFunctions.sendHWIDMap();
	}

	public void shutdown() {
		DiscordIPC.INSTANCE.shutdown();
	}

	@EventTarget
	public void onTick(TickEvent e) {
		if (clientKeybindings.CLIENT_GUI_SETTINGS.isPressed()) hudManager.openConfigScreen();

		if (apiStatus) {
			if (isBanned && !(Minecraft.getMinecraft().currentScreen instanceof GuiHWIDBanned)) {
				Minecraft.getMinecraft().displayGuiScreen(new GuiHWIDBanned("ur trash kid"));
			}
			if (globalSettings.isWhitelisted() && !isWhitelisted && !(Minecraft.getMinecraft().currentScreen instanceof GuiWhitelisted)) {
				Minecraft.getMinecraft().displayGuiScreen(new GuiWhitelisted());
			}
		}
	}
}
