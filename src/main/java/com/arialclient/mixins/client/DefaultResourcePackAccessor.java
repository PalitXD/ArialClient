package com.arialclient.mixins.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DefaultResourcePack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Minecraft.class)
public interface DefaultResourcePackAccessor {

    @Accessor("mcDefaultResourcePack")
    public DefaultResourcePack getDefaultResourcePack();

}
