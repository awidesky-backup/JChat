package com.intgames.JChat.core;

import java.net.InetAddress;

import com.intgames.JChat.resources.MessageOutputStream;

public class ClientData {

	public InetAddress ip;
	public MessageOutputStream mo;
	
	public ClientData(MessageOutputStream mo2, InetAddress ip2) {
		// TODO Auto-generated constructor stub
		
		ip = ip2;
		mo = mo2;
				
	}
	
}
