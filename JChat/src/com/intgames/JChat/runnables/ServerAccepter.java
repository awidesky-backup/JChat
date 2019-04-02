package com.intgames.JChat.runnables;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.intgames.JChat.Server;
import com.intgames.JChat.GUI.MainGUI;

public class ServerAccepter implements Runnable {

	private ServerSocket sock;
	private BufferedWriter bw;
	private Server svr;
	private MainGUI mg;
	private boolean isrunning;
	
	public ServerAccepter(ServerSocket server, Server svr) {
		// TODO Auto-generated constructor stub
		this.sock = server;
		this.svr = svr;
		this.mg = svr.mg;
		this.isrunning = true;
	}

	public void setisrunning(boolean isrunning) {
		
		this.isrunning = isrunning;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
			while (isrunning) {
				
				Socket sc = null;
				BufferedReader br = null;
				
				try {
					
					sc = sock.accept();
					bw = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));
					svr.putBufferedWriter(bw);
					br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					mg.error("클라이언트 연결 오류!", e.getMessage());
					continue;
				}
				
				new Thread(new MessageGetter(br, this.svr)).start();
		}
	}

}
