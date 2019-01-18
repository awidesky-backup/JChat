package com.intgames.JChat.GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.intgames.JChat.Client;

@SuppressWarnings("serial")
public class MainGUI extends JFrame {

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
		log.println(title + " : " + message, null);
		
	}

	public static void clientgot(String who, String msg) {
		// TODO Auto-generated method stub
		
		
	}
}
