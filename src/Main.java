import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cards.Card;
import com.cards.CardManager;
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
		
		Card[] perFlop;
		for(i = 0; i < 2; i++)
		{//每人发两次牌
			perFlop = cmgr.perflop(2);
			for(int j = 0; j < userList.size(); j++)
			{//每人获得一张牌
				userList.get(j).list[i] = perFlop[j];
			}
		}
		
		for(User u:userList)
		{
			
			System.out.print(u.getName());
			
		}
		
		
		
	}
	
	
	public Main()
	{
		
	}
}
