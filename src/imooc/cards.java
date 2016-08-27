package imooc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class cards {
    List<card> cards;
	public cards() {
		/*
		 * 用两个字符串数组储存花色和数字，用两个int数组生成每张牌的Integer的�??
		 */
		String[] color = { "梅花", "黑桃", "方片", "红桃" };//从小到大
		String[] num = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J",
				"Q", "K", "A" };
		int[] color_ = {1,2,3,4};//对应从小到大
		int[] num_ = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
		cards = new ArrayList<card>();
		//循环建立每一张牌并赋值后添加进cards�?
		for (int i = 0; i < color.length; i++) {
			for (int j = 0; j < num.length; j++) {
				card temp = new card();
				temp.setColor(color[i]);
				temp.setNum(num[j]);
				temp.color_=i;
				temp.num_=j;
				cards.add(temp);
			}
		}
	}
	public void show() {
		System.out.println("*********这副牌现在的顺序是：*********");
		for (card card : cards) {
			System.out.print(card.getColor() + card.getNum() + " ");
		}
		System.out.println();
	}
	public void wash() {
		System.out.println("***************�?始洗�?*******************");
		Collections.shuffle(cards);//洗牌，直接使用shuffle方法随机排序List
		show();
	}
}

