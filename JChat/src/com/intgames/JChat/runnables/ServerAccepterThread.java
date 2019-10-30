package com.intgames.JChat.runnables;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.intgames.JChat.Exception.BannedClientException;
import com.intgames.JChat.GUI.ErrorProvider;
import com.intgames.JChat.core.ClientData;
import com.intgames.JChat.core.Server;
import com.intgames.JChat.resources.MessageOutputStream;

public class ServerAccepterThread extends Thread {

	private ServerSocket sock;
	private MessageOutputStream mo;
	private ObjectInputStream oi;
	private Server svr;
	private ErrorProvider mg;
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
			
			svr.checkClient(sc);
			
			ip = sc.getLocalAddress();
			mo = new MessageOutputStream(sc.getOutputStream());
			svr.addClientData(new ClientData(mo, ip));
			oi = new ObjectInputStream(sc.getInputStream());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			mg.error("Ŭ���̾�Ʈ ���� ����!", "Ŭ���̾�Ʈ�� �����ϴ� ���� ������ �߻��߽��ϴ�!\n" + e.getMessage());
			svr.showtext("Ŭ���̾�Ʈ ���� �Ұ�-"+ e.getMessage());
			return;
			
		} catch (BannedClientException e) {
			
			svr.showtext("������ ���� ����� ���� - "+ e.getMessage());
			return;
			
		}
		
		svr.showtext(ip.toString() + " ���� Ŭ���̾�Ʈ ���� ����");
		
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
