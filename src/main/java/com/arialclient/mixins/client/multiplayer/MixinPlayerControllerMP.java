package com.arialclient.mixins.client.multiplayer;

import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({ PlayerControllerMP.class })
public interface MixinPlayerControllerMP
{
    @Accessor
    void setIsHittingBlock(final boolean p0);
}
