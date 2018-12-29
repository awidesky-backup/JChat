package com.intgames.JChat;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client  {

	Socket sock;
	InputStreamReader in;
	
	public void setnetwork(String ip, int port) {
		// TODO Auto-generated method stub
		try {
			sock = new Socket(ip, port);
			in = new InputStreamReader(sock.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
