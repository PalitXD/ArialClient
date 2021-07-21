package com.arialclient.mixins.client.renderer.network;

import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import org.json.JSONException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.io.IOException;

@Mixin(NetworkPlayerInfo.class)
public class MixinNetworkPlayerInfo {

    /**
     * @author duhc
     */
    @Overwrite
    public ResourceLocation getLocationCape() throws IOException, JSONException {
        //if (CosmeticController.shouldRenderCape()) return new ResourceLocation(CosmeticController.getCapeStyle());
        return null;
    }

}
