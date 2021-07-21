package com.arialclient.mixins.util;

import com.arialclient.ArialClient;
import com.arialclient.mods.ModInstances;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.potion.Potion;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MovementInputFromOptions.class)
public class MixinMovementInput extends MovementInput {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final GameSettings gameSettings = mc.gameSettings;
    private int sneakWasPressed = 0;
    private int sprintWasPressed = 0;
    private float originalFlySpeed = -1.0F;
    private float boostedFlySpeed = 1;

    @Inject(method = "updatePlayerMoveState", at = @At("TAIL"))
    public void onUpdatePlayerMoveStateHead(CallbackInfo ci) {
        EntityPlayerSP player = mc.thePlayer;

    }
}