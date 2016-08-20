package cardGame;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
public class game {
    public static void main(String[] args) {
		cards Cards = new cards();
		Cards.show();
		Cards.wash();
		@SuppressWarnings("resource")
		Scanner console = new Scanner(System.in);
		int ID1, ID2;
		String name1, name2;
		/*
		 * 进入死循环-》捕获了输入异常后continue，没有捕获时break跳出，实现了重新输入
		 * scanner有滞纳效应如果不每次更新就会陷入死循环
		 * 循环次数多了不断new scanner出来会增加内存损耗？如何解决？
		 */
		while (true) {
			try {
				System.out.println("请输入第一名玩家的ID");
				ID1 = console.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("请输入整数类型的ID！");
				console=new Scanner(System.in);
				continue;
			}
		}
		System.out.println("请输入第一名玩家的姓名");
		name1 = console.next();
		while (true) {
			try {
				System.out.println("请输入第二名玩家的ID");
				ID2 = console.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("请输入整数类型的ID！");
				console=new Scanner(System.in);
				continue;
			}
		}
		System.out.println("请输入第二名玩家的姓名");
		name2 = console.next();
		people people1 = new people(ID1, name1);
		people people2 = new people(ID2, name2);
		System.out.println("***********开始发牌************");
		System.out.println("玩家" + people1.name + "拿牌");
		people1.handCards.add(Cards.cards.get(0));
		System.out.println("玩家" + people2.name + "拿牌");
		people2.handCards.add(Cards.cards.get(1));
		System.out.println("玩家" + people1.name + "拿牌");
		people1.handCards.add(Cards.cards.get(2));
		System.out.println("玩家" + people2.name + "拿牌");
		people2.handCards.add(Cards.cards.get(3));
		Collections.sort(people1.handCards);
		Collections.sort(people2.handCards);
		System.out.println("玩家:" + people1.name + "最大的手牌是:"
				+ people1.handCards.get(1).getColor()
				+ people1.handCards.get(1).getNum());
		System.out.println("玩家:" + people2.name + "最大的手牌是:"
				+ people2.handCards.get(1).getColor()
				+ people2.handCards.get(1).getNum());
		if (people1.handCards.get(1).compareTo(people2.handCards.get(1)) > 0) {
			System.out.println("**********玩家：" + people1.name
					+ "获胜！***********");
		} else {
			System.out.println("**********玩家：" + people2.name
					+ "获胜！***********");
		}
		System.out.println("玩家各自的手牌为：");
		System.out.println(people1);
		System.out.println(people2);
	}
}

  

