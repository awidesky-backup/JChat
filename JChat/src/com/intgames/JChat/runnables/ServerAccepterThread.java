package com.intgames.JChat.runnables;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.intgames.JChat.GUI.ErrorGUI;
import com.intgames.JChat.core.ConnectedClient;
import com.intgames.JChat.core.Server;
import com.intgames.JChat.resources.MessageOutputStream;

public class ServerAccepterThread extends Thread {

	private ServerSocket sock;
	private MessageOutputStream mo;
	private ObjectInputStream oi;
	private Server svr;
	private ErrorGUI mg;
	private List<MessageGetterThread> msggetter = new LinkedList<>();
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
				
			this.Accept();
				
		}
	}
	
	private void Accept() {
		
		Socket sc = null;
		InetAddress ip;
		
		try {
			
			sc = sock.accept();
			ip = sc.getLocalAddress();
			mo = (MessageOutputStream) new ObjectOutputStream(sc.getOutputStream());
			svr.putConnectedClient(new ConnectedClient(mo, ip.toString()));
			oi = new ObjectInputStream(sc.getInputStream());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			mg.error("클라이언트 연결 오류!", "클라이언트와 연결하는 도중 문제가 발생했습니다!\n" + e.getMessage());
			svr.showtext("클라이언트 연결 불가-"+ e.getMessage());
			return;
			
		}
		
		svr.showtext(ip.toString() + " 에서 클라이언트 접속 성공");
		
		MessageGetterThread th = new MessageGetterThread(oi, this.svr, ip.toString());
		this.msggetter.add(th);
		th.setDaemon(true);
		th.start();
		
	}
	
	public void kill() {
		
		this.isrunning = false;
		Iterator<MessageGetterThread> it = this.msggetter.iterator();
		
		while(it.hasNext()) {
			
			it.next().kill();
			
		}
	}

}
