package com.fleetmanagement.shipping.constant;

public enum PackageStatus {

	CREATED(1), LOADED_INTO_BAG(2), LOADED(3), UNLOADED(4);

	private int state;

	PackageStatus(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}

}
