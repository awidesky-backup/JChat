package com.intgames.JChat.GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MainGUI extends JFrame {

	
	
	public static void error(String title, String message) {
		
		JOptionPane.showMessageDialog(null, message , title , JOptionPane.ERROR_MESSAGE);
		
	}

	public static void clientgot(String msg) {
		// TODO Auto-generated method stub
		
	}
}
