package com.arialclient.mixins.client.gui;

import com.arialclient.ArialClient;
import com.arialclient.ui.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiButton.class)
public abstract class MixinGuiButton {

    @Shadow
    public int xPosition;
    @Shadow
    public int yPosition;
    @Shadow
    protected int width;
    @Shadow
    protected int height;

    @Shadow
    public boolean visible;
    @Shadow
    public boolean enabled;
    @Shadow
    protected boolean hovered;

    @Shadow
    protected abstract void mouseDragged(Minecraft mc, int mouseX, int mouseY);

    @Shadow public String displayString;

    /**
     * @reason Overwrites the {@link GuiButton}.drawButton method to make use of our custom design.
     * @author Kaimson
     */
    @Overwrite
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (visible) {
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            GuiUtils.drawRoundedOutline(xPosition, yPosition, xPosition + width, yPosition + height, 2.0F, 2.0F, ArialClient.MAIN_COLOR);
            if (hovered)
                GuiUtils.drawRoundedRect(xPosition, yPosition, xPosition + width, yPosition + height, 2.0F, GuiUtils.getColor(ArialClient.MAIN_COLOR, 100));
            ArialClient.typographica.drawCenteredString(displayString, xPosition + width / 2, yPosition + (height - 17), 0xFFFFFF);
            this.mouseDragged(mc, mouseX, mouseY);
        }
    }

}

