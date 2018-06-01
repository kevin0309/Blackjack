package vo.player;

import java.util.ArrayList;
import java.util.UUID;

import vo.trumpCard.TrumpCard;

/**
 * 기본적인 플레이어의 정보만을 담는 클래스
 * @author LG
 *
 */
public class CardGamePlayer {

	protected String name;
	protected ArrayList<TrumpCard> hand;
	protected int money;
	protected String uuid;
	
	public CardGamePlayer(String name, int money) {
		super();
		this.name = name;
		this.hand = new ArrayList<>();
		this.money = money;
		this.uuid = UUID.randomUUID().toString(); //중복되지 않는 랜덤한 ID를 생성한다.
	}

	public ArrayList<TrumpCard> getHand() {
		return hand;
	}

	public void setHand(ArrayList<TrumpCard> hand) {
		this.hand = hand;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getName() {
		return name;
	}
	
	public String getId() {
		return uuid;
	}
	
}
