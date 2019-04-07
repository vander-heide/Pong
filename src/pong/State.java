package pong;


public class State {
	public enum STATE {
		Menu,
		Game,
		Pause,
		Select,
		Win
	};
	public STATE gameState = STATE.Menu;
}
