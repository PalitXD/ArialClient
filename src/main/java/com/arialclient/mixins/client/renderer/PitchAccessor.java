package com.arialclient.mixins.client.renderer;

import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityLivingBase.class)
public interface PitchAccessor {
    @Accessor
    public void setCameraPitch(float cameraPitch);

    @Accessor
    public void setPrevCameraPitch(float prevCameraPitch);
}
