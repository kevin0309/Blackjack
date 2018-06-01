package vo.suit;

/**
 * 카드 모양의 종류에대한 정보를 저장하는 ENUM
 * @author LG
 *
 */
public enum SuitType {

	SPADE("♠", 1, false),
	DIAMOND("◇", 2, true),
	CLOVER("♣", 3, false),
	HEART("♡", 4, true);
	
	private String suitEmblem;
	private int suitCode;
	private boolean isRedColorCard;
	
	private SuitType(String suitEmblem, int suitCode, boolean isRedColorCard) {
		this.suitEmblem = suitEmblem;
		this.suitCode = suitCode;
		this.isRedColorCard = isRedColorCard;
	}

	public String getSuitEmblem() {
		return suitEmblem;
	}

	public int getSuitCode() {
		return suitCode;
	}

	public boolean isRedColorCard() {
		return isRedColorCard;
	}
	
}
