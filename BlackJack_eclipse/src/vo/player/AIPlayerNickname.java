package vo.player;

import java.util.ArrayList;

/**
 * AI 플레이어의 닉네임을 저장하는 ENUM
 * @author LG
 *
 */
public enum AIPlayerNickname {
	AlphaGo, AI, Siri, Bixby, SkyNET, DeepMind, DeepBlue, GoogleBrain, MS_Cortana, ExoBrain, NierAutomata, RedQueen, ZAVIS, Omnic, GLaDOS;
	
	/**
	 * 위의 닉네임중 중복없이 amount 개의 닉네임을 뽑아 반환한다.
	 * @param amount
	 * @return
	 */
	public static ArrayList<String> getRandomNicknames(int amount) {
		ArrayList<String> res = new ArrayList<>();
		
		for (int i = 0; i < amount; i++) {
			while (true) {
				String newName = AIPlayerNickname.values()[(int)(Math.random()*values().length)].name();
				
				boolean chk = true;
				for (String oldName : res) {
					if (newName.equals(oldName))
						chk = false;
				}
				
				if (chk) {
					res.add(newName);
					break;
				}
			}
		}
		
		return res;
	}
}
