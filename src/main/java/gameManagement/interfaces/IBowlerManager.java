package gameManagement.interfaces;

import java.util.List;

import models.Bowler;

public interface IBowlerManager {
	public List<Bowler> getBowlers(List<String> fileLines);
}
