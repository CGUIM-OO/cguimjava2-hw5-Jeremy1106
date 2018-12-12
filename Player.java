import java.util.ArrayList;

public class Player extends Person{
	private String name; //���a�m�W
	private int chips; //�{�����w�X
	private int bet; //�U�`���w�X
	private ArrayList<Card> oneRoundCard;
	public Player(String name, int chips){
		this.name = name;//�Q�n���o���a�m�W�H���w�X���ɭ�
		this.chips = chips;
	}
	public String getName(){
		return name;//�Q�n���o���a���W��
	}
	public int makeBet(){//�^�ǹw�p�U�`���w�X
		if(chips>0){//���w�X�j��s���ɭԡA�N�i��U�`
			bet = 1;
			return bet;
		}
		else{//��S���w�X���ɭԡA�Ϧ^��0
			return 0;
		}
	}
	public void setOneRoundCard(ArrayList<Card> cards){
		oneRoundCard=cards;//�]�w���P���ұo�쪺�d
	}
	public boolean hit_me(Table table){//�ݬO�_�n�n�P
		 int total=0;
		 for(int i=0;i<oneRoundCard.size();i++){//�Q�ΦboneRoundCard�o��ArrayList��size�h�]�j��
			 Card c = oneRoundCard.get(i);
			 total += c.getRank();//��Ytotal=total=c.getRank()
		 }
		 
		 if(total<=16){//��p��X���Ȥp�󵥩�16���ɭԡA�N�i��n�P
			 return true;
		 }
		 else{ //�p�G���O�p�󵥩�16(�]�N�O�j�󵥩�17)�ɡA�N����n�P
			 return false;
		 }
	}
	public int getTotalValue(){//��hitMe�O�@�˪��覡
		int total = 0; 
		for(int i=0;i<oneRoundCard.size();i++){
			 Card c = oneRoundCard.get(i);
			 total += c.getRank();
		 }
		return total;//�^�Ǻ�X�Ӫ���
	}
	 public int getCurrentChips(){//�^�Ǫ��a�{�����w�X
		 return chips;
	 }
	 public void increaseChips(int diff){//���a�w�X�ܰ�
		 chips += diff;
	 }
	 public void sayHello(){//�򪱮asay hello
		 System.out.println("Hello, I am " + name + ".");
		 System.out.println("I have " + chips + " chips.");

	 }
	public void setchips(int chips2) {
		chips=chips2;
		
	}
}
