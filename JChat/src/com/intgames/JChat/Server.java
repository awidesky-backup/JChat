package com.intgames.JChat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;

import com.intgames.JChat.GUI.MainGUI;
import com.intgames.JChat.GUI.ServerLogGUI;
import com.intgames.JChat.runnables.ServerAccepter;

public class Server {

	private String servername;
	private ServerSocket server;
	private ArrayList<BufferedWriter> bw = new ArrayList<>();
	private ServerLogGUI log;
	
	public MainGUI mg;
	
	public Server(String servername) {
		this.servername = servername;
		mg = new MainGUI(this.log);
	}

	public void setnetwork(int port) {
		
		log = new ServerLogGUI(servername, this);
		
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			mg.error("ServerSocket 생성 실패!", "ServerSocket 생성에 실패했습니다!");
		}
		
		new Thread(new ServerAccepter(server, this)).start();
		
	}

	public void putBufferedWriter(BufferedWriter bw) {
		// TODO Auto-generated method stub
		this.bw.add(bw);
		
	}
	
	public void sendEveryone(String who, String msg) {
		
		log.println(msg, who);
		
		Iterator<BufferedWriter> it = bw.iterator();
		
		while(it.hasNext()) {
			
			try {
				BufferedWriter br = it.next();
				br.write(who);
				br.newLine();
				br.flush();
				br.write(msg);
				br.newLine();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				mg.error("서버 전송 오류", "클라이언트에게 메시지를 전송할 수 없습니다.");
			}
			
		}
		
		
	}
	
}
