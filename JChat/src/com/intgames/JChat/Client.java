package com.intgames.JChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.intgames.JChat.GUI.MainGUI;

public class Client  {

	private Socket sock;
	private BufferedReader br;
	private PrintWriter pw;
	
	public void setnetwork(String ip, int port) {
		// TODO Auto-generated method stub
			
			try {
				sock = new Socket(ip, port);
			} catch (UnknownHostException e) {
				MainGUI.error("UnknownHostException","호스트를 알 수 없습니다!");
			
			} catch (IOException e) {
				MainGUI.error("IOException","서버와의 통신을 위해 소켓을 생성하는 도중 문제가 발생했습니다!");
			}
			
			try {
				br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			} catch (IOException e) {
				MainGUI.error("InputStreamReader 생성 중 오류","서버에서 데이터를 받을 준비를 하는 도중 문제가 발생했습니다!");
			}
			
			try {
				pw = new PrintWriter(sock.getOutputStream());
			} catch (IOException e) {
				MainGUI.error("getOutputStream 생성 중 오류","서버로 데이터를 보낼 준비를 하는 도중 문제가 발생했습니다!");
			}
			
			new Thread(() ->  {
				
				
				String msg = null;
				
				while(true) {
					
					try {
						while((msg = br.readLine()) != null) {
							
							MainGUI.clientgot(msg);
							
						}
					} catch (IOException e) {
						MainGUI.error("데이터 수신 오류","서버에서 데이터를 받는 도중 문제가 발생했습니다!");
					}
					
					if (Launcher.clientstate.equals("ClientStopSend"))
						break;
					
				}
				
			}).start();

	}
}

