package logic;

import java.util.ArrayList;
import java.util.Scanner;

import blackJack.vo.menuUI.menuData.EndOfGameOptions;
import blackJack.vo.menuUI.menuData.PlayerTurnCommandOptions;
import blackJack.vo.player.BlackJackPlayer;
import blackJack.vo.player.BlackJackScoreRecord;
import util.WindowsConsoleUtil;
import vo.menuUI.UIViewProcedure;
import vo.menuUI.menuData.MainMenuOptions;
import vo.menuUI.menuData.YesNoOptions;
import vo.trumpCard.TrumpCard;

/**
 * UIViewProcedure를 상속받아 UI를 나타내기위해 필요한 메서드들을
 * Windows console환경에 맞게 구현한 클래스
 * @author LG
 *
 */
public class WindowsConsoleUIViewProcedure extends UIViewProcedure {

	@Override
	public void screenCutaway() {
		WindowsConsoleUtil.clear();
	}

	@Override
	public String acceptPlayerCommand() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}

	@Override
	public void printAcceptDeniedMsg() {
		WindowsConsoleUtil.printOneByOne("Invalid input. Please re-enter.");
		System.out.println();
	}
	
	@Override
	public void printBettingPhaseTitle() {
		WindowsConsoleUtil.sleep(1000);
		WindowsConsoleUtil.printOneByOne("<==== Betting ====>");
		WindowsConsoleUtil.sleep(1000);
		System.out.println();
	}

	@Override
	public void printPlayerSurrenderMsg(String playerName) {
		WindowsConsoleUtil.printOneByOne(playerName+" was surrendered.");
		WindowsConsoleUtil.sleep(3000);
		WindowsConsoleUtil.clear();
	}

	@Override
	public void printPlayerBetMoneyMsg(String playerName, int assets) {
		WindowsConsoleUtil.printOneByOne(playerName+"'s betting turn. Bet your money.(My assets : "+assets+") : ");
	}

	@Override
	public void printAIBetMoneyMsg(String playerName, int bettedMoney) {
		WindowsConsoleUtil.printOneByOne(playerName+" bet.");WindowsConsoleUtil.sleep(500);
		System.out.print(".");WindowsConsoleUtil.sleep(500);
		WindowsConsoleUtil.generateRandomTimeDelayMsg(".", 80, 500);
		WindowsConsoleUtil.printOneByOne(" "+bettedMoney);
		System.out.println();
		WindowsConsoleUtil.sleep(1000);
	}

	@Override
	public void printBetPhaseFinishedMsg() {
		System.out.println();
		WindowsConsoleUtil.printOneByOne("The game betting has ended. Prepare for game starts.");
		WindowsConsoleUtil.sleep(3000);
		WindowsConsoleUtil.clear();
	}

	@Override
	public void printEmptyDeckWarningMsg() {
		System.out.println("Warning! Deck is empty.");
	}

	@Override
	public void printHandPreview(boolean hideFirstCard, boolean isDealer, String playerName, int curScore, int bettedMoney, int assets) {
		System.out.println("------ "+playerName+"'s Hand ("+(hideFirstCard?"? + ":"")+curScore+"/21) ------");
		if (!isDealer)
			System.out.println("Betted/Assets : "+bettedMoney+"/"+assets);
	}

	@SuppressWarnings("unused")
	@Override
	public void printHand(boolean hideFirstCard, ArrayList<TrumpCard> hand) {
		int hideIndex = 0; //처음 한장을 가리기 위해 지정
		if (hideFirstCard)
			hideIndex = 1;
		
		//단순 for문을 사용하여 핸드에 들고있는 카드를 출력하는 과정 
		for (TrumpCard card : hand)
			System.out.print("┌────┐ ");
		System.out.println();
		for (int i = 0; i < hand.size(); i++) {
			try {
				hand.get(i);
				 
				if (i < hideIndex)
					System.out.print("│▩▩│ ");
				else {
					System.out.print("│"+hand.get(i).getSuit().getSuitType().getSuitEmblem());
					String temp = hand.get(i).getNumber().getPrintChar();
					if (temp.length()==1)
						System.out.print(" "+temp+"│ ");
					else
						System.out.print(temp+"│ ");
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
			}
		}
		System.out.println();
		for (int i = 0; i < hand.size(); i++) {
			if (i < hideIndex)
				System.out.print("│▩▩│ ");
			else
				System.out.print("│    │ ");
		}
		System.out.println();
		for (TrumpCard card : hand)
			System.out.print("└────┘ ");
		System.out.println();
		System.out.println();
	}

	@Override
	public void printAcceptPlayerTurnCommandMsg(String playerName) {
		WindowsConsoleUtil.printOneByOne(playerName+"'s turn. Make your decision..");
		WindowsConsoleUtil.sleep(500);
		System.out.println();
		System.out.println(PlayerTurnCommandOptions.getFullOptionsToString());
	}

	@Override
	public void printAcceptAITurnCommandMsg(String playerName, String msg) {
		WindowsConsoleUtil.printOneByOne(playerName+"'s turn.");WindowsConsoleUtil.sleep(500);
		System.out.print(".");WindowsConsoleUtil.sleep(500);
		WindowsConsoleUtil.generateRandomTimeDelayMsg(".", 75, 500);
		WindowsConsoleUtil.printOneByOne(msg);
		WindowsConsoleUtil.sleep(3000);
	}

	@Override
	public void printAcceptDealerTurnCommandMsg(String playerName, boolean isHit) {
		WindowsConsoleUtil.printOneByOne(playerName+"'s turn.");WindowsConsoleUtil.sleep(500);
		System.out.print(".");WindowsConsoleUtil.sleep(500);
		if (isHit)
			WindowsConsoleUtil.printOneByOne(" hit!");
		else
			WindowsConsoleUtil.printOneByOne(" stay.");
		WindowsConsoleUtil.sleep(3000);
	}

	@Override
	public void printAlreadyBustedMsg() {
		WindowsConsoleUtil.printOneByOne("You already BUSTED..");
		WindowsConsoleUtil.sleep(3000);
	}

	@Override
	public void printResult(BlackJackPlayer dealer, ArrayList<BlackJackPlayer> players) {
		WindowsConsoleUtil.printOneByOne("The winner is");
		int repeatCnt = WindowsConsoleUtil.generateRandomTimeDelayMsg(".", 75, 500);
		System.out.println("");
		String temp = "The winner is";
		for (int i = 0; i < repeatCnt; i++)
			temp+=".";
		
		System.out.println("------ "+dealer.getName()+"'s Hand ("+dealer.getCurScore()+"/21) ------");
		dealer.printCurHand(false);
		WindowsConsoleUtil.sleep(1000);

		//각각의 플레이어의 결과를 딜러와 비교하며 나타내준다.
		for (BlackJackPlayer player : players) {
			if (player == dealer) //딜러일 경우는 continue
				continue;
			for (int j = 0; j < 3; j++) { //한 플레이어에 대하여 결과화면을 동적으로 표현하기 위해 3단계로 나누어 출력하였다.
				WindowsConsoleUtil.clear();
				System.out.println(temp);
				System.out.println("------ "+dealer.getName()+"'s Hand ("+dealer.getCurScore()+"/21) ------");
				dealer.printCurHand(false);
				System.out.println();
				
				if (j == 0) { //단계 1. 플레이어의 Status까지 출력한다.
					System.out.println("------ "+player.getName()+"'s Hand ("+player.getCurScore()+"/21) ------");
					System.out.println("Betted/Assets : "+player.getBettedMoney()+"/"+(player.getMoney()-player.getPrize()+player.getBettedMoney()));
					player.printCurHand(false);
					WindowsConsoleUtil.sleep(1000);
					WindowsConsoleUtil.printOneByOne("Status : ");
					WindowsConsoleUtil.sleep(1000);
					WindowsConsoleUtil.printOneByOne(player.getStatus().getStatusMsg());
					WindowsConsoleUtil.sleep(1000);
				}
				else if (j == 1) { //단계 2. 플레이어의 보상금액을 플레이어 재산의 오른편에 출력한다.
					System.out.println("------ "+player.getName()+"'s Hand ("+player.getCurScore()+"/21) ------");
					System.out.println("Betted/Assets : "+player.getBettedMoney()+"/"+(player.getMoney()-player.getPrize()+player.getBettedMoney())+" "+((player.getPrize()-player.getBettedMoney())>=0?"+":"")+(player.getPrize()-player.getBettedMoney()));
					player.printCurHand(false);
					System.out.println("Status : "+player.getStatus().getStatusMsg());
					WindowsConsoleUtil.sleep(1000);
				}
				else if (j == 2) { //단계 3. 플레이어의 보상금액을 플레이어 재산에 합산하여 출력한다.
					System.out.println("------ "+player.getName()+"'s Hand ("+player.getCurScore()+"/21) ------");
					System.out.println("Betted/Assets : "+player.getBettedMoney()+"/"+player.getMoney());
					player.printCurHand(false);
					System.out.println("Status : "+player.getStatus().getStatusMsg());
					WindowsConsoleUtil.sleep(1000);
				}
			}
		}
		WindowsConsoleUtil.clear();
		WindowsConsoleUtil.printOneByOne("<=========== RESULT ===========>");
		WindowsConsoleUtil.sleep(1000);
		System.out.println("\n------ "+dealer.getName()+"'s Hand ("+dealer.getCurScore()+"/21) ------");
		dealer.printCurHand(false);
		
		for (BlackJackPlayer player : players) { //마지막으로 전체 플레이어의 결과를 출력한다.
			if (player == dealer)
				continue;
			System.out.println("------ "+player.getName()+"'s Hand ("+player.getCurScore()+"/21) ------");
			System.out.println("Betted/Assets : "+player.getBettedMoney()+"/"+player.getMoney());
			player.printCurHand(false);
			System.out.println("Status : "+player.getStatus().getStatusMsg());
		}
		System.out.println();
	}

	@Override
	public void printGameOverMsg() {
		WindowsConsoleUtil.sleep(2000);
		System.out.println();
		WindowsConsoleUtil.printOneByOne("Your life is ruined.... ");
		WindowsConsoleUtil.sleep(400);
		System.out.println();
		WindowsConsoleUtil.printOneByOne("Try again in your next life....");
	}

	@Override
	public void printEndGameOptions() {
		System.out.println();
		WindowsConsoleUtil.printOneByOne(EndOfGameOptions.getFullOptionsToString());
		System.out.println();
	}

	@Override
	public void printRetryMsg() {
		WindowsConsoleUtil.printOneByOne("A new game will start after 5 seconds.");
		WindowsConsoleUtil.sleep(5000);
	}

	@Override
	public void printScoreBoard(String playerName, ArrayList<BlackJackScoreRecord> scoreBoard) {
		System.out.println("------ "+playerName+" Score Board ------");
		WindowsConsoleUtil.sleep(200);
		if (scoreBoard.size() == 0) {
			WindowsConsoleUtil.printOneByOne("Data not found...");
			System.out.println();
		}
		for (BlackJackScoreRecord record : scoreBoard) {
			WindowsConsoleUtil.sleep(200);
			System.out.println(record.toString());
		}
	}

	@Override
	public void printLogo() {
		WindowsConsoleUtil.printOneByOne(
		"                              X                             \r\n" + 
		"                             aM                             \r\n" + 
		"                            2MMM                            \r\n" + 
		"                           BM8SMM;                          \r\n" + 
		"                         iMMZ@M80MW                         \r\n" + 
		"                       :@M08MMMM@ZMMa                       \r\n" + 
		"           .;:  ZaB  :@M@8MMMMMMMMW8MMZ   @SZ  ii.          \r\n" + 
		"         ;X00XXMS Zr@M@0MMMMMMMMMMMMBBMMZ;2 MMXS@2Z,        \r\n" + 
		"         7M0   BMW@MW0MMMMMMMMMMMMMMMM@0MMWWMi  ,MM,        \r\n" + 
		"           XX,:MMMWMMMMMMMMMMMMMMMMMMMMMMWWMM0 :X;          \r\n" + 
		"        7X7irMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWMM0i:;2        \r\n" + 
		"        M2.,.   8r  iMMMMMM     2MM@i    ;.  @Mr  W0        \r\n" + 
		"        M.  ZM   :  iMMMMM:      MZ      X   Z;  iMB        \r\n" + 
		"       MM   ,;  ZX  rMMMM@  rM   X    WMMM      XMWMMS      \r\n" + 
		" ,MMSXMMM   aM  77  SMMMM   i0,  ;:   ;XrM.  i  ,MMWZMMiMM0 \r\n" + 
		"7M, MMMaM   .S   r   , ;S  ,7.:   Xr     7   MM   8M@2MMZ 2Z\r\n" + 
		"0W  SMXMMrii:.irMX., ;i7,.,X@MMMX:iSi:X,,;02,722;.,MMZBM   M\r\n" + 
		" a2:MM2MMMMMMMMMMi   MM:     MMM:     X   MX  .MMMMMMMSMa;B:\r\n" + 
		"  iMMM2MMMMMMMMMMi   M@  2r  .M    77aM   ,  XMMMMMMMMSMMZ  \r\n" + 
		"    WMSMMMMMB. MMi   @   MM   r   ,MMMM      MMMMMMMMZBMi   \r\n" + 
		"     MM2MMMMa   ,    i        ;.      a   @   BMMMMMBZM2    \r\n" + 
		"      MM0BMMMZ      7   M8MM   7Z.    ;   MB   ZMM@0MMS     \r\n" + 
		"       iMM@WMMMMMMMMMMMMMMMMMBMBMMMMWMMMMMMMMMMWMW@M8       \r\n" + 
		"         SZ@MMMMMMMMMMMMMX MM8M2MX MMMMMMMWWMMMMM0Z;        \r\n" + 
		"      rM,XM  .i7MMMMM87   2MSMMZBM   i2WMMMM0;:  .M ZM      \r\n" + 
		"      70  X    iMM8      ZMZ0MMMSMM.     rMMB    ,i .W      \r\n" + 
		"       ,Z08@MMMMW.     iMM8WMMMMM8@M8      XMMMMMB00X       \r\n" + 
		"                    XMMMMS0W000BB@ZaMMMW:                   "
		, 1);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.print("\t\t  ");
	}

	@Override
	public void printPressEnterToContinueMsg() {
		WindowsConsoleUtil.printOneByOne("Press ENTER to continue!!");
		acceptPlayerCommand();
	}

	@Override
	public void printMainMenu() {
		WindowsConsoleUtil.printOneByOne("<==== Main Menu ====>");
		for (MainMenuOptions option : MainMenuOptions.values()) {
			System.out.println();
			System.out.println();
			System.out.println();
			WindowsConsoleUtil.printOneByOne(option.getOptionMsg());
		}
		System.out.println();
		System.out.println();
		System.out.println();
		WindowsConsoleUtil.printOneByOne("Please Enter your command here.");
		System.out.println();
	}

	@Override
	public void printAcceptPlayerNameMsg() {
		WindowsConsoleUtil.printOneByOne("Please Enter your name here.");
		System.out.println();
	}
	
	@Override
	public void printConfirmPlayerNameMsg(String playerName) {
		System.out.println();
		WindowsConsoleUtil.printOneByOne("Are you sure your name is '"+playerName+"'?");
		System.out.println();
		WindowsConsoleUtil.printOneByOne(YesNoOptions.getFullOptionsToString());
		System.out.println();
	}

	@Override
	public void printRules() {
		WindowsConsoleUtil.clear();
		WindowsConsoleUtil.printOneByOne("--카드 점수 계산--");
		WindowsConsoleUtil.sleep(1000);
		System.out.println();
		System.out.println();
		WindowsConsoleUtil.printOneByOne("Ace는 1 또는 11로 계산합니다.\r\n" + 
				"King, Queen, Jack은 각각 10으로 계산합니다.\r\n" + 
				"그 외의 카드는 카드에 표시된 숫자로 계산합니다.");
		WindowsConsoleUtil.sleep(1000);
		System.out.println();
		System.out.println();
		WindowsConsoleUtil.printOneByOne("--게임 방법--");
		System.out.println();
		System.out.println();
		WindowsConsoleUtil.sleep(1000);
		WindowsConsoleUtil.printOneByOne("베팅을 한 후 모든 플레이어와 딜러는 두 장의 카드를 받습니다.\r\n" + 
				"딜러는 자신의 카드 중 한 장을 오픈합니다.\r\n" + 
				"딜러는 카드의 합이 17이 될 때까지 반드시 추가 카드를 뽑아야 합니다.\r\n" + 
				"플레이어는 카드의 합이 21을 넘지 않는 범위 내에서 추가 카드를 받을 수도(Hit),받지 않을 수도(Stay)있습니다.");
		WindowsConsoleUtil.sleep(1000);
		System.out.println();
		System.out.println();
		WindowsConsoleUtil.printOneByOne("- Blackjack\r\n" + 
				"처음 두 장의 카드 합이 21일 경우를 말하며 베팅 금액의 2배의 Pay를 받습니다.\r\n" + 
				"\r\n" + 
				"- Bust\r\n" + 
				"카드 합이 21을 초과하면 베팅 금액을 잃게 됩니다.\r\n" + 
				"\r\n" + 
				"- Draw\r\n" + 
				"플레이어와 딜러의 각각의 카드 합이 같을 경우 서로 비기게 됩니다.\r\n" + 
				"\r\n" + 
				"- Stay\r\n" + 
				"플레이어가 추가 카드를 원하지 않을 경우를 말하며, 딜러는 카드의 합이 17 이상이면 추가 카드를 받을 수 없습니다.\r\n" + 
				"\r\n" + 
				"- Hit\r\n" + 
				"플레이어가 처음 두 장의 카드 외에 딜러에게 추가카드를 요청하는 경우를 말합니다.\r\n" + 
				"\r\n" + 
				"- Surrender\r\n" + 
				"플레이어가 처음 두 장의 카드로 딜러의 카드를 이길 수 없다고 판단한 경우 게임을 포기하는 것을 말합니다.\r\n" + 
				"이 경우 최초 베팅액의 1/2을 잃게 됩니다.");
		WindowsConsoleUtil.sleep(1000);
		System.out.println();
		System.out.println();
		printPressEnterToContinueMsg();
		WindowsConsoleUtil.clear();
	}

	@Override
	public void printAIAddMsg() {
		WindowsConsoleUtil.printOneByOne("Please enter how many competitive AI players to add. (0 ~ 6)");
		System.out.println();
	}

	@Override
	public void printNewAIAddMsg(String name) {
		WindowsConsoleUtil.printOneByOne(name+" joined.");
		WindowsConsoleUtil.sleep(1000);
		System.out.println();
	}

	@Override
	public void printAIPlayerRuinedMsg(String name) {
		WindowsConsoleUtil.printOneByOne(name+" ruined.");
		WindowsConsoleUtil.sleep(1000);
		System.out.println();
	}

}
