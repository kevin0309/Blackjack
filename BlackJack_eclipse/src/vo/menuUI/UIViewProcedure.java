package vo.menuUI;

import blackJack.vo.menuUI.BlackJackUIViewProcedure;

/**
 * 여러 UIViewProcedure interface를 상속받아 하나의 abstract class로 묶어 실제로 구현할 코드에선 이 클래스만을 extend하도록 한다.
 * @author LG
 *
 */
public abstract class UIViewProcedure implements MainMenuUIViewProcedure, BlackJackUIViewProcedure{

}
