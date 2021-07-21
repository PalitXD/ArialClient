package com.arialclient.gui.buttons;

import com.arialclient.gui.splash.UnicodeFontRenderer;
import com.arialclient.utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiButtonMainMenu extends GuiButton {

    private final UnicodeFontRenderer fontrenderer = UnicodeFontRenderer.getFontFromAssets("TypoGraphica", 28);

    public GuiButtonMainMenu(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, buttonText);
    }

    public GuiButtonMainMenu(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            DrawUtils.drawRoundedOutline(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 3, 2, 0xFF6804B9);

            if (this.hovered) {
                DrawUtils.drawRoundedRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 3, 0xFF6804B9);
            }

            fontrenderer.drawCenteredString(this.displayString, this.xPosition + this.width / 2, (this.yPosition + (this.height - 8) / 2) - 6, 0xFFFFFFFF);
        }

        //System.out.println(fontrenderer.getFont().toString());
    }
}
