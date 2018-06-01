package blackJack.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.Stack;

import blackJack.logic.playerAction.impl.StandardDealerActionService;
import blackJack.vo.BlackJackGameType;
import blackJack.vo.menuUI.menuData.EndOfGameOptions;
import blackJack.vo.menuUI.menuData.PlayerTurnCommandOptions;
import blackJack.vo.player.AIBlackJackPlayerFactory;
import blackJack.vo.player.BlackJackPlayer;
import blackJack.vo.player.BlackJackPlayerStatus;
import blackJack.vo.player.BlackJackScoreRecord;
import main.Main;
import util.WindowsConsoleUtil;
import vo.trumpCard.TrumpCard;
import vo.trumpCard.deck.Deck;
import vo.trumpCard.deck.DeckFactory;
import vo.trumpCard.deck.DeckType;

/**
 * 한 판의 블랙잭 게임에 대한 관리 및 실행 로직을 구현한 클래스
 * @author LG
 *
 */
public class BlackJackProcedure {

	private DeckType deckType;
	private Deck deck;
	private Stack<TrumpCard> visibleCards;
	private BlackJackPlayer playerCharacter;
	private BlackJackPlayer dealer;
	private ArrayList<BlackJackPlayer> players;
	
	public BlackJackProcedure(BlackJackGameType gameType, BlackJackPlayer playerCharacter, DeckType deckType) {
		this.players = new ArrayList<>();
		dealer = new BlackJackPlayer("Dealer", Integer.MAX_VALUE, new StandardDealerActionService()); //딜러를 생성한다.
		this.players.add(dealer);

		int command = -1;
		while (true) { //몇 명의 AI 플레이어를 추가할지 입력을 받는다.
			Main.UI.printAIAddMsg();
			String input = Main.UI.acceptPlayerCommand();
			try {
				command = Integer.parseInt(input);
			} catch (NumberFormatException e) {}
	
			if (command < 0 || command > 6)
				Main.UI.printAcceptDeniedMsg();
			else
				break;
		}
		ArrayList<BlackJackPlayer> newChallengers = AIBlackJackPlayerFactory.getInstances(command); //플레이어가 입력한 숫자만큼 새로운 AI 플레이어를 생성한다.
		for (BlackJackPlayer newAI : newChallengers) {
			Main.UI.printNewAIAddMsg(newAI.getName());
			this.players.add(newAI);
		}
		this.players.add(playerCharacter);
		this.playerCharacter = playerCharacter;
		this.deckType = deckType;
	}
	
	public Stack<TrumpCard> getVisibleCards() {
		return visibleCards;
	}
	
	/**
	 * 전체 게임 프로세스의 시작, 진행하는 메서드
	 * 게임이 끝난 뒤 사용자가 입력한 메뉴번호 리턴
	 * @return
	 */
	public EndOfGameOptions startGame() {
		setBeginning();
		while(!detGameEnd())
			processTurn();
		calcResult();
		Main.UI.printResult(dealer, players);
		addLog();
		return endGame();
	}
	
	/**
	 * 1. 덱을 초기화 하고 셔플을 한다.
	 * 2. 베팅을 받는다.
	 * 3. 플레이어 플레이정보를 초기화하고 2장씩 카드를 받는다.
	 */
	private void setBeginning() {
		Main.UI.screenCutaway();
		Main.UI.printBettingPhaseTitle();
		deck = DeckFactory.getInstance(deckType); //덱을 초기화
		visibleCards = new Stack<>(); //보이는 카드 집합 초기화
		deck.shuffle(); //덱 셔플
		betMoney(); //베팅금액을 받음
		Main.UI.printBetPhaseFinishedMsg();
		for (BlackJackPlayer player : players) //각 플레이어의 핸드 초기화
			player.setHand(new ArrayList<>());
		for (BlackJackPlayer player : players) {
			hit(player); //첫장을 받음
			player.setStatus(BlackJackPlayerStatus.NORMAL); //status 초기화
			player.setPrize(0); //획득한 금액 초기화
			WindowsConsoleUtil.clear();
			printAllPlayersHand();
			WindowsConsoleUtil.sleep(500);
		}
		for (BlackJackPlayer player : players) {
			visibleCards.push(hit(player)); //두번째 장을 받음
			//두번째장까지의 점수 합이 21일 경우 블랙잭으로 상태 변경
			if (player.getHand().get(0).getNumber().getBlackJackScore() + player.getHand().get(1).getNumber().getBlackJackScore() == 21)
				player.setStatus(BlackJackPlayerStatus.BLACK_JACK);
			WindowsConsoleUtil.clear();
			printAllPlayersHand();
			WindowsConsoleUtil.sleep(500);
		}
	}
	
