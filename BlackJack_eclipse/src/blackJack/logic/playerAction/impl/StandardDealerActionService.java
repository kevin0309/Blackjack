package blackJack.logic.playerAction.impl;

import java.util.Stack;

import blackJack.logic.playerAction.PlayerActionService;
import blackJack.vo.menuUI.menuData.PlayerTurnCommandOptions;
import blackJack.vo.player.BlackJackPlayer;
import main.Main;
import vo.trumpCard.TrumpCard;
import vo.trumpCard.deck.DeckType;

/**
 * 딜러의 의사결정 클래스
 * @author LG
 *
 */
public class StandardDealerActionService implements PlayerActionService {

	@Override
	public PlayerTurnCommandOptions acceptTurnCommand(String playerName, int curScore, int dealerScore, int bettedMoney, DeckType deckType, Stack<TrumpCard> visibleCards) {
		if (curScore <= 16) {
			Main.UI.printAcceptDealerTurnCommandMsg(playerName, true);
			return PlayerTurnCommandOptions.HIT;
		}
		else {
			Main.UI.printAcceptDealerTurnCommandMsg(playerName, false);
			return PlayerTurnCommandOptions.STAY;
		}
	}

	@Override
	public void printCurHand(BlackJackPlayer player) {
		try {
			Main.UI.printHandPreview(true, true, player.getName(), player.getCurScore()-player.getHand().get(0).getNumber().getBlackJackScore(), player.getBettedMoney(), player.getMoney());
		} catch (IndexOutOfBoundsException e) {
			Main.UI.printHandPreview(true, true, player.getName(), 0, player.getBettedMoney(), player.getMoney());
		}
		player.printCurHand(true);
	}

	@Override
	public int betMoney(BlackJackPlayer player) {
		// TODO Auto-generated method stub
		return 0;
	}

}
