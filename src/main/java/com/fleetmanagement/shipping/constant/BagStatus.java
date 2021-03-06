package com.fleetmanagement.shipping.constant;

public enum BagStatus {

	CREATED(1), LOADED(3), UNLOADED(4);

	private int state;

	BagStatus(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}

}
