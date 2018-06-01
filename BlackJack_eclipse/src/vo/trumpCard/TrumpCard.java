package vo.trumpCard;

import vo.suit.Suit;

/**
 * 한 장의 트럼프카드를 구현한 클래스
 * @author LG
 *
 */
public class TrumpCard {

	private Suit suit;
	private TrumpCardNumberType number;
	
	public TrumpCard(Suit suit, TrumpCardNumberType number) {
		this.suit = suit;
		this.number = number;
	}

	public Suit getSuit() {
		return suit;
	}
	
	public TrumpCardNumberType getNumber() {
		return number;
	}
	
	@Override
	public String toString() {
		return "["+suit.getSuitType().getSuitEmblem()+" "+number.getPrintChar()+"]";
	}
	
	/**
	 * Ace카드의 점수를 전환한다. 1 / 11
	 * @throws NotAceCardException
	 */
	public void processAceRule() throws NotAceCardException {
		if (this.number.getBlackJackScore() == TrumpCardNumberType.NUMBER_1.getBlackJackScore())
			this.number = TrumpCardNumberType.NUMBER_SPECIAL_ACE;
		else if (this.number.getBlackJackScore() == TrumpCardNumberType.NUMBER_SPECIAL_ACE.getBlackJackScore())
			this.number = TrumpCardNumberType.NUMBER_1;
		else
			throw new NotAceCardException();
	}
	
	/**
	 * 같은카드인지 아닌지 비교한다.
	 * @param card
	 * @return
	 */
	public boolean contrastCard(TrumpCard card) {
		if (this.number.getPrintChar().equals(card.getNumber().getPrintChar()) && this.suit.getSuitType() == card.suit.getSuitType())
			return true;
		else
			return false;
	}
}
