package me.terturl.Conversion.GameLogic;

public class StateManager {

	private static GameState STATE = GameState.WAITING;

	public static GameState getState() {
		return STATE;
	}

	public static void setState(GameState gs) {
		
		STATE = gs;

		switch (gs) {
		
		case WAITING:
			break;
		case WARMUP:
			break;
		case IN_PROGRESS:
			break;
		case ENDED:
			break;
		}
	}

}
