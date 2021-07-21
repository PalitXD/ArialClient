package com.arialclient.utils;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.HttpServerConnection;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import net.minecraft.client.resources.SkinManager.SkinAvailableCallback;

public class Capes {
	private static JsonObject capesJson;
	private static JsonParser jsonParser = new JsonParser();


	
	public static void getCapes(GameProfile gameprofile, Map skinMap, SkinAvailableCallback callback) {
		if(capesJson == null) {
			try {
				HttpsURLConnection connection = (HttpsURLConnection) new URL("https://pastebin.com/raw/5wGW5YMN").openConnection();
				connection.connect();
				capesJson = jsonParser.parse(new InputStreamReader(connection.getInputStream())).getAsJsonObject();
			}catch(Exception e) {
				e.printStackTrace();
				return;
			}
			try {
				if(capesJson.has(gameprofile.getName())) {
					skinMap.put(Type.CAPE, new MinecraftProfileTexture(capesJson.get(gameprofile.getName()).getAsString(), null));
				}

			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		//skinMap.put(Type.CAPE, new MinecraftProfileTexture("https://cdn.discordapp.com/attachments/825481412307976243/826164450079277096/hjghj.png", null));
		//ArialLogger.info("SkinMap has been putttteredfersioepmoefesomf");
	}
}

