package com.intgames.JChat.core;

import com.intgames.JChat.resources.MessageOutputStream;

public class ConnectedClient {

	public String ip;
	public MessageOutputStream mo;
	
	public ConnectedClient(MessageOutputStream mo2, String ip2) {
		// TODO Auto-generated constructor stub
		
		ip = ip2;
		mo = mo2;
				
	}
	
}
