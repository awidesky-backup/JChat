package com.intgames.JChat.runnables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;

import javax.swing.JOptionPane;

import com.intgames.JChat.Server;
import com.intgames.JChat.GUI.MainGUI;

public class ServerAccepter implements Runnable {

	private ServerSocket sock;
	private BufferedReader br;
	private PrintWriter pw;
	
	
	public ServerAccepter(ServerSocket server) {
		// TODO Auto-generated constructor stub
		this.sock = server;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
			while (true) {
			try {
				
				pw = new PrintWriter(this.sock.accept().getOutputStream());
				Server.putPrintWriter(pw);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				MainGUI.error("클라이언트 접속 오류!", "클라이언트와 연결하지 못했습니다.");
				e.printStackTrace();
			}

		}
	}

}
