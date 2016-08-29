import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cards.Card;
import com.cards.CardManager;
import com.cards.CardNum;
import com.user.User;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CardManager cmgr = new CardManager();
		cmgr.init();
		cmgr.shuffle();
		
		int i;
		
		Scanner input = new Scanner(System.in);
		
		ArrayList<User> userList = new ArrayList<User>();

		User user;
		for(i = 0; i < 2; i++)
		{
			user = new User();
			System.out.println("请输入第" + i + "名玩家的id");
			
			while (true) {
				try {
					user.setId(input.nextInt());
					break;
				} catch (InputMismatchException e) {
					System.out.println("请输入整数类型的ID?");
					user.setId(input.nextInt());
					continue;
				}
			}
			
			System.out.println("请输入第" + i + "名玩家的name");
			user.setName(input.next());
			
			userList.add(user);
		}
		
		input.close();
		
		Card[] perFlop;//发牌数组
		for(i = 0; i < 2; i++)
		{//每人发两次牌
			perFlop = cmgr.perflop(2);
			for(int j = 0; j < userList.size(); j++)
			{//每人获得一张牌
				userList.get(j).list[i] = perFlop[j];
			}
		}
		ArrayList<CardNum> cardNums = new ArrayList<CardNum>();
		//输出玩家手牌
		for(User u:userList)
		{
			u.traceCard();
			cardNums.add(u.getCardNum());
		}
		
		Collections.sort(cardNums,new SortByNum());
		CardNum num = cardNums.get(0);
		System.out.println(num.user.getName() + "  失败了，牌面是：红色数量：" + num.redNum + "  牌值：" + num.num);
		num = cardNums.get(1);
		System.out.println(num.user.getName() + "  获得了胜利，牌面是：红色数量：" + num.redNum + "  牌值：" + num.num);
		
	}
	
	
	public Main()
	{
		
	}
}

class SortByNum implements Comparator<CardNum> 
{
	 public int compare(CardNum u1, CardNum u2)
	 {
		 return u1.compareTo(u2);
	 }
}
