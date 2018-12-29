package com.intgames.JChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client  {

	private Socket sock;
	private BufferedReader br;
	private PrintWriter pw;
	
	public void setnetwork(String ip, int port) {
		// TODO Auto-generated method stub
			
			try {
				sock = new Socket(ip, port);
			} catch (UnknownHostException e) {
				GUI.warning("호스트를 알 수 없습니다!");
			
			} catch (IOException e) {
				GUI.warning("서버와의 통신을 위해 소켓을 생성하는 도중 문제가 발생했습니다!");
			}
			
			try {
				br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			} catch (IOException e) {
				GUI.warning("서버에서 데이터를 받을 준비를 하는 도중 문제가 발생했습니다!");
			}
			
			try {
				pw = new PrintWriter(sock.getOutputStream());
			} catch (IOException e) {
				GUI.warning("서버로 데이터를 보낼 준비를 하는 도중 문제가 발생했습니다!");
			}
			
			new Thread(() ->  {
				
				
				String msg = null;
				
				while(true) {
					
					try {
						while((msg = br.readLine()) != null) {
							
							GUI.clientgot(msg);
							
						}
					} catch (IOException e) {
						GUI.warning("서버에서 데이터를 받는 도중 문제가 발생했습니다!");
					}
					
					if (Launcher.clientstate.equals("ClientStopSend"))
						break;
					
				}
				
			}).start();

	}
}

