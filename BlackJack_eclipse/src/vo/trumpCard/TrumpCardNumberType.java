package vo.trumpCard;

/**
 * 트럼프카드의 종류에 대한 정보를 저장하는 ENUM
 * A는 1과 11이 모두 될 수 있으므로 따로 하나를 더 구현하였다.
 * @author LG
 *
 */
public enum TrumpCardNumberType {

	NUMBER_1("A", 1, 1),
	NUMBER_2("2", 2, 2),
	NUMBER_3("3", 3, 3),
	NUMBER_4("4", 4, 4),
	NUMBER_5("5", 5, 5),
	NUMBER_6("6", 6, 6),
	NUMBER_7("7", 7, 7),
	NUMBER_8("8", 8, 8),
	NUMBER_9("9", 9, 9),
	NUMBER_10("10", 10, 10),
	NUMBER_11("J", 11, 10),
	NUMBER_12("Q", 12, 10),
	NUMBER_13("K", 13, 10),
	NUMBER_SPECIAL_ACE("A", 1, 11);
	
	private String printChar;
	private int number;
	private int blackJackScore;
	
	private TrumpCardNumberType(String printChar, int number, int score) {
		this.printChar = printChar;
		this.number = number;
		this.blackJackScore = score;
	}

	public String getPrintChar() {
		return printChar;
	}

	public int getNumber() {
		return number;
	}
	
	public int getBlackJackScore() {
		return blackJackScore;
	}
}
