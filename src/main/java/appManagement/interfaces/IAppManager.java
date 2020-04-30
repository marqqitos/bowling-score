package appManagement.interfaces;

import gameManagement.interfaces.IBowlerManager;
import gameManagement.interfaces.IFormatManager;

public interface IAppManager {
	public IFormatManager getFormatManager();
	public IBowlerManager getBowlerManager();
}
