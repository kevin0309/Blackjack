package blackJack.vo.player;

import java.util.Date;

import util.DateUtil;

/**
 * 진행했었던 게임의 정보를 저장하기 위한 클래스
 * @author LG
 *
 */
public class BlackJackScoreRecord {

	private BlackJackPlayerStatus status;
	private int bettedMoney;
	private int prize;
	private Date playDate;
	private String playerName;
	private int playerScore;
	private String[] opponents;
	private int[] opponentScores;
	
	public BlackJackScoreRecord(BlackJackPlayerStatus status, int bettedMoney, int prize, Date playDate, String playerName,
			int playerScore, String[] opponents, int[] opponentScores) {
		this.status = status;
		this.bettedMoney = bettedMoney;
		this.prize = prize;
		this.playDate = playDate;
		this.playerName = playerName;
		this.playerScore = playerScore;
		this.opponents = opponents;
		this.opponentScores = opponentScores;
	}

	public BlackJackPlayerStatus getStatus() {
		return status;
	}

	public int getBettedMoney() {
		return bettedMoney;
	}

	public int getPrize() {
		return prize;
	}

	public Date getPlayDate() {
		return playDate;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public int getPlayerScore() {
		return playerScore;
	}

	public String[] getOpponents() {
		return opponents;
	}

	public int[] getOpponentScores() {
		return opponentScores;
	}
	
	@Override
	public String toString() {
		return status.getStatusMsg()+" /  bet : "+bettedMoney+" / prize : "+prize+" / score : ["+playerScore+"/21] / "+DateUtil.toString(playDate);
	}
}
