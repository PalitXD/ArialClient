package com.arialclient.http;

import com.arialclient.http.gsonobjs.ObjGlobalSettings;
import com.arialclient.http.gsonobjs.ObjIsBanned;
import com.arialclient.http.gsonobjs.ObjIsWhitelisted;
import com.arialclient.http.gsonobjs.ObjUserCosmetics;
import com.arialclient.utils.HWID;
import com.google.gson.Gson;
import net.minecraft.client.Minecraft;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class HttpFunctions {

    private static final Gson gson = new Gson();

    public static void sendHWIDMap() {
        Minecraft mc = Minecraft.getMinecraft();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("uuid", mc.getSession().getProfile().getId().toString()));
        params.add(new BasicNameValuePair("username", mc.getSession().getProfile().getName()));
        params.add(new BasicNameValuePair("hwid", HWID.getHWID()));
        HTTPUtils.sendPostAsync(HTTPEndpoints.MAP_UUID, params);
    }

    public static boolean isAPIUp() {
        HTTPReply reply = HTTPUtils.sendGet(HTTPEndpoints.BASE);
        return reply.getStatusCode() == 200;
    }

    public static boolean isBanned() throws Exception {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("hwid", HWID.getHWID()));
        HTTPReply reply = HTTPUtils.sendGet(HTTPEndpoints.IS_BANNED, params);
        if(reply.getStatusCode() == 200) {
            ObjIsBanned objIsBanned = gson.fromJson(reply.getBody(), ObjIsBanned.class);
            return objIsBanned.isBanned();
        }
        throw new Exception("Could not access API!");
    }

    public static boolean isWhitelisted() throws Exception {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("hwid", HWID.getHWID()));
        HTTPReply reply = HTTPUtils.sendGet(HTTPEndpoints.IS_WHITELISTED, params);
        if(reply.getStatusCode() == 200) {
            ObjIsWhitelisted objIsWhitelisted = gson.fromJson(reply.getBody(), ObjIsWhitelisted.class);
            return objIsWhitelisted.isWhitelisted();
        }
        throw new Exception("Could not access API!");
    }

    public static ObjUserCosmetics[] downloadUserCosmetics() {
        return gson.fromJson(HTTPUtils.sendGet(HTTPEndpoints.COSMETICS).getBody(), ObjUserCosmetics[].class);
    }

    public static ObjGlobalSettings downloadGlobalSettings() {
        return gson.fromJson(HTTPUtils.sendGet(HTTPEndpoints.GLOBAL_SETTINGS).getBody(), ObjGlobalSettings.class);
    }

}
