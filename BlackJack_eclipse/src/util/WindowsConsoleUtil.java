package util;

/**
 * Windows Console 환경에서 사용할 유틸 메서드들을 구현한 메서드
 * @author LG
 *
 */
public class WindowsConsoleUtil {

	/**
	 * 콘솔창 클리어
	 */
	public static void clear(){
		try {
        	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * repeatMsg를 percentage확률로 delay의 시간간격을 가지며 랜덤하게 반복 출력한다.
	 * @param repeatMsg
	 * @param percentage
	 * @param delay
	 * @return
	 */
	public static int generateRandomTimeDelayMsg(String repeatMsg, int percentage, int delay) {
		int cnt = 0;
		while (true) {
			int r = (int)(Math.random()*100);
			if (r < percentage) {
				System.out.print(repeatMsg);
				cnt++;
				sleep(delay);
			}
			else
				break;
		}
		return cnt;
	}
	
	/**
	 * 문자열을 25ms 시간차로 한 글자씩 출력한다.
	 * @param str
	 */
	public static void printOneByOne(String str) {
		for (int i = 0; i < str.length(); i++) {
			System.out.print(str.charAt(i));sleep(25);
		}
	}
	
	/**
	 * 문자열을 delay의 시간차로 한 글자씩 출력한다.
	 * @param str
	 * @param delay
	 */
	public static void printOneByOne(String str, int delay) {
		for (int i = 0; i < str.length(); i++) {
			System.out.print(str.charAt(i));sleep(delay);
		}
	}
	
	/**
	 * interval만큼 시간을 지연시킨다.
	 * @param interval
	 */
	public static void sleep(int interval) {
		try {
			Thread.sleep((long)interval);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
