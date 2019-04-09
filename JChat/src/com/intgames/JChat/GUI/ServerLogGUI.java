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
	final String[] week = { "일", "월", "화", "수", "목", "금", "토" };
	private Calendar oCalendar;
	private BufferedWriter br;
	private String svr;
	
	private SimpleDateFormat filef = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
	private SimpleDateFormat dayf = new SimpleDateFormat("dd");
	private SimpleDateFormat timeinlogf = new SimpleDateFormat("[a hh:mm] ");
	private SimpleDateFormat dayinlogf = new SimpleDateFormat(" yyyy년 MM월 dd일 ");
	private String today;
	private Server server;
	
	
	public ServerLogGUI(String servername, Server server, String logpath) {
		// TODO Auto-generated constructor stub
		
		today = dayf.format(System.currentTimeMillis());
		svr = servername;
		this.server = server;
		
		try {
			br = new BufferedWriter(new FileWriter(new File(logpath + "\\" + filef.format(System.currentTimeMillis()) + "-" + svr + ".txt")));
			br.write("---------------" + dayinlogf.format(System.currentTimeMillis()) + week[oCalendar.get(Calendar.DAY_OF_WEEK) - 1] + "요일" + " ---------------");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			server.mg.error("로그 파일 제작 실패!", "로그 파일을 제작하는 도중 오류가 발생했습니다!\n" + e.getMessage());
		}
		
	}
	
	
	public void println(String log, String who) {

		try {
		
			if (today != dayf.format(System.currentTimeMillis())) {
				
				br.write("---------------" + dayinlogf.format(System.currentTimeMillis()) + week[oCalendar.get(Calendar.DAY_OF_WEEK) - 1] + "요일" + " ---------------");
				today = dayf.format(System.currentTimeMillis());
	
			}
		
			if (who != null) who = "[" + who + "] ";
			else who = "_SYSTEM_ ";
		
			text.append(who + timeinlogf.format(System.currentTimeMillis()) + log + "\n");

			br.write(who + timeinlogf.format(System.currentTimeMillis()) + log + "\n");
			br.flush();
			
		} catch (IOException e) {
			
			server.mg.error("로그 파일 제작 중 오류", "로그 파일에 적어넣는 도중 실패했습니다!\n" + e.getMessage());
			
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
