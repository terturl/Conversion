package me.terturl.Conversion.GameLogic;

public enum GameState {

	WAITING(0), WARMUP(1), IN_PROGRESS(2), ENDED(3);

	private final int stateId;

	private GameState(int stateId) {
		this.stateId = stateId;
	}

	public int getStateId() {
		return this.stateId;
	}

}
