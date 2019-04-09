package com.intgames.JChat.GUI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.intgames.JChat.Server;

@SuppressWarnings("serial")
public class ServerLogGUI extends JFrame {

	private JTextArea text;
	final String[] week = { "��", "��", "ȭ", "��", "��", "��", "��" };
	private Calendar oCalendar;
	private BufferedWriter br;
	private String svr;
	
	private SimpleDateFormat filef = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
	private SimpleDateFormat dayf = new SimpleDateFormat("dd");
	private SimpleDateFormat timeinlogf = new SimpleDateFormat("[a hh:mm] ");
	private SimpleDateFormat dayinlogf = new SimpleDateFormat(" yyyy�� MM�� dd�� ");
	private String today;
	private Server server;
	
	
	public ServerLogGUI(String servername, Server server, String logpath) {
		// TODO Auto-generated constructor stub
		
		today = dayf.format(System.currentTimeMillis());
		svr = servername;
		this.server = server;
		
		try {
			br = new BufferedWriter(new FileWriter(new File(logpath + "\\" + filef.format(System.currentTimeMillis()) + "-" + svr + ".txt")));
			br.write("---------------" + dayinlogf.format(System.currentTimeMillis()) + week[oCalendar.get(Calendar.DAY_OF_WEEK) - 1] + "����" + " ---------------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			server.mg.error("�α� ���� ���� ����!", "�α� ������ �����ϴ� ���� ������ �߻��߽��ϴ�!\n" + e.getMessage());
		}
		
	}
	
	
	public void println(String log, String who) {

		try {
		
			if (today != dayf.format(System.currentTimeMillis())) {
				
				br.write("---------------" + dayinlogf.format(System.currentTimeMillis()) + week[oCalendar.get(Calendar.DAY_OF_WEEK) - 1] + "����" + " ---------------");
				today = dayf.format(System.currentTimeMillis());
	
			}
		
			if (who != null) who = "[" + who + "] ";
			else who = "_SYSTEM_ ";
		
			text.append(who + timeinlogf.format(System.currentTimeMillis()) + log + "\n");

			br.write(who + timeinlogf.format(System.currentTimeMillis()) + log + "\n");
			br.flush();
			
		} catch (IOException e) {
			
			server.mg.error("�α� ���� ���� �� ����", "�α� ���Ͽ� ����ִ� ���� �����߽��ϴ�!\n" + e.getMessage());
			
		}
	}
	
	public void serverstatus(int status) {
		
		/**
		 * 
		 * 0 means good exit
		 * 1 means exit with error
		 * 
		 * */
		
		switch(status) {
		
			case 0:
			
			
			case 1:
			
			
			default:
		
		}
		
	}
	
	
}
