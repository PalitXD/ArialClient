package com.arialclient.cosmetics;

import com.arialclient.http.HttpFunctions;
import com.arialclient.http.gsonobjs.ObjUserCosmetics;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;

public class CosmeticController {

	private static ObjUserCosmetics[] userCosmetics;

	public static boolean shouldRenderCape(NetworkPlayerInfo player) {
		ObjUserCosmetics cosmetics = getCosmetics(player);
		if (cosmetics == null) return false;

		return cosmetics.hasCape();
	}

	public static String getCapeStyle(NetworkPlayerInfo player) {
		ObjUserCosmetics cosmetics = getCosmetics(player);
		if (cosmetics == null) {
			return null;
		}

		return cosmetics.getCapeStyle();
	}

	public static boolean shouldRenderTopHat(AbstractClientPlayer player) {
		ObjUserCosmetics cosmetic = getCosmetics(player);
		if(cosmetic == null) {
			return false;
		}

		return cosmetic.getHat().isEnabled();
	}

	public static float[] getTopHatColor(AbstractClientPlayer player) {
		ObjUserCosmetics cosmetic = getCosmetics(player);
		if(cosmetic == null) {
			return new float[] {1, 0, 0};
		}

		return cosmetic.getHat().getColor();
	}

	private static ObjUserCosmetics getCosmetics(AbstractClientPlayer player) {
		for(ObjUserCosmetics cosmetic : userCosmetics) {
			if(player.getGameProfile().getId().equals(cosmetic.getUuid())) {
				return cosmetic;
			}

		}
		return null;
	}

	private static ObjUserCosmetics getCosmetics(NetworkPlayerInfo player) {
		for(ObjUserCosmetics cosmetic : userCosmetics) {
			if(player.getGameProfile().getId().equals(cosmetic.getUuid())) {
				return cosmetic;
			}

		}
		return null;
	}

	public static void downloadUserCosmetics() {
		userCosmetics = HttpFunctions.downloadUserCosmetics();
	}
}