	private void betMoney() {
		for (BlackJackPlayer player : players) {
			player.getPlayerActionService().betMoney(player);
		}
	}
	
	/**
	 * 각각의 플레이어별로 턴을 진행한다.
	 */
	private void processTurn() {
		for (BlackJackPlayer player : players) {
			if (player.getStatus() == BlackJackPlayerStatus.SURRENDER) { //플레이어가 surrender상태일 경우 건너뛴다.
				Main.UI.printPlayerSurrenderMsg(player.getName());
				if (detGameEnd())
					break;
				printAllPlayersHand();
				continue;
			}
			PlayerTurnCommandOptions command = player.getPlayerActionService().acceptTurnCommand(player.getName(), player.getCurScore(), dealer.getCurScore()-dealer.getHand().get(0).getNumber().getBlackJackScore(), player.getBettedMoney(), deckType, visibleCards);
			switch (command) {
			case HIT:
				if (player.getCurScore() < 22) {
					visibleCards.push(hit(player));
					if (player.getCurScore() > 21) //한장을 받아 21보다 카드합이 커졌을 경우 상태를 bust로 변경한다.
						player.setStatus(BlackJackPlayerStatus.BUST);
					else //그렇지 않을경우 상태를 normal로 변경한다.
						player.setStatus(BlackJackPlayerStatus.NORMAL);
				}
				else { //이미 bust인 상태에서 hit 요청 시 요청을 거부하고 bust_stay로 상태를 변경한다.
					player.setStatus(BlackJackPlayerStatus.BUST_STAY);
					Main.UI.printAlreadyBustedMsg();
				}
				break;
			case STAY:
				if (player.getStatus() == BlackJackPlayerStatus.BUST) //이미 bust일 때 bust_stay로 상태를 변경한다.
					player.setStatus(BlackJackPlayerStatus.BUST_STAY);
				else if (player.getStatus() == BlackJackPlayerStatus.NORMAL) //blackjack일 경우 덮어씌워질 수 있으므로 normal일때 stay로 상태를 변경한다.
					player.setStatus(BlackJackPlayerStatus.STAY);
				break;
			case SURRENDER:
				if (player.getStatus() == BlackJackPlayerStatus.BUST) { //이미 bust인 상태에서 surrender 요청 시 요청을 거부하고 bust_stay로 상태를 변경한다.
					player.setStatus(BlackJackPlayerStatus.BUST_STAY);
					Main.UI.printAlreadyBustedMsg();
				}
				else //surrender로 상태를 변경한다.
					player.setStatus(BlackJackPlayerStatus.SURRENDER);
				break;
			}
			Main.UI.screenCutaway();
			if (detGameEnd())
				break;
			printAllPlayersHand();
		}
	}
	
	/**
	 * 게임이 끝났는지 판별한다.
	 * 모든 플레이어가 normal이나 bust가 아니어야 한다.
	 * @return
	 */
	private boolean detGameEnd() {
		int cnt = 0;
		for (BlackJackPlayer player : players) 
			if (player.getStatus() != BlackJackPlayerStatus.NORMAL && player.getStatus() != BlackJackPlayerStatus.BUST)
				cnt++;
		if (cnt == players.size())
			return true;
		else
			return false;
	}
	
