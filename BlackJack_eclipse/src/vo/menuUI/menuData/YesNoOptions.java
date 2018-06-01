package vo.menuUI.menuData;

/**
 * yes or no 옵션을 저장하는 ENUM
 * @author LG
 *
 */
public enum YesNoOptions {
	
	YES("1. Yes"),
	NO("2. No");

	private String optionMsg;
	
	private YesNoOptions(String optionMsg) {
		this.optionMsg = optionMsg;
	}

	public String getOptionMsg() {
		return optionMsg;
	}
	
	public static String getFullOptionsToString() {
		String res = "";
		for (YesNoOptions option : YesNoOptions.values())
			res+=option.getOptionMsg()+" ";
		return res;
	}
}
