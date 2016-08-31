package com.cards;


public class Card {

	public Card() {
	}
	
	/**
	 * 牌面
	 */
	public int num;
	/**
	 * 花色
	 */
	public CardColor color;
	//例如： 红桃：8
	public String toString()
	{
		return color.getColor() + ":" + num;
	}
}
