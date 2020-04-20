package models;

public class Frame {
	private int knockedDownPinsFirstRoll;
	private int knockedDownPinsSecondRoll;
	private int knockedDownPinsThirdRoll;
	private int score;
	private int frameNumber;
	
	public Frame() {
		this.knockedDownPinsFirstRoll = 0;
		this.knockedDownPinsSecondRoll = 0;
		this.knockedDownPinsThirdRoll = -1;
		this.score = 0;
	}

	public int getKnockedDownPinsFirstRoll() {
		return knockedDownPinsFirstRoll;
	}

	public void setKnockedDownPinsFirstRoll(int knockedDownPinsFirstRoll) {
		this.knockedDownPinsFirstRoll = knockedDownPinsFirstRoll;
	}

	public int getKnockedDownPinsSecondRoll() {
		return knockedDownPinsSecondRoll;
	}

	public void setKnockedDownPinsSecondRoll(int knockedDownPinsSecondRoll) {
		this.knockedDownPinsSecondRoll = knockedDownPinsSecondRoll;
	}

	public int getKnockedDownPinsThirdRoll() {
		return knockedDownPinsThirdRoll;
	}

	public void setKnockedDownPinsThirdRoll(int knockedDownPinsThirdRoll) {
		this.knockedDownPinsThirdRoll = knockedDownPinsThirdRoll;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public int getFrameNumber() {
		return frameNumber;
	}

	public void setFrameNumber(int frameNumber) {
		this.frameNumber = frameNumber;
	}	
}
