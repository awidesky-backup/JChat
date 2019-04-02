package com.intgames.JChat.runnables;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;

import com.intgames.JChat.Server;
import com.intgames.JChat.GUI.MainGUI;

public class ServerAccepterThread extends Thread {

	private ServerSocket sock;
	private BufferedWriter bw;
	private Server svr;
	private MainGUI mg;
	private LinkedList<MessageGetterThread> msggetter = new LinkedList<>();
	private boolean isrunning;
	
	public ServerAccepterThread(ServerSocket server, Server svr) {
		// TODO Auto-generated constructor stub
		this.sock = server;
		this.svr = svr;
		this.mg = svr.mg;
		this.isrunning = true;
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
					mg.error("클라이언트 연결 오류!", "클라이언트와 연결하는 도중 문제가 발생했습니다!\n" + e.getMessage());
					continue;
				}
				
				MessageGetterThread th = new MessageGetterThread(br, this.svr);
				this.msggetter.add(th);
				th.start();
				
		}
	}
	
	
	public void kill() {
		
		this.isrunning = false;
		Iterator<MessageGetterThread> it = this.msggetter.iterator();
		
		while(it.hasNext()) {
			
			it.next().kill();
			
		}
	}

}
