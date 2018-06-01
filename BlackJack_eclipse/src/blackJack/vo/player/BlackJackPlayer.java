package blackJack.vo.player;

import java.util.ArrayList;

import blackJack.logic.playerAction.PlayerActionService;
import main.Main;
import vo.player.CardGamePlayer;
import vo.trumpCard.NotAceCardException;
import vo.trumpCard.TrumpCard;

/**
 * 블랙잭에 참여하는 플레이어 클래스
 * @author LG
 *
 */
public class BlackJackPlayer extends CardGamePlayer {

	private ArrayList<BlackJackScoreRecord> scoreBoard;
	private PlayerActionService action;
	private BlackJackPlayerStatus status;
	private int curScore;
	private int bettedMoney;
	private int prize;
	
	public BlackJackPlayer(String name, int money, PlayerActionService service) {
		super(name, money);
		scoreBoard = new ArrayList<>();
		this.action = service;
		this.status = BlackJackPlayerStatus.NORMAL;
	}
	
	public PlayerActionService getPlayerActionService() {
		return action;
	}
	
	/**
	 * 현재 핸드에 들고있는 점수를 새로고침한다.
	 * 이 때 Ace카드를 1로 쓸 지 11로 쓸 지 계산해준다.
	 */
	public void refreshScore() {
		curScore = 0;
		for (TrumpCard card : hand) {
			curScore += card.getNumber().getBlackJackScore();
		}
		for (TrumpCard card : hand) {
			try {
				if ((card.getNumber().ordinal() == 13 && curScore > 21) 
						|| (card.getNumber().ordinal() == 0 && curScore < 12)) {
					curScore -= card.getNumber().getBlackJackScore();
					card.processAceRule();
					curScore += card.getNumber().getBlackJackScore();
				}
			} catch (NotAceCardException e) {}
		}
	}
	
	public int getCurScore() {
		return curScore;
	}
	
	public BlackJackPlayerStatus getStatus() {
		return status;
	}

	public void setStatus(BlackJackPlayerStatus status) {
		this.status = status;
	}
	
	public void addScoreRecord(BlackJackScoreRecord record) {
		scoreBoard.add(record);
	}
	
	public int getBettedMoney() {
		return bettedMoney;
	}

	public void setBettedMoney(int bettedMoney) {
		this.bettedMoney = bettedMoney;
	}

	public int getPrize() {
		return prize;
	}

	public void setPrize(int prize) {
		this.prize = prize;
	}
	
	public void printCurHand(boolean hideFirstCard) {
		Main.UI.printHand(hideFirstCard, hand);
	}

	public void printScoreRecords() {
		Main.UI.printScoreBoard(name, scoreBoard);
	}
}
