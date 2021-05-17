package appManagement.implementations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import appManagement.interfaces.IAppManager;
import config.Config;
import gameManagement.implementations.BowlerManager;
import gameManagement.implementations.FormatManager;
import gameManagement.interfaces.IBowlerManager;
import gameManagement.interfaces.IFormatManager;

/**
 * This class sets the context for the application and gets the implementation of both Format and Bowler manager used to calculate the scoreboard of the game
 * @author: Marcos Caccavaio
 * @version: 1.0
 */
public class AppManager implements IAppManager {
	private ApplicationContext context;
	
	public AppManager() {
		this.context = new AnnotationConfigApplicationContext(Config.class);
	}
	
	@Override
	public IFormatManager getFormatManager() {
		return context.getBean(FormatManager.class);
	}
	
	@Override
	public IBowlerManager getBowlerManager() {
		return context.getBean(BowlerManager.class);
	}

}
