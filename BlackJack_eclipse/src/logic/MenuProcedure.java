package logic;

import blackJack.logic.BlackJackProcedure;
import blackJack.logic.playerAction.PlayerActionServiceFactory;
import blackJack.logic.playerAction.PlayerActionServiceType;
import blackJack.vo.BlackJackGameType;
import blackJack.vo.menuUI.menuData.EndOfGameOptions;
import blackJack.vo.player.BlackJackPlayer;
import main.Main;
import vo.menuUI.menuData.MainMenuOptions;
import vo.menuUI.menuData.YesNoOptions;
import vo.trumpCard.deck.DeckType;

/**
 * 메뉴에 대한 관리 및 실행로직을 구현한 클래스
 * @author LG
 *
 */
public class MenuProcedure {

	private BlackJackPlayer player;
	
	/**
	 * main메서드에서 실행하는 메서드 게임을 시작한다.
	 * @param isFirst
	 * @return 게임이 끝났으면 false 아니면 true 반환
	 */
	public boolean process(boolean isFirst) {
		if (isFirst) {
			printLogo(); //스플래시 화면을 출력
			String playerName = null;
			while (playerName == null)
				playerName = registerNewPlayer(); //플레이어 생성
			player = new BlackJackPlayer(playerName, 10000, PlayerActionServiceFactory.getInstance(PlayerActionServiceType.CONSOLE_PLAYER));
		}
		
		if (player.getMoney() == 0) //플레이어가 파산했을 경우
			return false;
		else if (processMenu(printMainMenu()) == MainMenuOptions.EXIT) //메뉴를 출력한 뒤 사용자의 요청을 받는다.
			return false;
		else
			return true;
	}
	
	/**
	 * 처음 실행 시 스플래시 화면을 출력
	 */
	private void printLogo() {
		Main.UI.screenCutaway();
		Main.UI.printLogo();
		Main.UI.printPressEnterToContinueMsg();
		Main.UI.screenCutaway();
	}
	
	/**
	 * 메인 메뉴를 출력한 뒤 사용자의 요청을 받는다.
	 * @return
	 */
	private MainMenuOptions printMainMenu() {
		Main.UI.printMainMenu();
		
		int command = 0;
		while (true) {
			String input = Main.UI.acceptPlayerCommand();
			try {
				command = Integer.parseInt(input);
			} catch (NumberFormatException e) {}
	
			if (command < 1 || command > MainMenuOptions.values().length)
				Main.UI.printAcceptDeniedMsg();
			else
				break;
		}
		
		return MainMenuOptions.values()[command-1];
	}
	
	/**
	 * 요청받은 메뉴를 실행한다.
	 * @param choosen
	 * @return
	 */
	private MainMenuOptions processMenu(MainMenuOptions choosen) {
		Main.UI.screenCutaway();
		switch (choosen) {
		case START: //게임 시작
			BlackJackProcedure bp = new BlackJackProcedure(BlackJackGameType.CASINO, player, DeckType.STANDARD);
			while (bp.startGame() != EndOfGameOptions.EXIT) {} //사용자가 게임을 끝내기 전까지 반복한다.
			break;
		case RULES: //룰에대한 설명 출력
			Main.UI.printRules();
			break;
		case SCORE_BOARD: //현재 플레이어의 게임 로그 출력
			player.printScoreRecords();
			Main.UI.printPressEnterToContinueMsg();
			Main.UI.screenCutaway();
			break;
		case EXIT: //게임 종료
		}
		
		return choosen;
	}
	
	/**
	 * 플레이어의 캐릭터를 생성한다.
	 * @return
	 */
	private String registerNewPlayer() {
		Main.UI.printAcceptPlayerNameMsg();
		String command = Main.UI.acceptPlayerCommand(); //플레이어에게서 이름을 입력받는다.
		Main.UI.printConfirmPlayerNameMsg(command);
		
		int command2 = 0;
		while (true) { //입력한 이름이 맞는지 한번 더 확인한다.
			String input = Main.UI.acceptPlayerCommand();
			try {
				command2 = Integer.parseInt(input);
			} catch (NumberFormatException e) {}
	
			if (command2 < 1 || command2 > YesNoOptions.values().length)
				Main.UI.printAcceptDeniedMsg();
			else
				break;
		}
		Main.UI.screenCutaway();
		if (command2 == 1)
			return command;
		else
			return null;
		
	}
}
