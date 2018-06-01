package vo.trumpCard.deck;

/**
 * 덱의 종류에대한 정보를 저장하는 ENUM
 * @author LG
 *
 */
public enum DeckType {
	STANDARD(52), HALF(26), QUARTER(13);
	
	private int cardCnt;
	
	private DeckType(int cardCnt) {
		this.cardCnt = cardCnt;
	}

	public int getCardCnt() {
		return cardCnt;
	}
}
