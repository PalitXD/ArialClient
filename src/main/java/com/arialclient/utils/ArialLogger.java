package com.arialclient.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ArialLogger {

    public static void info(String... message) {
        for (String out : message)
            System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] [Arial Client/INFO] " + out);
    }

    public static void warn(String... message) {
        for (String out : message)
            System.err.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] [Arial Client/WARN] " + out);
    }

    public static void error(String... message) {
        for (String out : message)
            System.err.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] [Arial Client/ERROR] " + out);
    }

}
