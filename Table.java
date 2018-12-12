import java.util.ArrayList;

public class Table {
	final private int MaxPLAYER = 4; //class field
	private Deck deck; //以Deck為型別的變數 存放牌
	private Player[] allplayers; //存放所有的玩家的array
	private Dealer dealer; //存放一位莊家
	private int[] pos_betArray; //存每個玩家下的注
	
	private ArrayList <Card> player1 = new ArrayList<Card>();	
	private ArrayList <Card> player2 = new ArrayList<Card>();
	private ArrayList <Card> player3 = new ArrayList<Card>();
	private ArrayList <Card> player4 = new ArrayList<Card>();
	private ArrayList <Card> dealercard = new ArrayList<Card>();
	
	public Table(int nDeck){ //Constructor
		Deck Locdeck = new Deck(nDeck); //以Deck為型別的 名稱Locdeck
		deck = Locdeck; //把值存進去
		allplayers = new Player[MaxPLAYER]; //宣告長度為MaxPLAYER的Player array
		pos_betArray = new int[MaxPLAYER];
	}
	public void set_player(int pos,Player p){
		allplayers[pos] = p; //pos 數字 (幾號格字) p  
	}
	public Player[] get_player(){
		return allplayers; //回傳玩家
	}
	public void set_Dealer(Dealer d){
		dealer = d;
	}
	public Card get_face_up_card_of_dealer(){
		ArrayList <Card> card = new ArrayList<Card>();
		card = dealer.getOneRoundCard(); //把莊家的牌丟到card這個抽屜裡
		Card get = card.get(1); //把第一張牌丟到為Card的型態的變數 get裡面
		return get; //回傳get
	}
	private void ask_each_player_about_bets(){
		for(int i = 0; i < 4;i++){
			if(allplayers[i] != null){ //當如果玩家不是空的舊執行下面的程式碼
				Player players = allplayers[i]; //把裡面的玩家丟到Player型態的players變數裡面
				players.sayHello(); //讓玩家做打招呼的動作
				players.makeBet(); //讓玩家做下注的動作
				pos_betArray[i] = players.makeBet(); //把這些賭注丟到pos_betArray裡面
			}
		}
		
	}
	private void distribute_cards_to_dealer_and_players(){ //一開始牌都會是蓋著 所以發牌
		player1.add(deck.getOneCard(true)); //把牌變成true 以表示是翻開的
		player1.add(deck.getOneCard(true)); 
		allplayers[0].setOneRoundCard(player1); //把這些牌丟到玩家牌組的ArrayList裡面
		
		player2.add(deck.getOneCard(true)); //同上
		player2.add(deck.getOneCard(true));
		allplayers[1].setOneRoundCard(player2);
		
		player3.add(deck.getOneCard(true));//同上
		player3.add(deck.getOneCard(true));
		allplayers[2].setOneRoundCard(player3);
		
		player4.add(deck.getOneCard(true));//同上
		player4.add(deck.getOneCard(true));
		allplayers[3].setOneRoundCard(player4);
		
		dealercard.add(deck.getOneCard(false)); //莊家是一開一蓋的  因為後來要取開著那張 所以第二張是開著
		dealercard.add(deck.getOneCard(true));
		dealer.setOneRoundCard(dealercard);
		
		System.out.println("Dealer's face up card is " + get_face_up_card_of_dealer().getSuit()+","+get_face_up_card_of_dealer().getRank());
	}
	private void ask_each_player_about_hits(){
		boolean hit = false; //一開始要牌這個動作都會是不要的
		for(int i = 0;i < 4;i++){
		do{
			hit = allplayers[i].hit_me(this); //this
			if(hit){
				ArrayList <Card> cd = allplayers[i].getOneRoundCard(); //先把玩家的牌丟進ArrayList裡面
				cd.add(deck.getOneCard(true)); //改成要牌
				allplayers[i].setOneRoundCard(cd);
				System.out.print("Hit!");
				System.out.println(allplayers[i].getName()+"'s Cards now:");
				for(Card c : cd){
					c.printCard();
				}
			}
			else{
				System.out.println(allplayers[i].getName()+", Pass hit!");
				System.out.println(allplayers[i].getName()+", Final Card:");
				for(Card c : allplayers[i].getOneRoundCard()){
					c.printCard();
				}
			}
		}while(hit);
	}
	}
	private void ask_dealer_about_hits(){
		boolean hit = false;
		do{
			hit = dealer.hit_me(this); //this
			if(hit){
				dealercard.add(deck.getOneCard(true));
				dealer.setOneRoundCard(dealercard);
				System.out.print("Hit! ");
				System.out.println("Dealer's Cards now:");
				for(Card c : dealercard){
					c.printCard();
				}
			}
			else{
				System.out.println("Dealer, Pass hit!");
				System.out.println("Dealer, Final Card:");
				for(Card c : dealercard){
					c.printCard();
				}
			}
		}while(hit);
		System.out.println("Dealer's hit is over!");
	}
	private void calculate_chips(){
		System.out.println("Dealer's card value is "+dealer.getTotalValue()+",Cards");
		dealer.printAllCard();
		
		//可能會遇到的情況是
		//莊家跟玩家的牌都大於21 雙方不變
		//莊家的牌比玩家的牌大 且 莊家沒有大於21 莊家贏
		//莊家的排比玩家的牌大 但是莊家的牌大於21 玩家贏
		//莊家的牌比玩家的牌小 且 玩家的牌沒有大於21 玩家贏
		//莊家的牌比玩家的牌小 但玩家的牌大於21 莊家贏
		//莊家跟玩家的牌一樣 雙方不變
		
		for(int i = 0;i < allplayers.length;i++){
			System.out.println(allplayers[i].getName()+" card value is "+allplayers[i].getTotalValue());
			
			if(allplayers[i].getTotalValue() > 21 && dealer.getTotalValue() > 21){
				System.out.println(",chips have no change! The chips now is  " + allplayers[i].getCurrentChips());
			}
			
			else if(dealer.getTotalValue() > allplayers[i].getTotalValue() && dealer.getTotalValue() <= 21){
				int chips = allplayers[i].getCurrentChips();
				chips -= allplayers[i].makeBet();
				allplayers[i].setchips(chips);
				 System.out.println(",Loss "+allplayers[i].makeBet()+" Chips, the Chips now is: "+chips);
			}
			
			else if(dealer.getTotalValue() > allplayers[i].getTotalValue() && dealer.getTotalValue() > 21){
				int chips = allplayers[i].getCurrentChips();
				chips += allplayers[i].makeBet();
				allplayers[i].setchips(chips);
				 System.out.println(",Get "+allplayers[i].makeBet()+" Chips, the Chips now is: "+chips);
			}
			
			else if(dealer.getTotalValue() < allplayers[i].getTotalValue() && allplayers[i].getTotalValue() <= 21){
				int chips = allplayers[i].getCurrentChips();
				chips += allplayers[i].makeBet();
				allplayers[i].setchips(chips);
				 System.out.println(",Get "+allplayers[i].makeBet()+" Chips, the Chips now is: "+chips);
			}
			
			else if(dealer.getTotalValue() < allplayers[i].getTotalValue() && allplayers[i].getTotalValue() > 21){
				int chips = allplayers[i].getCurrentChips();
				chips -= allplayers[i].makeBet();
				allplayers[i].setchips(chips);
				 System.out.println(",Loss "+allplayers[i].makeBet()+" Chips, the Chips now is: "+chips);
			}
			
			else if(dealer.getTotalValue() == allplayers[i].getTotalValue()){
				System.out.println(",chips have no change! The Chips now is:  "+allplayers[i].getCurrentChips());
			}
		}
	}
	public int[]get_players_bet(){
		return pos_betArray;
	}
	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}

}
