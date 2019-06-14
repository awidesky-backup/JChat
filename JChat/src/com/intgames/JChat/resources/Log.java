package com.intgames.JChat.resources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JTextArea;

import com.intgames.JChat.core.Client;
import com.intgames.JChat.core.Server;

@SuppressWarnings("unused")
public abstract class Log {

	final String[] week = { "일", "월", "화", "수", "목", "금", "토" };
	private Calendar oCalendar;
	private BufferedWriter br;
	private String name;
	
	private JTextArea jta = new JTextArea("");
	private SimpleDateFormat filef = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
	private SimpleDateFormat dayf = new SimpleDateFormat("dd");
	private SimpleDateFormat timeinlogf = new SimpleDateFormat("[a hh:mm] ");
	private SimpleDateFormat dayinlogf = new SimpleDateFormat(" yyyy년 MM월 dd일 ");
	private String today;
	private Server server = null;
	private Client client = null;

	
	public void println(Message log, long ping) {

		String who = log.getWho();
		String sping = Long.toString(ping);
		StringBuilder sb = new StringBuilder(sping); //used to insert dot
		
		
		// 핑이 -1.0이면 안적기
		if (ping != -1) {
			
			sping = sb.insert(sping.length() - 6, ".").toString();
			
		} else {
			// Message by System
			sping = "-";
			
		}
		
		try {
		
			if (today != dayf.format(System.currentTimeMillis())) {
				
				append("---------------" + dayinlogf.format(System.currentTimeMillis()) + week[oCalendar.get(Calendar.DAY_OF_WEEK) - 1] + "요일" + " ---------------");
				today = dayf.format(System.currentTimeMillis());
	
			}
			
			if (who != null) who = "[" + who + "] [ " + sping +"ms] ";
			else who = "_SYSTEM_ ";
		
			append(who + timeinlogf.format(System.currentTimeMillis()) + log.getMsg() + "\n");
			
		} catch (IOException e) {
			
			if (server !=null) server.mg.error("로그 파일 제작 중 오류", "로그 파일에 적어넣는 도중 실패했습니다!\n" + e.getMessage());
			else client.mg.error("로그 파일 제작 중 오류", "로그 파일에 적어넣는 도중 실패했습니다!\n" + e.getMessage());
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
			
				println(new Message(null, "서버 종료 시도... 요류 없음"), -1);
			
			case 1:
			
				println(new Message(null, "서버 종료 시도... errorlevel : 1"), -1);
			
			default:
		
				println(new Message(null, "서버 종료 시도... errorlevel : ?"), -1);
				
		}
		
	}
	
	private void append(String log) throws IOException {
		
		br.write(log);
		br.flush();
		jta.append(log);
		
	}
	
	
	
}
