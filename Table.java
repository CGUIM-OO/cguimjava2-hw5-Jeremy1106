import java.util.ArrayList;

public class Table {
	final private int MaxPLAYER = 4; //class field
	private Deck deck; //�HDeck�����O���ܼ� �s��P
	private Player[] allplayers; //�s��Ҧ������a��array
	private Dealer dealer; //�s��@����a
	private int[] pos_betArray; //�s�C�Ӫ��a�U���`
	
	private ArrayList <Card> player1 = new ArrayList<Card>();	
	private ArrayList <Card> player2 = new ArrayList<Card>();
	private ArrayList <Card> player3 = new ArrayList<Card>();
	private ArrayList <Card> player4 = new ArrayList<Card>();
	private ArrayList <Card> dealercard = new ArrayList<Card>();
	
	public Table(int nDeck){ //Constructor
		Deck Locdeck = new Deck(nDeck); //�HDeck�����O�� �W��Locdeck
		deck = Locdeck; //��Ȧs�i�h
		allplayers = new Player[MaxPLAYER]; //�ŧi���׬�MaxPLAYER��Player array
		pos_betArray = new int[MaxPLAYER];
	}
	public void set_player(int pos,Player p){
		allplayers[pos] = p; //pos �Ʀr (�X����r) p  
	}
	public Player[] get_player(){
		return allplayers; //�^�Ǫ��a
	}
	public void set_Dealer(Dealer d){
		dealer = d;
	}
	public Card get_face_up_card_of_dealer(){
		ArrayList <Card> card = new ArrayList<Card>();
		card = dealer.getOneRoundCard(); //����a���P���card�o�ө�P��
		Card get = card.get(1); //��Ĥ@�i�P��쬰Card�����A���ܼ� get�̭�
		return get; //�^��get
	}
	private void ask_each_player_about_bets(){
		for(int i = 0; i < 4;i++){
			if(allplayers[i] != null){ //��p�G���a���O�Ū��°���U�����{���X
				Player players = allplayers[i]; //��̭������a���Player���A��players�ܼƸ̭�
				players.sayHello(); //�����a�����۩I���ʧ@
				players.makeBet(); //�����a���U�`���ʧ@
				pos_betArray[i] = players.makeBet(); //��o�ǽ�`���pos_betArray�̭�
			}
		}
		
	}
	private void distribute_cards_to_dealer_and_players(){ //�@�}�l�P���|�O�\�� �ҥH�o�P
		player1.add(deck.getOneCard(true)); //��P�ܦ�true �H��ܬO½�}��
		player1.add(deck.getOneCard(true)); 
		allplayers[0].setOneRoundCard(player1); //��o�ǵP��쪱�a�P�ժ�ArrayList�̭�
		
		player2.add(deck.getOneCard(true)); //�P�W
		player2.add(deck.getOneCard(true));
		allplayers[1].setOneRoundCard(player2);
		
		player3.add(deck.getOneCard(true));//�P�W
		player3.add(deck.getOneCard(true));
		allplayers[2].setOneRoundCard(player3);
		
		player4.add(deck.getOneCard(true));//�P�W
		player4.add(deck.getOneCard(true));
		allplayers[3].setOneRoundCard(player4);
		
		dealercard.add(deck.getOneCard(false)); //���a�O�@�}�@�\��  �]����ӭn���}�ۨ��i �ҥH�ĤG�i�O�}��
		dealercard.add(deck.getOneCard(true));
		dealer.setOneRoundCard(dealercard);
		
		System.out.println("Dealer's face up card is " + get_face_up_card_of_dealer().getSuit()+","+get_face_up_card_of_dealer().getRank());
	}
	private void ask_each_player_about_hits(){
		boolean hit = false; //�@�}�l�n�P�o�Ӱʧ@���|�O���n��
		for(int i = 0;i < 4;i++){
		do{
			hit = allplayers[i].hit_me(this); //this
			if(hit){
				ArrayList <Card> cd = allplayers[i].getOneRoundCard(); //���⪱�a���P��iArrayList�̭�
				cd.add(deck.getOneCard(true)); //�令�n�P
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
		
		//�i��|�J�쪺���p�O
		//���a�򪱮a���P���j��21 ���褣��
		//���a���P�񪱮a���P�j �B ���a�S���j��21 ���aĹ
		//���a���Ƥ񪱮a���P�j ���O���a���P�j��21 ���aĹ
		//���a���P�񪱮a���P�p �B ���a���P�S���j��21 ���aĹ
		//���a���P�񪱮a���P�p �����a���P�j��21 ���aĹ
		//���a�򪱮a���P�@�� ���褣��
		
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
