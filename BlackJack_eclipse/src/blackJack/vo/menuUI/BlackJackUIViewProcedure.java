package blackJack.vo.menuUI;

import java.util.ArrayList;

import blackJack.vo.player.BlackJackPlayer;
import blackJack.vo.player.BlackJackScoreRecord;
import vo.trumpCard.TrumpCard;

/**
 * 블랙잭 게임 내에서 사용되는 UI procedure들을 구현한 인터페이스
 * @author LG
 *
 */
public interface BlackJackUIViewProcedure {
	/**
	 * 몇 명의 AI를 추가할지 정하는 메세지를 출력한다.
	 */
	public void printAIAddMsg();
	/**
	 * AI가 추가되었다는 메세지를 출력한다.
	 * @param name
	 */
	public void printNewAIAddMsg(String name);
	/**
	 * betting phase의 제목을 출력한다.
	 */
	public void printBettingPhaseTitle();
	/**
	 * AI 플레이어의 베팅 상황을 출력한다.
	 * @param playerName
	 * @param bettedMoney
	 */
	public void printAIBetMoneyMsg(String playerName, int bettedMoney);
	/**
	 * 플레이어의 베팅 메세지를 출력한다.
	 * @param playerName
	 * @param assets
	 */
	public void printPlayerBetMoneyMsg(String playerName, int assets);
	/**
	 * 베팅이 끝났음을 알리는 메세지를 출력한다.
	 */
	public void printBetPhaseFinishedMsg();
	/**
	 * 덱이 비어있어 더 이상 뽑을 수 없을 때 메세지를 출력한다.
	 */
	public void printEmptyDeckWarningMsg();
	/**
	 * 핸드에 대한 정보와 현재 상황을 간략하게 출력한다. 
	 * @param hideFirstCard
	 * @param isDealer
	 * @param playerName
	 * @param curScore
	 * @param bettedMoney
	 * @param assets
	 */
	public void printHandPreview(boolean hideFirstCard, boolean isDealer, String playerName, int curScore, int bettedMoney, int assets);
	/**
	 * 핸드를 출력한다.
	 * @param hideFirstCard
	 * @param hand
	 */
	public void printHand(boolean hideFirstCard, ArrayList<TrumpCard> hand);
	/**
	 * AI 플레이어의 턴 결정을 출력한다.
	 * @param playerName
	 * @param msg
	 */
	public void printAcceptAITurnCommandMsg(String playerName, String msg);
	/**
	 * 플레이어의 턴임을 알리는 메세지를 출력한다.
	 * @param playerName
	 */
	public void printAcceptPlayerTurnCommandMsg(String playerName);
	/**
	 * 딜러의 턴 결정을 출력한다.
	 * @param playerName
	 * @param isHit
	 */
	public void printAcceptDealerTurnCommandMsg(String playerName, boolean isHit);
	/**
	 * 이미 bust되었다는 메세지를 출력한다.
	 */
	public void printAlreadyBustedMsg();
	/**
	 * 이미 surrender를 했다는 메세지를 출력한다.
	 * @param playerName
	 */
	public void printPlayerSurrenderMsg(String playerName);
	/**
	 * 결과를 출력한다.
	 * @param dealer
	 * @param players
	 */
	public void printResult(BlackJackPlayer dealer, ArrayList<BlackJackPlayer> players);
	/**
	 * 플레이어가 파산했을 때 게임오버 메세지를 출력한다.
	 */
	public void printGameOverMsg();
	/**
	 * 게임이 종료되었을때 플레이어의 옵션을 출력한다.
	 */
	public void printEndGameOptions();
	/**
	 * 재시작 메세지를 출력한다.
	 */
	public void printRetryMsg();
	/**
	 * 플레이어의 점수내역을 출력한다.
	 * @param playerName
	 * @param scoreBoard
	 */
	public void printScoreBoard(String playerName, ArrayList<BlackJackScoreRecord> scoreBoard);
	/**
	 * AI 플레이어가 파산했을때의 메세지를 출력한다.
	 * @param name
	 */
	public void printAIPlayerRuinedMsg(String name);
}
