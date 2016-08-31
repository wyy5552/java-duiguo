import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cards.Card;
import com.cards.CardManager;
import com.cards.CardNum;
import com.user.PlayMethodDict;
import com.user.User;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CardManager cmgr = new CardManager();
		cmgr.init();
		cmgr.shuffle();
		
		int i;
		
		Scanner input = new Scanner(System.in);
		//创建userNum个玩家================================
		ArrayList<User> userList = new ArrayList<User>();
		int userNum = 2;//5个人玩
		User user;
		for(i = 0; i < userNum; i++)
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
		//每人发cards_NUM张牌=============================================
		Card[] perFlop;//发牌数组
		for(i = 0; i < PlayMethodDict.CARDS_NUM; i++)//每人发num张牌
		{//每人发两次牌
			perFlop = cmgr.perflop(userList.size());//从牌里面取出玩家数量的牌
			for(int j = 0; j < userList.size(); j++)
			{//每人获得一张牌
				userList.get(j).list[i] = perFlop[j];
			}
		}
		
		//列举每个人手里的牌，让玩家选择搭配，选择后锁死，找到头牌、尾牌===================
		//输出每位玩家的手牌
		for(User u:userList)
		{
			System.out.println(u.getName() + "手中的牌:  ");
			for(Card c:u.list)
			{
				System.out.print(c.toString() + "  ");
			}
			System.out.println(" ");
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
