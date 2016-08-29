package com.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardManager {
	List<Card> cards;

	public CardManager()
	{
		cards = new ArrayList<>();


	}

	public void init()
	{
		CardColor[] colors = CardColor.values();
		System.out.println("创建一副新扑克！");
		Card card;
		for (CardColor color : colors) {
//			System.out.println("当前枚举名字：" + color.name());
//			System.out.println("当前索引：" + color.ordinal());
//			System.out.println("当前花色：" + color.getColor());
			for(int i = 1; i < 14; i++)
			{
				card = new Card();
				card.color = color;
				card.num = i;
				
				cards.add(card);
			}
			
		}
		
		show();
		
	}
	/**
	 * 洗牌
	 */
	public void shuffle()
	{
		System.out.println("洗牌");
		Collections.shuffle(cards);
		show();
	}
	/**
	 * 每人发一张牌
	 * @param 每次发牌数量
	 * @return 
	 */
	public Card[] perflop(int value)
	{
		Card[] list = new Card[2];
		for(int i = 0; i < value; i++)
		{
			list[i] = cards.remove(0);
		}
		return list;
	}
//输出当前的牌
	private void show() {
		
//		for(Card c :cards)
//		{
//			System.out.println("花色:" + c.color.getColor() + " 数字：" + c.num);
//		}
	}
	
}
