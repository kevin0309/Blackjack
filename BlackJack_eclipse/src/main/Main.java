package main;

import logic.MenuProcedure;
import logic.WindowsConsoleUIViewProcedure;
import vo.menuUI.UIViewProcedure;

/**
 * 실행을 위한 main메서드를 포함하는 클래스
 * @author LG
 *
 */
public class Main {
	//UI를 Windows console에서 출력하기 위해 지정
	public static final UIViewProcedure UI = new WindowsConsoleUIViewProcedure();

	public static void main(String[] args) {
		MenuProcedure proc = new MenuProcedure();
		boolean gameEnd = !proc.process(true);
		while (!gameEnd) //사용자가 게임을 끝내기 전까지 반복한다.
			gameEnd = !proc.process(false);
	}
}
