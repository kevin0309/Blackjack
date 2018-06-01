package blackJack.vo.menuUI.menuData;

/**
 * 게임이 끝났을때 출력할 옵션을 저장하는 Enum
 * @author LG
 *
 */
public enum EndOfGameOptions {

	RETRY("1. Retry"),
	EXIT("2. Return to main menu");
	
	private String optionMsg;
	
	private EndOfGameOptions(String optionMsg) {
		this.optionMsg = optionMsg;
	}

	public String getOptionMsg() {
		return optionMsg;
	}
	
	public static String getFullOptionsToString() {
		String res = "";
		for (EndOfGameOptions option : EndOfGameOptions.values())
			res+=option.getOptionMsg()+" ";
		return res;
	}
}
