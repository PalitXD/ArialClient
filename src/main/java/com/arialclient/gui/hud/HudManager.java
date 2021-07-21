package com.arialclient.gui.hud;

import com.arialclient.event.EventManager;
import com.arialclient.event.EventTarget;
import com.arialclient.event.implement.RenderEvent;
import com.google.common.collect.Sets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiContainer;

import java.util.Collection;
import java.util.Set;

public class HudManager {
	
	private HudManager() {}
	
	private static HudManager instance = null;
	
	public static HudManager getInstance() {
		
		if (instance != null) {
			return instance;
		}
		
		instance = new HudManager();
		EventManager.register(instance);
		
		return instance;
		
	}
	
	private Set<IRenderer> registeredRenderers = Sets.newHashSet();
	private Minecraft mc = Minecraft.getMinecraft();
	
	public void register(IRenderer... renderers) {
		for(IRenderer render : renderers) {
			this.registeredRenderers.add(render);
		}
	}
	
	public void unregister(IRenderer... renderers) {
		for(IRenderer render : renderers) {
			this.registeredRenderers.remove(render);
		}
	}
	
	public Collection<IRenderer> getRegisteredRenderers() {
		return Sets.newHashSet(registeredRenderers);
	}
	
	public void openConfigScreen() {
		mc.displayGuiScreen(new HudConfigScreen(this));
	}
	
	@EventTarget
	public void onRender(RenderEvent e) {
		if (mc.currentScreen == null || mc.currentScreen instanceof GuiContainer || mc.currentScreen instanceof GuiChat) {
			if (mc.gameSettings.showDebugInfo) return;
			for(IRenderer renderer : registeredRenderers) {
				callRenderer(renderer);
			}			
		}
	}

	private void callRenderer(IRenderer renderer) {
		if(!renderer.isEnabled()) return;
		
		ScreenPosition pos = renderer.load();
		
		if (pos == null) {
			pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
		}
		
		renderer.render(pos);
	}
	
}
