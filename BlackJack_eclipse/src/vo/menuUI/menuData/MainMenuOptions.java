package vo.menuUI.menuData;

/**
 * 메인메뉴에 나타날 메뉴 옵션을 저장하는 ENUM
 * @author LG
 *
 */
public enum MainMenuOptions {
	
	START("1. Start BlackJack"),
	RULES("2. How to play?"),
	SCORE_BOARD("3. Score board"),
	EXIT("4. Exit");

	private String optionMsg;
	
	private MainMenuOptions(String optionMsg) {
		this.optionMsg = optionMsg;
	}

	public String getOptionMsg() {
		return optionMsg;
	}
	
	public static String getFullOptionsToString() {
		String res = "";
		for (MainMenuOptions option : MainMenuOptions.values())
			res+=option.getOptionMsg()+" ";
		return res;
	}
}
