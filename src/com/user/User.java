package com.user;

import com.cards.Card;
import com.cards.CardColor;
import com.cards.CardNum;

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
	public Card[] list = new Card[PlayMethodDict.CARDS_NUM];

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
	 /**
	  * 
	  * @return 手中牌的大小,红牌数量、牌面
	  */
	public CardNum getCardNum()
	{
		int result = 0 ;
		int redNum = 0;
		for(Card c : list)
		{
			if(c.color.getColor() == CardColor.hongtao.getColor() ||
				c.color.getColor() == CardColor.fangkuai.getColor())
				{
					redNum ++;
				}
				
			result += c.num;
		}
		CardNum num = new CardNum();
		num.num = result % 10;
		num.redNum = redNum;
		num.user = this;
		return num;
	}
	/**
	 * 输出用户名字，id，手中卡牌
	 */
	public void traceCard()
	{
		System.out.println("玩家姓名：" + this.name);
		System.out.println("玩家id：" + this.id);
		for(Card c : list)
		{
			System.out.println("牌面：" + c.num
					+ "  花色：" + c.color.getColor());
		}
		System.out.println("-------------------------");
	}
	
}
