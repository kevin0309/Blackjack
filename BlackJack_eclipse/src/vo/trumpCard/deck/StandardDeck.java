package vo.trumpCard.deck;

import java.util.Stack;

import vo.suit.SuitType;
import vo.trumpCard.TrumpCardFactory;

/**
 * 일반적인 트럼프 한 세트 덱
 * @author LG
 *
 */
public class StandardDeck extends Deck {

	public StandardDeck() {
		deck = new Stack<>();
		for (int i = 1; i < 14; i++) { 
			deck.push(TrumpCardFactory.getInstance(SuitType.SPADE, i));
			deck.push(TrumpCardFactory.getInstance(SuitType.DIAMOND, i));
			deck.push(TrumpCardFactory.getInstance(SuitType.CLOVER, i));
			deck.push(TrumpCardFactory.getInstance(SuitType.HEART, i));
		}
	}

	@Override
	public DeckType getDeckType() {
		return DeckType.STANDARD;
	}
}
