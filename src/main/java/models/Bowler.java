package models;

import java.util.List;

public class Bowler {
	private String name;
	private List<Frame> frames;
	
	public Bowler() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Frame> getFrames() {
		return frames;
	}

	public void setFrames(List<Frame> frames) {
		this.frames = frames;
	}
}
