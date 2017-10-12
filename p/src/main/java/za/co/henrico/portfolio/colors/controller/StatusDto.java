package za.co.henrico.portfolio.colors.controller;

public class StatusDto {

	protected boolean training;
	protected int interation;
	protected String errorFactor;

	public boolean isTraining() {
		return training;
	}

	public void setTraining(boolean training) {
		this.training = training;
	}

	public int getInteration() {
		return interation;
	}

	public void setInteration(int interation) {
		this.interation = interation;
	}

	public String getErrorFactor() {
		return errorFactor;
	}

	public void setErrorFactor(String errorFactor) {
		this.errorFactor = errorFactor;
	}
}
