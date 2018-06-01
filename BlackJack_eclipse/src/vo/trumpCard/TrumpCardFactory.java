package vo.trumpCard;

import vo.suit.Suit;
import vo.suit.SuitType;

/**
 * TrumCard 인스턴스를 생성해 주는 클래스
 * @author LG
 *
 */
public class TrumpCardFactory {

	public static TrumpCard getInstance(SuitType suitType, int number) {
		return new TrumpCard(new Suit(suitType), TrumpCardNumberType.valueOf("NUMBER_"+number));
	}
}
