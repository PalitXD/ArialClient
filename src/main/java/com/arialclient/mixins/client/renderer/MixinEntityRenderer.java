package com.arialclient.mixins.client.renderer;

import com.arialclient.event.implement.RenderEvent;
import com.arialclient.mixins.client.multiplayer.MixinPlayerControllerMP;
import com.arialclient.mods.ModInstances;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.Callable;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer {

    private Minecraft mc = Minecraft.getMinecraft();

    @Inject(method = "updateCameraAndRender", at = @At("TAIL"))
    public void onRender(CallbackInfo ci) {
        new RenderEvent().call();
    }

    @Inject(method = "renderHand", at = @At("TAIL"))
    private void renderHand(float partialTicks, int xOffset, CallbackInfo ci) {
        if (ModInstances.getModOldAnimations().isEnabled()) {
            if (this.mc.thePlayer.getItemInUseCount() != 0 && this.mc.gameSettings.keyBindAttack.isKeyDown() && this.mc.gameSettings.keyBindUseItem.isKeyDown() && this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit.equals(MovingObjectPosition.MovingObjectType.BLOCK)) {
                this.swingItem(this.mc.thePlayer);
            }
            if (this.mc.gameSettings.keyBindAttack.isKeyDown() && this.mc.gameSettings.keyBindUseItem.isKeyDown() && this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit.equals(MovingObjectPosition.MovingObjectType.BLOCK)) {
                ((MixinPlayerControllerMP) this.mc.playerController).setIsHittingBlock(false);
            }
        }
    }

    private void swingItem(EntityLivingBase entity) {
        ItemStack stack = entity.getHeldItem();
        if (stack != null && stack.getItem() != null && (!entity.isSwingInProgress || entity.swingProgressInt >= this.getArmSwingAnimationEnd(entity) / 2 || entity.swingProgressInt < 0)) {
            entity.swingProgressInt = -1;
            entity.isSwingInProgress = true;
        }
    }

    private int getArmSwingAnimationEnd(EntityLivingBase e) {
        return e.isPotionActive(Potion.digSpeed) ? (6 - (1 + e.getActivePotionEffect(Potion.digSpeed).getAmplifier())) : (e.isPotionActive(Potion.digSlowdown) ? (6 + (1 + e.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2) : 6);
    }


    // Thanks Decencies

/**

    @Redirect(method = "updateCameraAndRender", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;inGameHasFocus:Z"))
    public boolean patchFocus(Minecraft minecraft) {
        return ModInstances.getModPerspective().overrideMouse();
    }

    @Redirect(method = "orientCamera", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;rotationYaw:F"))
    public float patchYaw(Entity entity) {
        return ModInstances.getModPerspective().getCameraYaw();
    }

    @Redirect(method = "orientCamera", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;rotationPitch:F"))
    public float patchPitch(Entity entity) {
        return ModInstances.getModPerspective().getCameraPitch();
    }

    @Redirect(method = "orientCamera",  at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;prevRotationYaw:F"))
    public float patchPrevYaw(Entity entity) {
        return ModInstances.getModPerspective().getCameraYaw();
    }

    @Redirect(method = "orientCamera", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;prevRotationPitch:F"))
    public float patchPrevPitch(Entity entity) {
        return ModInstances.getModPerspective().getCameraPitch();
    }
    **/

}
