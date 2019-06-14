package com.intgames.JChat.GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.intgames.JChat.core.ServerLogGUI;
import com.intgames.JChat.resources.Log;
import com.intgames.JChat.resources.Message;

@SuppressWarnings("serial")
public class ErrorGUI extends JFrame {

	/**
	 * @author Eugene Hong
	 * 
	 * this class manage log   
	 * 
	 * */
	
	Log log;
	
	public ErrorGUI(ClientLogGUI log) {
		// TODO Auto-generated constructor stub
		this.log = log;
		
	}

	public ErrorGUI(ServerLogGUI log2) {
		// TODO Auto-generated constructor stub
		this.log = log2;
		
	}

	public void error(String title, String message) {
		
		JOptionPane.showMessageDialog(null, message , title , JOptionPane.ERROR_MESSAGE);
		log.println(new Message(null, title + " : " + message), -1L);
		
	}

}
