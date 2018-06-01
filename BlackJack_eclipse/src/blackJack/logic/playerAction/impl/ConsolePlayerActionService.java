package blackJack.logic.playerAction.impl;

import java.util.Stack;

import blackJack.logic.playerAction.PlayerActionService;
import blackJack.vo.menuUI.menuData.PlayerTurnCommandOptions;
import blackJack.vo.player.BlackJackPlayer;
import main.Main;
import vo.trumpCard.TrumpCard;
import vo.trumpCard.deck.DeckType;

/**
 * 실제 플레이하는 유저의 의사결정 클래스
 * @author LG
 *
 */
public class ConsolePlayerActionService implements PlayerActionService {

	@Override
	public PlayerTurnCommandOptions acceptTurnCommand(String playerName, int curScore, int dealerScore, int bettedMoney, DeckType deckType, Stack<TrumpCard> visibleCards) {
		int command = 0;
		while (true) { //플레이어의 의사를 입력받는다. hit or stay or surrender
			Main.UI.printAcceptPlayerTurnCommandMsg(playerName);
			String input = Main.UI.acceptPlayerCommand();
			try {
				command = Integer.parseInt(input);
			} catch (NumberFormatException e) {}
	
			if (command < 1 || command > PlayerTurnCommandOptions.values().length)
				Main.UI.printAcceptDeniedMsg();
			else
				break;
		}
		return PlayerTurnCommandOptions.values()[command-1];
	}

	@Override
	public void printCurHand(BlackJackPlayer player) {
		try {
			Main.UI.printHandPreview(false, false, player.getName(), player.getCurScore(), player.getBettedMoney(), player.getMoney());
		} catch (IndexOutOfBoundsException e) {
			Main.UI.printHandPreview(false, false, player.getName(), 0, player.getBettedMoney(), player.getMoney());
		}
		player.printCurHand(false);
	}

	@Override
	public int betMoney(BlackJackPlayer player) {
		int res = -1;
		while (true) {
			Main.UI.printPlayerBetMoneyMsg(player.getName(), player.getMoney());
			String input = Main.UI.acceptPlayerCommand();
			try {
				res = Integer.parseInt(input);
			} catch (NumberFormatException e) {}
	
			if (res < 1 || res > player.getMoney())
				Main.UI.printAcceptDeniedMsg();
			else
				break;
		}
		player.setBettedMoney(res);
		return res;
	}
	
	
}
