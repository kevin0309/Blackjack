package vo.trumpCard.deck;

import java.util.Stack;

import vo.suit.SuitType;
import vo.trumpCard.TrumpCardFactory;

/**
 * 스페이드로만 이루어진 4분의1 덱
 * @author LG
 *
 */
public class QuarterDeck extends Deck {

	public QuarterDeck() {
		deck = new Stack<>();
		for (int i = 1; i < 14; i++) 
			deck.push(TrumpCardFactory.getInstance(SuitType.SPADE, i));
	}
	
	@Override
	public DeckType getDeckType() {
		return DeckType.QUARTER;
	}
}
