package appManagement.implementations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import appManagement.interfaces.IAppManager;
import config.Config;
import gameManagement.implementations.BowlerManager;
import gameManagement.implementations.FormatManager;
import gameManagement.interfaces.IBowlerManager;
import gameManagement.interfaces.IFormatManager;

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
