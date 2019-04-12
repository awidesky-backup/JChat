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
				svr.mg.error("������ ���� ����!", "�޽����� �޾ƿ��� �� �����߽��ϴ�.\n" + e.getMessage());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				svr.mg.error("������ ���� ����!", "���ŵ� �����͸� ��ȯ�ϴ� ���� ������ �߻��߽��ϴ�.\n" + e.getMessage());
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

			svr.mg.error("������ ���� �� �����ϴ�!",e.getMessage());
		
		}
		
	}

}
