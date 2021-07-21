package com.arialclient.gui.clickgui;

import com.arialclient.utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class ClickGuiFrame extends GuiButton {

    public ClickGuiFrame(int buttonId, int x, int y, String  mod) {
        super(buttonId, x, y, 100, 100, mod);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible)
        {

            FontRenderer fontrenderer = mc.fontRendererObj;
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height + 10;
            int i = this.getHoverState(this.hovered);

            DrawUtils.drawRoundedOutline(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 5, 6, -1);
            this.mouseDragged(mc, mouseX, mouseY);

        }
    }
}
