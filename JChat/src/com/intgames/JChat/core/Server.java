package com.intgames.JChat.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.intgames.JChat.GUI.ErrorGUI;
import com.intgames.JChat.GUI.MainGUI;
import com.intgames.JChat.GUI.ServerGUI;
import com.intgames.JChat.resources.Log;
import com.intgames.JChat.resources.LogAppender;
import com.intgames.JChat.resources.Message;
import com.intgames.JChat.resources.MessageOutputStream;
import com.intgames.JChat.runnables.ServerAccepterThread;

public class Server {

	
	/**
	 * @author Eugene Hong
	 * 
	 * 
	 * 
	 */
	
	
	private String servername;
	private ServerSocket serversc;
	private ServerAccepterThread sa;
	private List<ConnectedClient> bw = new LinkedList<>();
	private Log log;
	private ServerGUI sg;
	
	public ErrorGUI mg;
	
	public Server(String servername, String path, ServerGUI sg, int port) {
		
		this.servername = servername;
		this.mg = new ErrorGUI(this.log); 
		this.log = new Log(servername, path, mg, new LogAppender());
		this.sg = sg;
		
		setnetwork(port);
		
	}

	public void setnetwork(int port) {
		
		try {
			
			sg.showtext("ServerSosket ���� ��...");
			serversc = new ServerSocket(port);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			mg.error("ServerSocket ���� ����!", "ServerSocket ������ �����߽��ϴ�!\n" + e.getMessage());
			sg.showtext("ServerSocket ���� ����-" + e.getMessage());
			return;
		
		}
		
		this.sa = new ServerAccepterThread(serversc, this);
		this.sa.setDaemon(true);
		this.sa.start();
		
	}
	
	public String getservername() {
		
		return this.servername;
		
	}

	public void putConnectedClient(ConnectedClient oo) {
		// TODO Auto-generated method stub
		this.bw.add(oo);
		
	}
	
	public void error(String title, String message) {
		
		mg.error(title, message);
		
	}
	
	public void showtext(String string) {
		
		sg.showtext(string);
		
	}
	
	public void sendEveryone(Message msg, long ping) {
		
		log.println(msg, ping);
		
		Iterator<ConnectedClient> it = bw.iterator();
		
		while(it.hasNext()) {
			
			ConnectedClient co = it.next();
			
			try {
				
				MessageOutputStream oo = co.mo;
				oo.writeMessage(msg);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				mg.error("���� ���� ����", co.ip + " �� �޽����� ������ �� �����ϴ�.\n" + e.getMessage());
				sg.showtext(co.ip + " �� �޽����� ������ �� ����-" + e.getMessage());
				
			}
			
		}
		
		
	}
	
	public void shutdown(int status) {
		
		log.serverstatus(status);
		
		this.sa.kill();
		
		Iterator<ConnectedClient> it = bw.iterator();
		
		while(it.hasNext()) {
			
			ConnectedClient co = it.next();
			
			try {
				
				MessageOutputStream br = co.mo;
				br.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				mg.error("������ ���� �� �����ϴ�!", co.ip + " �� ����� ��Ʈ���� �ݴ� ���� ������ �߻��߽��ϴ�.\n" + e.getMessage());
				sg.showtext(co.ip + " �� ����� ��Ʈ���� �ݴ� ���� ���� �߻�-" + e.getMessage());
				
			}
			
		}
		
	}
	
	
}