	/**
	 * 결과를 계산한다.
	 */
	private void calcResult() {
		boolean isDealerBusted = false;
		if (dealer.getStatus() == BlackJackPlayerStatus.BUST_STAY)
			isDealerBusted = true;
		boolean isDealerBlackJack = false;
		if (dealer.getStatus() == BlackJackPlayerStatus.BLACK_JACK)
			isDealerBlackJack = true;
		
		for (BlackJackPlayer player : players) {
			boolean isPlayerSurrendered = false;
			if (player.getStatus() == BlackJackPlayerStatus.SURRENDER)
				isPlayerSurrendered = true;
			boolean isPlayerBusted = false;
			if (player.getStatus() == BlackJackPlayerStatus.BUST_STAY)
				isPlayerBusted = true;
			boolean isPlayerBlackJack = false;
			if (player.getStatus() == BlackJackPlayerStatus.BLACK_JACK)
				isPlayerBlackJack = true;
			
			if (isPlayerSurrendered) { //플레이어가 surrender인 경우
				player.setPrize(player.getBettedMoney()/2);
			}
			else {
				if (isPlayerBusted) { //플레이어가 bust인 경우 패배
					player.setStatus(BlackJackPlayerStatus.BUST);
					player.setPrize(0);
				}
				else { //플레이어가 bust가 아닐 때
					if (isDealerBusted) { //딜러가 bust인 경우
						if (isPlayerBlackJack) //플레이어가 블랙잭인 경우
							player.setPrize(player.getBettedMoney()*3);
						else { //플레이어 승리
							player.setStatus(BlackJackPlayerStatus.WIN);
							player.setPrize(player.getBettedMoney()*2);
						}
					}
					else { //딜러가 bust가 아닌경우
						if (isPlayerBlackJack) { //플레이어가 블랙잭인 경우
							if (isDealerBlackJack) { //딜러도 블랙잭인 경우 무승부
								player.setStatus(BlackJackPlayerStatus.DRAW);
								player.setPrize(player.getBettedMoney());
							}
							else
								player.setPrize(player.getBettedMoney()*3);
						}
						else { //플레이어가 블랙잭이 아닌경우
							if (dealer.getCurScore() > player.getCurScore()) {
								player.setStatus(BlackJackPlayerStatus.LOSE);
								player.setPrize(0);
							}
							else if (dealer.getCurScore() == player.getCurScore()) {
								player.setStatus(BlackJackPlayerStatus.DRAW);
								player.setPrize(player.getBettedMoney());
							}
							else {
								player.setStatus(BlackJackPlayerStatus.WIN);
								player.setPrize(player.getBettedMoney()*2);
							}
						}
					}
				}
			}
			player.setMoney(player.getMoney()-player.getBettedMoney()+player.getPrize()); //플레이어 가진돈 저장
		}
	}
	
	/**
	 * 게임이 끝났을 때 실행되는 메서드
	 * @return
	 */
	private EndOfGameOptions endGame() {
		playerCharacter.printScoreRecords();
		if (playerCharacter.getMoney() == 0) { //플레이어가 가진돈이 0원일 때 게임을 끝낸다.
			Main.UI.printGameOverMsg();
			return EndOfGameOptions.EXIT;
		}
		checkRuinedPlayer(); //AI플레이어 중 파산한 AI를 내보낸다.
		
		int command = -1;
		while (true) { //플레이어의 의사를 입력받는다.
			Main.UI.printEndGameOptions();
			String input = Main.UI.acceptPlayerCommand();
			try {
				command = Integer.parseInt(input);
			} catch (NumberFormatException e) {}
	
			if (command != 1 && command != 2)
				Main.UI.printAcceptDeniedMsg();
			else
				break;
		}

		if (command-1 == EndOfGameOptions.RETRY.ordinal()) {
			Main.UI.printRetryMsg();;
		}
		else
			Main.UI.screenCutaway();
		
		return EndOfGameOptions.values()[command-1];
	}
	
	/**
	 * 파산한 플레이어를 찾아 내보낸다.
	 */
	private void checkRuinedPlayer() {
		ArrayList<BlackJackPlayer> ruined = new ArrayList<>();
		for (BlackJackPlayer player : players)
			if (player.getMoney() <= 0)
				ruined.add(player);
		for (BlackJackPlayer player : ruined) {
			players.remove(player);
			Main.UI.printAIPlayerRuinedMsg(player.getName());
		}
	}
	
	/**
	 * 전체 플레이어의 손패를 출력한다.
	 */
	private void printAllPlayersHand() {
		for (BlackJackPlayer player : players) {
			player.getPlayerActionService().printCurHand(player);
		}
	}
	
	/**
	 * 새로 카드를 받는다.
	 * @param host
	 * @return 받은 카드를 반환
	 */
	private TrumpCard hit(BlackJackPlayer host) {
		try {
			TrumpCard res = deck.drawCard();
			host.getHand().add(res);
			host.refreshScore();
			return res;
		} catch (EmptyStackException e) {
			Main.UI.printEmptyDeckWarningMsg();
			return null;
		}
	}
	
	/**
	 * 게임이 끝났을 때 각각의 플레이어들에게 로그를 입력한다.
	 */
	private void addLog() {
		for (BlackJackPlayer player : players) {
			String[] opponents = new String[players.size()];
			int[] opponentScores = new int[players.size()];
			for (int i = 0; i < players.size(); i++) {
				if (!players.get(i).getId().equals(player.getId())) { //자기 자신은 제외한다.
					opponents[i] = players.get(i).getName();
					opponentScores[i] = players.get(i).getCurScore();
				}
			}
			BlackJackScoreRecord newRecord = new BlackJackScoreRecord(player.getStatus(), player.getBettedMoney(), player.getPrize(), new Date(), player.getName(), player.getCurScore(), opponents, opponentScores);
			player.addScoreRecord(newRecord);
		}
	}
}
