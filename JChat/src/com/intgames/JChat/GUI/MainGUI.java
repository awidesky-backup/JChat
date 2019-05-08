package com.intgames.JChat.GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.intgames.JChat.core.Client;
import com.intgames.JChat.resources.Message;

@SuppressWarnings("serial")
public class MainGUI extends JFrame {

	/**
	 * @author Eugene Hong
	 * 
	 * this class manage log   
	 * 
	 * */
	
	ServerLogGUI log;
	
	public MainGUI(ServerLogGUI log) {
		// TODO Auto-generated constructor stub
		this.log = log;
	}

	public MainGUI(Client client) {
		// TODO Auto-generated constructor stub
	}

	public void error(String title, String message) {
		
		JOptionPane.showMessageDialog(null, message , title , JOptionPane.ERROR_MESSAGE);
		log.println(new Message(null, title + " : " + message), -1L);
		
	}

	public static void clientgot(String who, String msg) {
		// TODO Auto-generated method stub
		
		
	}
}
