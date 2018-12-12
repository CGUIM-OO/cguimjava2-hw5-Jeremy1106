import java.util.ArrayList;
import java.util.Random;

public class Deck {
		private ArrayList<Card> openCard; //�s�WopenCard�o��ArrayList ��ܤw�g���}�L���P
		private ArrayList<Card> cards;
		//TODO: Please implement the constructor (30 points)
		
		public ArrayList<Card> usedCard;
		public int nUsed;
		Random ran = new Random();	

		public Deck(int nDeck){
			cards=new ArrayList<Card>();
			usedCard = new ArrayList<Card>();
			openCard = new ArrayList<Card>();
			//1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
			//Hint: Use new Card(x,y) and 3 for loops to add card into deck
			//Sample code start
			//Card card=new Card(1,1); ->means new card as clubs ace
			//cards.add(card);
			//Sample code end
			
			for(int x=1;x<=nDeck;x++){
				for(int y=1;y<5;y++){  
					for(int z=1;z<14;z++){  
						if(y==1){
						Card card=new Card(Card.Suit.Club,z);
						cards.add(card);
						}
						else if(y==2){
						Card card=new Card(Card.Suit.Diamond,z);
						cards.add(card);
						}
						else if(y==3){
						Card card=new Card(Card.Suit.Heart,z);
						cards.add(card);
						}
						else{
						Card card=new Card(Card.Suit.Spade,z);
						cards.add(card);
						}
						}
					}
				}
			shuffle();
			}
		//TODO: Please implement the method to print all cards on screen (10 points)
		public void printDeck(){
			//Hint: print all items in ArrayList<Card> cards, 
			//TODO: please implement and reuse printCard method in Card class (5 points)
			for(int i=0;i<cards.size();i++){
				Card j=cards.get(i);
				j.printCard();
			}
		}
		public ArrayList<Card> getAllCards(){
			return cards;
		}
		
		
		public void shuffle() {
			openCard.clear();//���sOpenCard�o��ArrayList
			nUsed = 0; 
			cards.addAll(usedCard);
			usedCard.clear();
			
		    for(int i = 0;i < cards.size() ;i++) {
			    int loc = ran.nextInt(cards.size());
			    Card val = cards.get(loc);
			    cards.set(loc,cards.get(i));
			    cards.set(i,val);
			   }
			 }
		
		public Card getOneCard(boolean isOpened){
			if(cards.size()==0){
				shuffle();
			}
			Card cardval = cards.get(0);
			cards.remove(0);
			usedCard.add(cardval);
			
			if(isOpened=true){//�p�G�o�i�P�O½�}��
				openCard.add(cardval);//�h�N�o�i�d�[�J��openCard�o��ArrayList�̭�
			}
			nUsed++;
			return cardval;
			}
		 public ArrayList<Card> getOpenedCard(){
			 return openCard;//�^�ǳo�ƵP���}�L���P
		 }

	}
