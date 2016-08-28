package com.user;

import com.cards.Card;

public class User {
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 玩家唯一id
	 */
	private int id;
	/**
	 * 人手中牌
	 */
	public Card[] list = new Card[2];

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
