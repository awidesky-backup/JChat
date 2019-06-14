package com.intgames.JChat.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.intgames.JChat.GUI.ErrorGUI;
import com.intgames.JChat.GUI.ClientLogGUI;
import com.intgames.JChat.GUI.MainGUI;
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
	private ServerSocket server;
	private ServerAccepterThread sa;
	private List<MessageOutputStream> bw = new LinkedList<>();
	private ServerLogGUI log;
	
	public ErrorGUI mg;
	
	public Server(String servername, String path) {
		this.servername = servername;
		this.log = new ServerLogGUI(this, path);
		this.mg = new ErrorGUI(this.log); 
		
		/* 
	 	Since different server instance uses different ServerLogGUI instance,
	 	give ServerLogGUI a Server object.
		*/
		 
	}

	public void setnetwork(int port) {
		
		try {
			
			server = new ServerSocket(port);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			mg.error("ServerSocket 생성 실패!", "ServerSocket 생성에 실패했습니다!\n" + e.getMessage());
		
		}
		
		this.sa = new ServerAccepterThread(server, this);
		this.sa.setDaemon(true);
		this.sa.start();
		
	}
	
	public String getservername() {
		
		return this.servername;
		
	}

	public void putObjectOutputStream(MessageOutputStream oo) {
		// TODO Auto-generated method stub
		this.bw.add(oo);
		
	}
	
	public void sendEveryone(Message msg, long ping) {
		
		log.println(msg, ping);
		
		Iterator<MessageOutputStream> it = bw.iterator();
		
		while(it.hasNext()) {
			
			try {
				
				MessageOutputStream oo = it.next();
				oo.writeMessage(msg);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				mg.error("서버 전송 오류", "클라이언트에게 메시지를 전송할 수 없습니다.\n" + e.getMessage());
			}
			
		}
		
		
	}
	
	public void shutdown(int status) {
		
		log.serverstatus(status);
		
		this.sa.kill();
		
		Iterator<MessageOutputStream> it = bw.iterator();
		
		while(it.hasNext()) {
			
			try {
				
				MessageOutputStream br = it.next();
				br.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				mg.error("서버를 닫을 수 없습니다!",e.getMessage());
			}
			
		}
		
	}
	
	public static void main(String args[]) {
		
		new MainGUI(new Server(null, null));
		
	}
	
}






