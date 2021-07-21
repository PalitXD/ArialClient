package com.arialclient.gui.clickgui;

import com.arialclient.ArialClient;
import com.arialclient.gui.hud.IRenderer;
import com.arialclient.mods.Mod;
import com.arialclient.ui.utils.GuiUtils;
import com.arialclient.utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.omg.CORBA.ARG_IN;

import javax.sound.sampled.Line;
import java.awt.*;

public class ClickGuiButton {

    private int x1;
    private int y1;

    private int width;
    private int height;

    private String Name;
    private IRenderer m;

    public float x2;
    public float width2;
    public float y2;
    public float height2;
    public float height3;
    public double Scale;
    public double SH;
    public float LineWidth;
    public float Curve;
    public int offset;
    public float offset1 = 0;
    public float offset2 = 0;
    public ClickGuiButton(int x, int y, int width, int height, String name, IRenderer m) {
        this.x1 = x;
        this.y1 = y;
        this.width = width;
        this.height = height;
        this.Name = name;
        this.m = m;

    }

    public void Render(int mouseX, int mouseY) {
        int Color1 = GuiUtils.getColor(ArialClient.MAIN_COLOR, 100);
        if(m.isEnabled() == true){
            Color1 = ArialClient.MAIN_COLOR;
        }else{
            Color1 = Color.gray.getRGB();
        }

        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution lr = new ScaledResolution(Minecraft.getMinecraft());
        x2 = lr.getScaledWidth()/25 * (x1);
        width2 = lr.getScaledWidth()/25 * (x1 + 3);
        y2 =  (lr.getScaledWidth()/25 * (y1 - 1) *  0.78F);
        height2 =  y2 + (width2 - x2);
        height3 = height2 - ((width2 - x2)/3);//Maths by Boncorde :)
        Scale =  ((SH/514) * 1.12D);
        LineWidth = (float) ((SH/514) * 8);
        Curve = (float) ((SH/514) * 8);

        if(mouseX >= x2 && mouseX <= width2 && mouseY >= height3 && mouseY <= height2) {
          if(offset < (SH/514) * 20){
              offset++;
              System.out.println(offset);
          }
        }else {
            if(offset > (SH/514) * 1){
                offset--;
            }
        }
        SH = lr.getScaledHeight();
        ArialClient.typographica.drawStringScaled(Name,(int) x2,(int) height3, Color.black.getRGB(), Scale);

        DrawUtils.drawRoundedOutlineFloat(x2 - offset, height3 - offset, width2 + offset, height2 + offset, Curve, LineWidth, Color1);
        DrawUtils.drawRoundedOutlineFloat( x2 - offset, y2 - offset, width2 + offset, height2 + offset, Curve, LineWidth, Color1);


    }

    public void OnClick(int mouseX, int mouseY, int Button) {
        if(mouseX >= this.x2 && mouseX <= this.width2 && mouseY >= this.height3 && mouseY <= this.height2) {
            if (Button == 0) {

                if (m instanceof Mod) {
                    Mod m2 = (Mod) m;
                    if (m2.isEnabled()) {
                        m2.setEnabled(false);
                    } else {
                        m2.setEnabled(true);
                    }
                }
            }
        }
    }
}


