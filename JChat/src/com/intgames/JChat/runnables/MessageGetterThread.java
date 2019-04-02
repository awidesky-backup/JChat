package com.intgames.JChat.runnables;

import java.io.BufferedReader;
import java.io.IOException;
import com.intgames.JChat.Server;

public class MessageGetterThread extends Thread {

	private BufferedReader br;
	private Server svr;
	private boolean isrunning;
	
	public MessageGetterThread(BufferedReader br, Server svr) {
		// TODO Auto-generated constructor stub
		this.br = br;
		this.svr = svr;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String msg = null;
		String who = null;

		while (this.isrunning) {
			
			try {
				who = br.readLine();
				msg = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				svr.mg.error("������ ���� ����!", "�޽����� �޾ƿ��� �� �����߽��ϴ�.\n" + e.getMessage());
			}
		
			svr.sendEveryone(who, msg);
		
		}
		
	}

	public void kill() {
		// TODO Auto-generated method stub
		
		this.isrunning = false;
		
		try {
			
			this.br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block

			svr.mg.error("������ ���� �� �����ϴ�!",e.getMessage());
		
		}
		
	}

}
