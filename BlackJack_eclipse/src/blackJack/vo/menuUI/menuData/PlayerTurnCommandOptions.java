package blackJack.vo.menuUI.menuData;

/**
 * 플레이어의 턴에 출력할 옵션을 저장하는 Enum
 * @author LG
 *
 */
public enum PlayerTurnCommandOptions {

	HIT("1. Hit"),
	STAY("2. Stay"),
	SURRENDER("3. Surrender");
	
	private String optionMsg;
	
	private PlayerTurnCommandOptions(String optionMsg) {
		this.optionMsg = optionMsg;
	}

	public String getOptionMsg() {
		return optionMsg;
	}
	
	public static String getFullOptionsToString() {
		String res = "";
		for (PlayerTurnCommandOptions option : PlayerTurnCommandOptions.values())
			res+=option.getOptionMsg()+" ";
		return res;
	}
}
