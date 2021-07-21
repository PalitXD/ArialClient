package com.arialclient.gui.clickgui;

import com.arialclient.ArialClient;
import com.arialclient.gui.hud.HudManager;
import com.arialclient.gui.hud.IRenderer;
import com.arialclient.gui.hud.ScreenPosition;
import com.arialclient.mods.Mod;
import com.arialclient.utils.DrawUtils;
import com.google.common.reflect.ClassPath;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class  ClickGUI extends GuiScreen {

    private final HashMap<IRenderer, ScreenPosition> renderers = new HashMap<IRenderer, ScreenPosition>();
    /*    private final Optional<IRenderer> selectedRenderer = Optional.empty();*/
    private final Collection<IRenderer> registeredRenderers;

    public ClickGUI(HudManager api) {
        registeredRenderers = api.getRegisteredRenderers();
    }
    ArrayList<ClickGuiButton> ClickButton = new ArrayList<>();


public int GuiSize;
    @Override
    public void initGui() {



        int xAdd = 0;
        int yAdd = 0;

        for (int i = 0; i < registeredRenderers.toArray().length; i++) {
            int j = i % 5;
            if (j == 0 && i != 0) yAdd += 120;
            if (j == 1 || j == 2 || j == 3 || j == 4) xAdd += (120 * j);

            ClassLoader loader = ClassLoader.getSystemClassLoader();
            Collection<ClassPath.ClassInfo> classInfos = null;
            try {
                classInfos = ClassPath.from(loader).getTopLevelClassesRecursive("com.arialclient.mods.implement");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int xpos = 8;
        int ypos = 5;
        int ModuleIndex = 0;
        for (IRenderer m : ArialClient.INSTANCE.getHudManager().getRegisteredRenderers()){


            //buttonList.add(new ClickGuiButton(20 + xpos*140, 190 + ypos*120, m.getClass().getName().replace("com.arialclient.mods.implement.Mod", "lol")));
                ClickButton.add(new ClickGuiButton(xpos, ypos, 60, 60, m.getClass().getName().replace("com.arialclient.mods.implement.Mod", ""), m));
            xpos+=5;
            if(xpos == 23){
                ypos+=7;
                xpos = 8;
            }

        }
        super.initGui();
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        for(ClickGuiButton b : ClickButton){
            b.Render(mouseX, mouseY);
        }
        ScaledResolution lr = new ScaledResolution(Minecraft.getMinecraft());
        float xb = (lr.getScaledWidth()/40) * 7;
        float yb = (lr.getScaledHeight()/40) * 1;
        float widthb = (lr.getScaledWidth()/40) * 39;
        float heightb = (lr.getScaledHeight()/40)* 40;
        float widthsb = (lr.getScaledWidth()/40) * 6;
        DrawUtils.drawRoundedOutlineFloat(xb, yb ,widthb ,heightb , 6, 5, ArialClient.MAIN_COLOR);
        DrawUtils.drawRoundedOutlineFloat(yb, yb ,widthsb,heightb , 6, 5, ArialClient.MAIN_COLOR);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }


    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException{
        super.mouseClicked(mouseX,mouseY, mouseButton);
        for(ClickGuiButton b : ClickButton){
            b.OnClick(mouseX, mouseY, mouseButton);
        }
    }
}
