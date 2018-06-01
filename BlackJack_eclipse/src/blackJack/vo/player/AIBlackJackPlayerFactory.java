package blackJack.vo.player;

import java.util.ArrayList;

import blackJack.logic.playerAction.PlayerActionServiceFactory;
import blackJack.logic.playerAction.PlayerActionServiceType;
import vo.player.AIPlayerNickname;

/**
 * AIPlayerNickname에 등록된 무작위 닉네임을 갖고,
 * 5000~15000 사이의 자금을 소유한 AI 플레이어를 생성하는 클래스
 * @author LG
 *
 */
public class AIBlackJackPlayerFactory {

	/**
	 * AIPlayerNickname에 등록된 무작위 닉네임을 갖고,
	 * 5000~15000 사이의 자금을 소유한 AI 플레이어를 생성한다.
	 * @return
	 */
	public static ArrayList<BlackJackPlayer> getInstances(int amount) {
		ArrayList<BlackJackPlayer> res = new ArrayList<>();
		ArrayList<String> nickNames = AIPlayerNickname.getRandomNicknames(amount);
		for (int i = 0; i < amount; i++) {
			int money = ((int)(Math.random()*11))*1000+5000;
			res.add(new BlackJackPlayer(nickNames.get(i), money, PlayerActionServiceFactory.getInstance(PlayerActionServiceType.AI_PLAYER)));
		}
		return res;
	}
}
