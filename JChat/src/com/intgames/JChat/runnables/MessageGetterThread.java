package com.intgames.JChat.runnables;

import java.io.IOException;
import java.io.ObjectInputStream;

import com.intgames.JChat.Message;
import com.intgames.JChat.Server;

public class MessageGetterThread extends Thread {

	private ObjectInputStream oi;
	private Server svr;
	private boolean isrunning;
	
	public MessageGetterThread(ObjectInputStream oi, Server svr) {
		// TODO Auto-generated constructor stub
		this.oi = oi;
		this.svr = svr;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Message msg = null;
		double ping = 0;
		
		
		while (this.isrunning) {
			
			try {
				msg = (Message)oi.readObject();
				ping = msg.getPing(System.nanoTime());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				svr.mg.error("데이터 수신 오류!", "메시지를 받아오는 데 실패했습니다.\n" + e.getMessage());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				svr.mg.error("데이터 수신 오류!", "수신된 데이터를 변환하는 도중 문제가 발생했습니다.\n" + e.getMessage());
			}
		
			svr.sendEveryone(msg, ping);
		
		}
		
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
