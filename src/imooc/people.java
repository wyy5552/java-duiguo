package imooc;
import java.util.ArrayList;
import java.util.List;
public class people {
    int ID;
	String name;
	List<card> handCards;
	public people(int ID,String name){
		this.ID=ID;
		this.name=name;
		handCards=new ArrayList<card>();
	}
	@Override
	public String toString() {		//重写toString方法便于输出手牌
		return name+"["+handCards.get(0).getColor()+handCards.get(0).getNum()+
				","+handCards.get(1).getColor()+handCards.get(1).getNum()+"]";
	}
}

