package com.arialclient.mixins.client.renderer;

import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityPlayer.class)
public interface YawAccessor {
    @Accessor
    public void setCameraYaw(float cameraYaw);

    @Accessor
    public void setPrevCameraYaw(float prevCameraYaw);
}
