package com.intgames.JChat.GUI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;

import com.intgames.JChat.core.Client;
import com.intgames.JChat.resources.Log;
import com.intgames.JChat.resources.Message;

public class ClientLogGUI extends Log{

	final String[] week = { "��", "��", "ȭ", "��", "��", "��", "��" };
	private Calendar oCalendar;
	private BufferedWriter br;
	private String name;
	
	private JTextArea jta = new JTextArea("");
	
	private SimpleDateFormat filef = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
	private SimpleDateFormat dayf = new SimpleDateFormat("dd");
	private SimpleDateFormat timeinlogf = new SimpleDateFormat("[a hh:mm] ");
	private SimpleDateFormat dayinlogf = new SimpleDateFormat(" yyyy�� MM�� dd�� ");
	private String today;
	private Client client = null;
	
	
	
	public ClientLogGUI(Client client, String logpath) {
		// TODO Auto-generated constructor stub
		
		today = dayf.format(System.currentTimeMillis());
		name = client.getclientname();
		this.client = client;
		
		try {
			br = new BufferedWriter(new FileWriter(new File(logpath + "\\" + filef.format(System.currentTimeMillis()) + "-" + name + ".txt")));
			append("---------------" + dayinlogf.format(System.currentTimeMillis()) + week[oCalendar.get(Calendar.DAY_OF_WEEK) - 1] + "����" + " ---------------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			client.mg.error("�α� ���� ���� ����!", "�α� ������ �����ϴ� ���� ������ �߻��߽��ϴ�!\n" + e.getMessage());
		}
		
	}
	
	
	public void println(Message log, long ping) {

		String who = log.getWho();
		String sping = Long.toString(ping);
		StringBuilder sb = new StringBuilder(sping); //used to insert dot
		
		
		// ���� -1.0�̸� ������
		if (ping != -1) {
			
			sping = sb.insert(sping.length() - 6, ".").toString();
			
		} else {
			// Message by System
			sping = "-";
			
		}
		
		try {
		
			if (today != dayf.format(System.currentTimeMillis())) {
				
				append("---------------" + dayinlogf.format(System.currentTimeMillis()) + week[oCalendar.get(Calendar.DAY_OF_WEEK) - 1] + "����" + " ---------------");
				today = dayf.format(System.currentTimeMillis());
	
			}
			
			if (who != null) who = "[" + who + "] [ " + sping +"ms] ";
			else who = "_SYSTEM_ ";
		
			append(who + timeinlogf.format(System.currentTimeMillis()) + log.getMsg() + "\n");
			
		} catch (IOException e) {
			
			client.mg.error("�α� ���� ���� �� ����", "�α� ���Ͽ� ����ִ� ���� �����߽��ϴ�!\n" + e.getMessage());
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
			
				println(new Message(null, "���� ���� �õ�... ��� ����"), -1);
			
			case 1:
			
				println(new Message(null, "���� ���� �õ�... errorlevel : 1"), -1);
			
			default:
		
				println(new Message(null, "���� ���� �õ�... errorlevel : ?"), -1);
				
		}
		
	}
	
	private void append(String log) throws IOException {
		
		br.write(log);
		br.flush();
		jta.append(log);
		
	}
	
	public void windowOpen() {
		
		JFrame frame = new JFrame(client.getclientname() + "-log");
		frame.setSize(200, 500);
		
		JScrollPane jsp = new JScrollPane();
		
		
	}
	
	
}
