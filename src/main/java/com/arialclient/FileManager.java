package com.arialclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

public class FileManager {
	
	private static Gson gson = new Gson();
	
	private static File MAIN_DIR = new File("ArialClient");
	private static File MODS_DIR = new File(MAIN_DIR, "Mods");
	
	public static void init() {
		if (!MAIN_DIR.exists()) MAIN_DIR.mkdirs();
		if (!MODS_DIR.exists()) MODS_DIR.mkdirs();
	} 
	
	public static Gson getGson() {
		return gson;
	}
	
	public static File getModsDir() {
		return MODS_DIR;
	}
	
	public static boolean writeJsonToFile(File file, Object o) {
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write(gson.toJson(o).getBytes());
			outputStream.flush();
			outputStream.close();
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static <T extends Object> T readJsonFromFile(File file, Class<T> c) {
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			StringBuilder sb = new StringBuilder();
			String line;
			
			while((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			bufferedReader.close();
			inputStreamReader.close();
			fileInputStream.close();
			
			return gson.fromJson(sb.toString(), c);
		} catch (Exception e) {
			//e.printStackTrace(); disabled bc on first startup for a new person there are lots of errors
			return null;
		}
	}
	
}
