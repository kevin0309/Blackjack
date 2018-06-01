package blackJack.logic.playerAction;

import blackJack.logic.playerAction.impl.AIPlayerActionService;
import blackJack.logic.playerAction.impl.ConsolePlayerActionService;
import blackJack.logic.playerAction.impl.StandardDealerActionService;

/**
 * PlayerActionService 인스턴스를 생성하는 클래스
 * @author LG
 *
 */
public class PlayerActionServiceFactory {

	public static PlayerActionService getInstance(PlayerActionServiceType type) {
		switch (type) {
		case CONSOLE_PLAYER:
			return new ConsolePlayerActionService();
		case STANDARD_DEALER:
			return new StandardDealerActionService();
		case AI_PLAYER:
			return new AIPlayerActionService();
		default :
			return null;
		}
	}
}
