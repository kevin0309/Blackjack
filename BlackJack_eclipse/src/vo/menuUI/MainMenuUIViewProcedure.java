package vo.menuUI;

/**
 * 메뉴에서 사용되는 UI procedure들을 구현한 인터페이스
 * @author LG
 *
 */
public interface MainMenuUIViewProcedure {
	/**
	 * 화면을 새로운 페이지로 전환한다.
	 */
	public void screenCutaway();
	/**
	 * 플레이어의 요청을 받는다.
	 * @return
	 */
	public String acceptPlayerCommand();
	/**
	 * 플레이어의 요청이 잘못된 입력값일 경우 출력하는 메세지를 출력한다.
	 */
	public void printAcceptDeniedMsg();
	
	/**
	 * splash 메인 로고를 출력한다.
	 */
	public void printLogo();
	/**
	 * 진행하려면 엔터를 누르시오 메세지를 출력한다.
	 */
	public void printPressEnterToContinueMsg();
	/**
	 * 메인메뉴를 출력한다.
	 */
	public void printMainMenu();
	/**
	 * 사용자 이름 입력 메세지를 출력한다.
	 */
	public void printAcceptPlayerNameMsg();
	/**
	 * 사용자가 입력한 이름이 맞는지 확인하는 메세지를 출력한다.
	 * @param playerName
	 */
	public void printConfirmPlayerNameMsg(String playerName);
	/**
	 * 게임 룰 설명을 출력한다.
	 */
	public void printRules();
}
