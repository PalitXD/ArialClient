package com.arialclient.mixins.client.renderer.entity;

import com.arialclient.capes.AnimatedLayerCape;
import com.arialclient.cosmetics.impl.CosmeticTopHat;
import com.arialclient.utils.AnimatedResourceLocation;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderPlayer.class)
public abstract class MixinRenderPlayer extends RendererLivingEntity<AbstractClientPlayer>{

    public MixinRenderPlayer(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
        super(renderManagerIn, modelBaseIn, shadowSizeIn);
    }

    protected abstract <V extends EntityLivingBase, U extends LayerRenderer<V>> boolean addLayer();

    @Inject(method = "<init>(Lnet/minecraft/client/renderer/entity/RenderManager;Z)V", at = @At("RETURN"))
    public void addThings(CallbackInfo ci) {

        this.addLayer(new CosmeticTopHat((RenderPlayer) (Object) this));
        this.addLayer(new AnimatedLayerCape ((RenderPlayer) (Object) this, new AnimatedResourceLocation("arialclient/capes/rickroll/", 17, 4)));
    }
}
