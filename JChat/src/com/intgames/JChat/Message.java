package com.intgames.JChat;

import java.io.Serializable;

public class Message implements Serializable{

	/**
	 * MUST CALL readyToSend() BEFORE SERIALIZE!!
	 */
	private static final long serialVersionUID = 1L;
	
	private String who, msg;
	private long starttime;
	
	public Message(String who, String msg) {
		// TODO Auto-generated constructor stub
		
		this.who = who;
		this.msg = msg;
		
	}
	
	public void readyToSend() {
		
		this.starttime = System.nanoTime();
		
	}

	public String getWho() {
		
		return who;
		
	}
	
	public String getMsg() {
		
		return msg;
		
	}

	public double getPing(long now) {
		
		return (now - starttime)/1e6;
		
	}


	@Override
	public String toString() {
		
		return msg;
		
	}

	
}
