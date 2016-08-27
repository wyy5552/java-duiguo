package com.user;

import com.cards.Card;

public class User {
	/**
	 * �û�����
	 */
	private String name;
	/**
	 * �û�id
	 */
	private int id;
	
	public Card[] list = new Card[2];

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
