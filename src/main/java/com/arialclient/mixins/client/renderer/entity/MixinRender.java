package com.arialclient.mixins.client.renderer.entity;

import com.arialclient.mods.ModInstances;
import com.arialclient.mods.implement.ModLevelHead;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.Render;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Render.class)
public abstract class MixinRender {

    //@Shadow abstract public FontRenderer getFontRendererFromRenderManager();

    //@Inject(method = "renderLivingLabel", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/gui/FontRenderer;drawString(Ljava/lang/String;III)I", ordinal = 1))
    //public void nametagThing(CallbackInfo ci) {
    //    ModInstances.getModLevelHead().renderTag(this.getFontRendererFromRenderManager());
    //}

}
