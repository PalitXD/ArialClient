package com.arialclient.mods;

import com.arialclient.FileManager;
import com.arialclient.gui.hud.IRenderer;
import com.arialclient.gui.hud.ScreenPosition;
import com.arialclient.settings.Setting;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;

import java.io.File;
import java.util.ArrayList;

public abstract class ModDraggable extends Mod implements IRenderer {
	
	protected ScreenPosition pos;
	public ArrayList<Setting> settingsList = new ArrayList<Setting>();
	
	public ModDraggable() {
		pos = loadPositionFromFile();
	}

	@Override
	public ScreenPosition load() {
		return pos;
	}
	
	@Override
	public void save(ScreenPosition pos) {
		this.pos = pos;
		savePositionToFile();
	}
	
	@Override
	public void render(ScreenPosition pos) {
		//Gui.drawRect(pos.getAbsoluteX() - 5, pos.getAbsoluteY() - 5, pos.getAbsoluteX() + getWidth() - 7, pos.getAbsoluteY() + getHeight() + 3, 0x8A000000);
	}
	
	private File getFolder() {
		File folder = new File(FileManager.getModsDir(), this.getClass().getSimpleName());
		folder.mkdirs();
		return folder;
	}

	private void savePositionToFile() {
		FileManager.writeJsonToFile(new File(getFolder(), "pos.json"), pos);
	}
	
	private ScreenPosition loadPositionFromFile() {
		
		ScreenPosition loaded = FileManager.readJsonFromFile(new File(getFolder(), "pos.json"), ScreenPosition.class);
		
		if (loaded == null) {
			loaded = ScreenPosition.fromRelativePosition(0.5, 0.5);
			this.pos = loaded;
			savePositionToFile();
		}
		
		return loaded;
	}

	public final int getLineOffset(ScreenPosition pos, int lineNum) {
		return pos.getAbsoluteY() + getLineOffset(lineNum);
	}

	private int getLineOffset(int lineNum) {
		return (font.FONT_HEIGHT + 3) * lineNum;
	}

    public abstract int render (UnsupportedZipFeatureException.Feature feature, float x, float y);
}
