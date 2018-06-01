package vo.trumpCard.deck;

import java.util.Stack;

import vo.trumpCard.TrumpCard;

/**
 * TrumCard의 집합인 카드덱을 구현하는 abstract class
 * @author LG
 *
 */
public abstract class Deck {

	protected Stack<TrumpCard> deck;
	
	/**
	 * 덱의 타입정보를 반환한다.
	 * @return
	 */
	public abstract DeckType getDeckType();
	
	/**
	 * 덱을 셔플한다.
	 * 랜덤한 인덱스의 Stack 멤버를 뽑아 서로 인덱스를 바꾸는 행위를 (덱에 있는 카드의 수 * 30)번 반복한다.
	 */
	public void shuffle() {
		for (int i = 0; i < deck.size()*30; i++) { 
			int r1 = (int)(Math.random()*deck.size());
			int r2 = (int)(Math.random()*deck.size());
			TrumpCard temp = deck.get(r1);
			deck.set(r1, deck.get(r2));
			deck.set(r2, temp);
		}
	}
	
	/**
	 * 덱에서 한장을 뽑는다.
	 * @return
	 */
	public TrumpCard drawCard() {
		return deck.pop();
	}
	
	public Stack<TrumpCard> getDeck() {
		return deck;
	}
}
