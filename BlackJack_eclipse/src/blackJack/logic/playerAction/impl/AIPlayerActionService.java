package blackJack.logic.playerAction.impl;

import java.util.ArrayList;
import java.util.Stack;

import blackJack.logic.playerAction.PlayerActionService;
import blackJack.vo.menuUI.menuData.PlayerTurnCommandOptions;
import blackJack.vo.player.BlackJackPlayer;
import main.Main;
import vo.trumpCard.TrumpCard;
import vo.trumpCard.deck.Deck;
import vo.trumpCard.deck.DeckFactory;
import vo.trumpCard.deck.DeckType;

/**
 * AI 플레이어의 의사결정 클래스
 * @author LG
 *
 */
public class AIPlayerActionService implements PlayerActionService {

	@Override
	public PlayerTurnCommandOptions acceptTurnCommand(String playerName, int curScore, int dealerScore, int bettedMoney, DeckType deckType, Stack<TrumpCard> visibleCards) {
		Deck sample = DeckFactory.getInstance(deckType); //현재 보이는 카드로부터 보이지 않는, 덱에 남아있는 카드를 계산
		int nonVisibleCardsScoreSum = 0;
		ArrayList<TrumpCard> temp = new ArrayList<>();
		for (TrumpCard card : sample.getDeck())
			for (TrumpCard vCard : visibleCards)
				if (card.contrastCard(vCard)) { //for문이 동작중에 배열크기가 달라지면 오작동 할 수 있으므로 temp에 임시 저장
					temp.add(card);
					break;
				}
		for (TrumpCard card : temp)
			sample.getDeck().remove(card);
		Stack<TrumpCard> nonVisibleCards = sample.getDeck();
		for (TrumpCard card : nonVisibleCards) //보이지 않는 카드의 집합의 평균 점수를 계산하여 딜러가 받은 뒤집어져있는 카드점수의 기댓값을 계산한다.
			nonVisibleCardsScoreSum += card.getNumber().getBlackJackScore();
		float nonVisibleCardsScoreAvg = (float) nonVisibleCardsScoreSum / nonVisibleCards.size();
		int originalDealerScore = dealerScore;
		dealerScore += Math.round(nonVisibleCardsScoreAvg) + ((int)(Math.random()*3)) - 1; //딜러의 현재 점수 기댓값을 유추한다. (1의 오차 범위를 랜덤으로 추가한다.)
		
		int decision;
		final int HIT = 0;
		final int STAY = 1;
		final int SURRENDER = 2;

		//모든 경우의 수에 대한 인공지능 플레이어의 의사결정 부분
		//11보다 적을경우 무조건 hit
		if (curScore <= 11)
			decision = HIT;
		//현재점수가 21 혹은 busted된 경우 or 딜러가 확실히 busted된 경우 stay
		else if (curScore >= 21 || originalDealerScore > 19 || dealerScore > 21)
			decision = STAY;
		//딜러의 기댓값이 21인 경우 surrender
		else if (dealerScore == 21)
			decision = SURRENDER;
		//딜러가 16이하인 경우
		else if (dealerScore <= 16) {
			//둘 다 16이하인 경우 bust될 확률만 계산한다.
			if (curScore <= 16) {
				int possibilityBusted = 0;
				int possibilityNotBusted = 0;
				for (TrumpCard card : nonVisibleCards) {
					if (curScore + card.getNumber().getBlackJackScore() > 21)
						possibilityBusted++;
					else
						possibilityNotBusted++;
				}
				if (possibilityBusted > possibilityNotBusted)
					decision = STAY;
				else
					decision = HIT;
			}
			//플레이어가 17~20인 경우 딜러의 의사를 먼저 확인하기 위해 일단 stay
			else 
				decision = STAY;
		}
		//딜러가 17~20일 경우 다음 의사결정에 대한 각각의 가치 기댓값을 계산하여 가장 기댓값이 높은 의사결정을 선택한다. (hit or stay or surrender)
		else {
			int possibilityCntWin = 0;
			@SuppressWarnings("unused")
			int possibilityCntDraw = 0;
			int possibilityCntLose = 0;
			int totalPossibilityCnt = 0;
			
			for (TrumpCard card : nonVisibleCards) { //각각의 경우의 수에 대한 결과를 계산한다.
				if (curScore + card.getNumber().getBlackJackScore() <= 21) {
					if (curScore + card.getNumber().getBlackJackScore() > dealerScore)
						possibilityCntWin++;
					else if (curScore + card.getNumber().getBlackJackScore() == dealerScore)
						possibilityCntDraw++;
					else
						possibilityCntLose++;
				}
				else 
					possibilityCntLose++;
				totalPossibilityCnt++;
			}
			//승패 확률을 계산
			float winPossibility = (float)possibilityCntWin / totalPossibilityCnt;
			float losePossibility = (float)possibilityCntLose / totalPossibilityCnt;
			int hitExpectedValue = Math.round(bettedMoney * (winPossibility-losePossibility)); //hit에 대한 결과 기댓값
			int stayExpectedValue;
			if (curScore > dealerScore) //stay에 대한 결과 기댓값
				stayExpectedValue = bettedMoney;
			else if (curScore == dealerScore)
				stayExpectedValue = 0;
			else
				stayExpectedValue = -bettedMoney;
			int surrenderExpectedValue = -(bettedMoney / 2); //surrender에 대한 결과 기댓값
			
			if (hitExpectedValue >= stayExpectedValue && hitExpectedValue >= surrenderExpectedValue)
				decision = HIT;
			else if (stayExpectedValue > hitExpectedValue && stayExpectedValue > surrenderExpectedValue)
				decision = STAY;
			else
				decision = SURRENDER;
		}
		String msg;
		if (decision == HIT)
			msg = "hit!!";
		else if (decision == STAY)
			msg = "stay.";
		else
			msg = "surrender...";
		Main.UI.printAcceptAITurnCommandMsg(playerName, msg); //의사결정을 화면에 출력
		return PlayerTurnCommandOptions.values()[decision];
	}

	@Override
	public void printCurHand(BlackJackPlayer player) {
		try {
			Main.UI.printHandPreview(true, false, player.getName(), player.getCurScore()-player.getHand().get(0).getNumber().getBlackJackScore(), player.getBettedMoney(), player.getMoney());
		} catch (IndexOutOfBoundsException e) {
			Main.UI.printHandPreview(true, false, player.getName(), 0, player.getBettedMoney(), player.getMoney());
		}
		player.printCurHand(true);
	}

	@Override
	public int betMoney(BlackJackPlayer player) {
		int res;
		if (player.getMoney() < 100) //자금이 100원보다 적을경우 전액 베팅
			res = player.getMoney();
		else { //가진 금액의 10~50% 사이의 금액을 건다.
			int r = (int)(Math.random()*4);
			res = player.getMoney()*(1+r)/10;
			res = Math.round((float)res/100)*100;
			if (res < 100) //결과값이 100보다 작을경우 전액을 건다.
				res = player.getMoney();
		}
		player.setBettedMoney(res);
		Main.UI.printAIBetMoneyMsg(player.getName(), res);
		return res;
	}

}
