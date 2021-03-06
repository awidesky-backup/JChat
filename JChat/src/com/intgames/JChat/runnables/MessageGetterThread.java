package com.intgames.JChat.runnables;

import java.io.IOException;
import java.io.ObjectInputStream;

import com.intgames.JChat.core.Server;
import com.intgames.JChat.resources.Message;

public class MessageGetterThread extends Thread {

	private ObjectInputStream oi;
	private Server svr;
	private String ip;
	private boolean isrunning;
	
	public MessageGetterThread(ObjectInputStream oi, Server svr, String ip) {
		// TODO Auto-generated constructor stub
		this.oi = oi;
		this.svr = svr;
		this.ip = ip;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	
		while (this.isrunning) {
			
			this.getmessage();
			
		}
		
	}
	
	private void getmessage() {
		Message msg = null;
		long ping = 0L;
		
		try {
			
			msg = (Message)oi.readObject();
			ping = msg.getPing(System.nanoTime());
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			svr.mg.error("데이터 수신 오류!", "메시지를 받아오는 데 실패했습니다.\n" + e.getMessage());
			svr.showtext(ip + "에게서 메시지를 받아오는 도중 실패-" + e.getMessage());
			
		} catch (ClassNotFoundException e) {
			
			// TODO Auto-generated catch block
			svr.mg.error("데이터 수신 오류!", "수신된 데이터를 변환하는 도중 문제가 발생했습니다.\n" + e.getMessage());
			svr.showtext(ip + "에게서 수신된 데이터를 변환하는 도중 문제 발생-" + e.getMessage());
			
		}
	
		svr.sendEveryone(msg, ping);
	
	}

	public void kill() {
		// TODO Auto-generated method stub
		
		this.isrunning = false;
		
		try {
			
			this.oi.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block

			svr.mg.error("서버를 닫을 수 없습니다!",e.getMessage());
		
		}
		
	}

}
