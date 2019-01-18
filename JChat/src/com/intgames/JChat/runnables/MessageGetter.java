package com.intgames.JChat.runnables;

import java.io.BufferedReader;
import java.io.IOException;
import com.intgames.JChat.Server;

public class MessageGetter implements Runnable {

	private BufferedReader br;
	private Server svr;

	public MessageGetter(BufferedReader br, Server svr) {
		// TODO Auto-generated constructor stub
		this.br = br;
		this.svr = svr;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String msg = null;
		String who = null;

		while (true) {
			
			try {
				who = br.readLine();
				msg = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				svr.mg.error("������ ���� ����!", "�޽����� �޾ƿ��� �� �����߽��ϴ�.");
			}
		
			svr.sendEveryone(who, msg);
		
		}
		
	}

}
