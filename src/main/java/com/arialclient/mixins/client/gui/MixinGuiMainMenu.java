package com.arialclient.mixins.client.gui;

import com.arialclient.ArialClient;
import com.arialclient.ui.utils.GuiUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(GuiMainMenu.class)
public abstract class MixinGuiMainMenu extends GuiScreen {

    @Inject(method = "addSingleplayerMultiplayerButtons", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 1, shift = At.Shift.AFTER), cancellable = true)
    private void removeRealmsButton(int p_73969_1_, int p_73969_2_, CallbackInfo ci) {
        ci.cancel();
        this.buttonList.add(new GuiButton(99, this.width / 2 - 75, p_73969_1_ + p_73969_2_ * 2, 150, 20, "Replay   Viewer"));
    }

    @Redirect (method = "initGui", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 1))
    private boolean moveQuitButton(List<GuiButton> list, Object e) {
        final GuiButton quit = (GuiButton) e;
        this.buttonList.add(new GuiButton(quit.id, quit.xPosition + 10, quit.yPosition, 89, 20, "Quit Game"));
        return false;
    }
    @ModifyArg(method = "initGui", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 3))
    private Object moveOptionsButton(Object buttonIn) {
        GuiButton guiButton = (GuiButton) buttonIn;
        guiButton.xPosition += 240;
        guiButton.setWidth(87);
        return guiButton;
    }

    @ModifyArg(method = "initGui", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 2))
    private Object removeLanguageButtons(Object buttonIn) {
        final GuiButton guiButton = (GuiButton) buttonIn;
        guiButton.xPosition += 2000;
        return guiButton;
    }

    @ModifyArg(method = "addSingleplayerMultiplayerButtons", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
    private Object decreaseButtonWidth(Object button) {
        GuiButton btn = (GuiButton)button;
        btn.setWidth(btn.getButtonWidth() - 50);
        btn.xPosition += 25;
        return button;
    }

    /**
     * @reason Overwrite drawScreen() to implement a custom main menu design.
     * @author Kaimson -- Boncorde loves hanime
     */
    @Overwrite
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        // Draw background
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(ArialClient.BACKGROUND);
        GuiUtils.drawModalRectWithCustomSizedTexture(0, 0, width, height, width, height, width, height);
        GlStateManager.popMatrix();
        // Draw text in bottom left
        ArialClient.typographica.drawString("Arial Client " + ArialClient.MC_VERSION, 3, this.height - 14, -1);
        // Draw copyright notice
        String text = "Not affiliated with Mojang Studios.";
        ArialClient.typographica.drawString(text, width - ArialClient.typographica.getWidth(text) - 3, height - 14, -1);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}
