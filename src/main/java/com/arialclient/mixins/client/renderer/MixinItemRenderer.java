package com.arialclient.mixins.client.renderer;

import com.arialclient.mods.ModInstances;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer {
    @Shadow
    @Final
    private Minecraft mc;
    private float swingProgress;

    @Inject(method = "renderItemInFirstPerson", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItemUseAction()Lnet/minecraft/item/EnumAction;", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void modifySwingProgress(float partialTicks, CallbackInfo ci, float f, AbstractClientPlayer player, float f1, float f2, float f3) {
        this.swingProgress = f1;
    }

    @Inject(method = "renderItemInFirstPerson", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemRenderer;doBlockTransformations()V", shift = At.Shift.AFTER))
    private void modifySwing(float partialTicks, CallbackInfo ci) {
        if (ModInstances.getModOldAnimations().isEnabled()) { // block hit
            GlStateManager.scale(0.83f, 0.88f, 0.85f);
            GlStateManager.translate(-0.3f, 0.1f, 0.0f);
        }
    }

    @Inject(method = "renderItemInFirstPerson", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemRenderer;transformFirstPersonItem(FF)V", ordinal = 1, shift = At.Shift.AFTER))
    private void modifyEat(float partialTicks, CallbackInfo ci) {
        if (ModInstances.getModOldAnimations().isEnabled()) { // eating
            GlStateManager.scale(0.8f, 1.0f, 1.0f);
            GlStateManager.translate(-0.2f, -0.1f, 0.0f);
        }
    }

    @ModifyArg(method = "renderItemInFirstPerson", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemRenderer;transformFirstPersonItem(FF)V", ordinal = 1), index = 1)
    private float drinkSwingProgress(float swingProgress) {
        return ModInstances.getModOldAnimations().isEnabled() ? this.swingProgress : 0.0f; //eating
    }

    @ModifyArg(method = "renderItemInFirstPerson", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemRenderer;transformFirstPersonItem(FF)V", ordinal = 2), index = 1)
    private float blockSwingProgress(float swingProgress) {
        return ModInstances.getModOldAnimations().isEnabled() ? this.swingProgress : 0.0f; // block hit
    }

    @ModifyArg(method = "renderItemInFirstPerson", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemRenderer;transformFirstPersonItem(FF)V", ordinal = 3), index = 1)
    private float bowSwingProgress(float swingProgress) {
        return ModInstances.getModOldAnimations().isEnabled() ? this.swingProgress : 0.0f; // bow
    }

    @Inject(method = "transformFirstPersonItem", at = {@At("HEAD")})
    private void transformFirstPersonItem(float equipProgress, float swingProgress, CallbackInfo ci) {
        if (ModInstances.getModOldAnimations().isEnabled() && this.mc != null && this.mc.thePlayer != null && this.mc.thePlayer.getItemInUse() != null && this.mc.thePlayer.getItemInUse().getItem() != null && Item.getIdFromItem(this.mc.thePlayer.getItemInUse().getItem()) == 261) { // bow
            GlStateManager.translate(-0.01f, 0.05f, -0.06f);
        }
        if (ModInstances.getModOldAnimations().isEnabled() && this.mc != null && this.mc.thePlayer != null && this.mc.thePlayer.getCurrentEquippedItem() != null && this.mc.thePlayer.getCurrentEquippedItem().getItem() != null && (Item.getIdFromItem(this.mc.thePlayer.getCurrentEquippedItem().getItem()) == 346 || Item.getIdFromItem(this.mc.thePlayer.getCurrentEquippedItem().getItem()) == 398)) { // rod
            GlStateManager.translate(0.08f, -0.027f, -0.33f);
            GlStateManager.scale(0.93f, 1.0f, 1.0f);
        }
        if (ModInstances.getModOldAnimations().isEnabled() && this.mc != null && this.mc.thePlayer != null && this.mc.thePlayer.isSwingInProgress  && this.mc.thePlayer.getCurrentEquippedItem() != null && !this.mc.thePlayer.isEating() && !this.mc.thePlayer.isBlocking()) { // swing
            GlStateManager.scale(0.85f, 0.85f, 0.85f);
            GlStateManager.translate(-0.078f, 0.003f, 0.05f);
        }
    }
}
