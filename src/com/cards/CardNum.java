package com.cards;

import com.user.User;


public class CardNum implements Comparable<CardNum>{
	/**
	 * 红色牌数量
	 */
	public int redNum;
	/**
	 * 牌值
	 */
	public int num;
	
	public User user;
	
	@Override
	public int compareTo(CardNum card) {
		// TODO Auto-generated method stub
		if(this.num > card.num)
		{
			return 1;
		}
		else if(this.num < card.num)
		{
			return -1;
		}
		else
		{
			if(this.redNum > card.redNum)
			{
				return 1;
			}
			else if(this.redNum < card.redNum)
			{
				return -1;
			}
			else
			{
				return 0;
			}
		}
	}
}
