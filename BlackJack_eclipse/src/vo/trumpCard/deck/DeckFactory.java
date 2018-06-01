package vo.trumpCard.deck;

/**
 * DeckType에 저장된 정보로 새로운 Deck 인스턴스를 생성하는 클래스
 * @author LG
 *
 */
public class DeckFactory {

	public static Deck getInstance(DeckType type) {
		switch (type) {
		case STANDARD:
			return new StandardDeck();
		case HALF:
			return new HalfDeck();
		case QUARTER:
			return new QuarterDeck();
		}
		return null;
	}
}
