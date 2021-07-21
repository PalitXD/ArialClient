package com.arialclient.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleSocketClient {
    public static void start() throws IOException {
        ServerSocket ss =new ServerSocket(6666);
        Socket s=ss.accept();//establishes connection
        DataInputStream dis=new DataInputStream(s.getInputStream());
        String  str=(String)dis.readUTF();
        System.out.println("message= "+str);
        ss.close();
    }
}
