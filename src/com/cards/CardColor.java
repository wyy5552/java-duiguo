package com.cards;

public enum CardColor {
	heitao(1,"黑桃"),hongtao(2,"红桃"),
	meihua(3,"梅花"),fangkuai(4,"方块");
	
	private int _index;
	private String _color;
	private CardColor(int index,String color)
	{
		_index = index;
		_color = color;
	}
	
	public String getColor()
	{
		return this._color;
	}
	 @Override
     public String toString() {

         return String.valueOf(this._index);

     }
}
