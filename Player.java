import java.util.ArrayList;

public class Player extends Person{
	private String name; //玩家姓名
	private int chips; //現有的籌碼
	private int bet; //下注的籌碼
	private ArrayList<Card> oneRoundCard;
	public Player(String name, int chips){
		this.name = name;//想要取得玩家姓名以及籌碼的時候
		this.chips = chips;
	}
	public String getName(){
		return name;//想要取得玩家的名稱
	}
	public int makeBet(){//回傳預計下注的籌碼
		if(chips>0){//當籌碼大於零的時候，就進行下注
			bet = 1;
			return bet;
		}
		else{//當沒有籌碼的時候，救回傳0
			return 0;
		}
	}
	public void setOneRoundCard(ArrayList<Card> cards){
		oneRoundCard=cards;//設定此牌局所得到的卡
	}
	public boolean hit_me(Table table){//看是否要要牌
		 int total=0;
		 for(int i=0;i<oneRoundCard.size();i++){//利用在oneRoundCard這個ArrayList的size去跑迴圈
			 Card c = oneRoundCard.get(i);
			 total += c.getRank();//亦即total=total=c.getRank()
		 }
		 
		 if(total<=16){//當計算出的值小於等於16的時候，就進行要牌
			 return true;
		 }
		 else{ //如果不是小於等於16(也就是大於等於17)時，就停止要牌
			 return false;
		 }
	}
	public int getTotalValue(){//跟hitMe是一樣的方式
		int total = 0; 
		for(int i=0;i<oneRoundCard.size();i++){
			 Card c = oneRoundCard.get(i);
			 total += c.getRank();
		 }
		return total;//回傳算出來的值
	}
	 public int getCurrentChips(){//回傳玩家現有的籌碼
		 return chips;
	 }
	 public void increaseChips(int diff){//玩家籌碼變動
		 chips += diff;
	 }
	 public void sayHello(){//跟玩家say hello
		 System.out.println("Hello, I am " + name + ".");
		 System.out.println("I have " + chips + " chips.");

	 }
	public void setchips(int chips2) {
		chips=chips2;
		
	}
}
