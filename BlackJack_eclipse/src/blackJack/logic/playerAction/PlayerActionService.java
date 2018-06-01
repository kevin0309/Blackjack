package blackJack.logic.playerAction;

import java.util.Stack;

import blackJack.vo.menuUI.menuData.PlayerTurnCommandOptions;
import blackJack.vo.player.BlackJackPlayer;
import vo.trumpCard.TrumpCard;
import vo.trumpCard.deck.DeckType;

/**
 * 각 플레이어가 의사결정하는 알고리즘을 구현하는 인터페이스
 * @author LG
 *
 */
public interface PlayerActionService {
	/**
	 * 각 플레이어의 턴에 대한 의사를 입력받는다.
	 * @return
	 */
	public PlayerTurnCommandOptions acceptTurnCommand(String playerName, int curScore, int dealerScore, int bettedMoney, DeckType deckType, Stack<TrumpCard> visibleCards);
	/**
	 * 현재 플레이어의 손패를 출력한다.
	 * @param player
	 */
	public void printCurHand(BlackJackPlayer player);
	/**
	 * 각 플레이어의 베팅금을 입력받는다.
	 * @param player
	 * @return
	 */
	public int betMoney(BlackJackPlayer player);
}
