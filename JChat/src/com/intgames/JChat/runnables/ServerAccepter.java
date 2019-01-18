package com.intgames.JChat.runnables;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.intgames.JChat.Server;
import com.intgames.JChat.GUI.MainGUI;

public class ServerAccepter implements Runnable {

	private ServerSocket sock;
	private BufferedWriter bw;
	private Server svr;
	private MainGUI mg;
	
	public ServerAccepter(ServerSocket server, Server svr) {
		// TODO Auto-generated constructor stub
		this.sock = server;
		this.svr = svr;
		this.mg = svr.mg;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
			while (true) {
				
				Socket sc = null;
				BufferedReader br = null;
				
				try {
					
					sc = sock.accept();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					mg.error("Ŭ���̾�Ʈ ���� ����!", "Ŭ���̾�Ʈ�� �������� ���߽��ϴ�.");
				}
				
				
				try {
				
				bw = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));
				svr.putBufferedWriter(bw);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					mg.error("Ŭ���̾�Ʈ ���� ����!", "Ŭ���̾�Ʈ�� OutputStream�� �������� �� �����߽��ϴ�.");
				}

				
				try {
					br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					mg.error("Ŭ���̾�Ʈ ���� ����!", "Ŭ���̾�Ʈ�� InputStream�� �������� �� �����߽��ϴ�.");
				}
				
				new Thread(new MessageGetter(br, this.svr)).start();
		}
	}

}
