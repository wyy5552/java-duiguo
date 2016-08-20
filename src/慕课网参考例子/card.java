package cardGame;
public class card implements Comparable<card> {
    private String color;
	private String num;
	/*
	 * 用两个String属性来表示颜色和数字，但是不方便比较因此添加两个
	 * Integer来帮助比较大霄
	 */
	Integer color_;           
	Integer num_;
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	@Override
	public int compareTo(card card) {//直接使用两个Integer的compareTo方法比较
		// TODO Auto-generated method stub
		if(this.num_!=card.num_)
			return this.num_.compareTo(card.num_);
		else{
			return this.color_.compareTo(card.color_);
		}
	}
}

