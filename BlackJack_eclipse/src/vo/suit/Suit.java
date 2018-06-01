package vo.suit;

/**
 * 카드의 모양을 구현한 클래스
 * @author LG
 *
 */
public class Suit {
	
	/**
	 * 카드의 모양을 담아둔 attribute
	 */
	private SuitType suitType;
	
	public Suit(SuitType suitType) {
		super();
		this.suitType = suitType;
	}

	/**
	 * 두 모양 중 어떤게 점수가 높은지 비교한다.
	 * 점수가 높은 순서 : 스페이드 - 다이아몬드 - 하트 - 클로버
	 * @param suit
	 * @return
	 */
	public boolean compare(Suit suit) {
		return suit.suitType.getSuitCode() > this.suitType.getSuitCode();
	}

	public SuitType getSuitType() {
		return suitType;
	}
	
}
