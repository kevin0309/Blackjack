package vo.trumpCard.deck;

import java.util.Stack;

import vo.suit.SuitType;
import vo.trumpCard.TrumpCardFactory;

/**
 * 스페이드와 클로버로만 이루어진 반쪽짜리 덱
 * @author LG
 *
 */
public class HalfDeck extends Deck {

	public HalfDeck() {
		deck = new Stack<>();
		for (int i = 1; i < 14; i++) { 
			deck.push(TrumpCardFactory.getInstance(SuitType.SPADE, i));
			deck.push(TrumpCardFactory.getInstance(SuitType.CLOVER, i));
		}
	}
	
	@Override
	public DeckType getDeckType() {
		return DeckType.HALF;
	}
}
