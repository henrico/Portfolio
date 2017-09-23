package za.co.henrico.portfolio;

import za.co.henrico.portfolio.exactcoverproblem.PartialSolutionObject;

public class DummySolutionObject implements PartialSolutionObject {

	private Boolean[] v;
	private String name;

	public DummySolutionObject(String string, Boolean[] v) {
		this.name = string;
		this.v = v;
	}

	public DummySolutionObject clone() {
		return new DummySolutionObject(name, v);
	}

	public Boolean[] getRow() {
		return v;
	}

	public String toString() {
		return name;
	}

}
