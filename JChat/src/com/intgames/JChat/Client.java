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
				MainGUI.error("UnknownHostException","ȣ��Ʈ�� �� �� �����ϴ�!");
			
			} catch (IOException e) {
				MainGUI.error("IOException","�������� ����� ���� ������ �����ϴ� ���� ������ �߻��߽��ϴ�!");
			}
			
			try {
				br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			} catch (IOException e) {
				MainGUI.error("InputStreamReader ���� �� ����","�������� �����͸� ���� �غ� �ϴ� ���� ������ �߻��߽��ϴ�!");
			}
			
			try {
				pw = new PrintWriter(sock.getOutputStream());
			} catch (IOException e) {
				MainGUI.error("getOutputStream ���� �� ����","������ �����͸� ���� �غ� �ϴ� ���� ������ �߻��߽��ϴ�!");
			}
			
			new Thread(() ->  {
				
				
				String msg = null;
				
				while(true) {
					
					try {
						while((msg = br.readLine()) != null) {
							
							MainGUI.clientgot(msg);
							
						}
					} catch (IOException e) {
						MainGUI.error("������ ���� ����","�������� �����͸� �޴� ���� ������ �߻��߽��ϴ�!");
					}
					
					if (Launcher.clientstate.equals("ClientStopSend"))
						break;
					
				}
				
			}).start();

	}
}

