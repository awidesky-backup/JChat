package com.intgames.JChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.intgames.JChat.runnables.ServerAccepter;

public class Server {

	private ServerSocket server;
	private Socket client;
	private BufferedReader br;
	private static ArrayList<PrintWriter> pw = new ArrayList<>();
	
	public void setnetwork(int port) {
		
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Thread(new ServerAccepter(server));
		
	}

	public static void putPrintWriter(PrintWriter pw2) {
		// TODO Auto-generated method stub
		pw.add(pw2);
		
	}
	
}
