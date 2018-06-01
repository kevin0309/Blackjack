package blackJack.vo.player;

/**
 * 블랙잭 플레이 중 플레이어가 가질 수 있는 상태를 저장한다.<br>
 * NORMAL : 일반<br>
 * STAY : stay<br>
 * BUST : bust<br>
 * BUST_STAY : 기본적으로 bust상태이나 bust 상태에서 한번 더 턴을 진행 할 수 있게 bust_stay를 따로 구현<br>
 * WIN : 승리<br>
 * BLACK_JACK : 처음받은 두 장의 합이 21일 경우<br>
 * LOSE : 패배<br>
 * SURRENDER : 항복<br>
 * DRAW : 무승부
 * 
 * @author LG
 *
 */
public enum BlackJackPlayerStatus {

	NORMAL("Normal", 0),
	STAY("Stay", 1),
	BUST("Busted", -1),
	BUST_STAY("Busted/Stay", -2),
	WIN("Win", 10),
	BLACK_JACK("<({[--==♠BLACK☆JACK♠==--]})>", 777),
	LOSE("Lose", 11),
	SURRENDER("Surrender", 12),
	DRAW("Draw", 13);
	
	private String statusMsg;
	private int statusCode;
	
	private BlackJackPlayerStatus(String statusMsg, int statusCode) {
		this.statusMsg = statusMsg;
		this.statusCode = statusCode;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public int getStatusCode() {
		return statusCode;
	}
	
}
