package com.arialclient.mixins.client.resources;

import com.arialclient.utils.Capes;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.InsecureTextureException;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.SkinManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(targets = "net.minecraft.client.resources.SkinManager$3")
public class MixinSkinManager {

    @Shadow @Final GameProfile val$profile;
    @Shadow @Final SkinManager.SkinAvailableCallback val$skinAvailableCallback;

    @Inject(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;addScheduledTask(Ljava/lang/Runnable;)Lcom/google/common/util/concurrent/ListenableFuture;"), locals = LocalCapture.CAPTURE_FAILSOFT, remap = false)
    private void onLoad(CallbackInfo ci, final Map map) {
        Capes.getCapes(val$profile, map, val$skinAvailableCallback);
    }
}
