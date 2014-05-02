package core.helpers;

import java.awt.event.KeyEvent;

import core.ApplicationManager;
import core.config.Config;

public class ThreadHelper extends Thread
{
	private final ApplicationManager manager;
	int timeout;
	public boolean timeoutOccurred;
	public boolean completed;

	public ThreadHelper(ApplicationManager p_manager)
	{
		manager = p_manager;
		timeout = Config.WEBDRIVER_WAIT * 2;
		completed = false;
		timeoutOccurred = false;
	}

	@Override
	public void run()
	{
		try
		{
			Thread.sleep(timeout * 1000);
			this.timeoutOccurred = true;
			this.handleTimeout();
			this.completed = true;

		}
		catch (InterruptedException e)
		{
			return;
		}
		catch (Exception e)
		{
			manager.log().debug("Exception on TimeoutThread.run(): " + e.getMessage());
		}
	}

	public void handleTimeout()
	{
		manager.log().debug("Type proxy login => " + manager.config().PROXY_USERNAME);
		manager.robot().writeToClipboard(manager.config().PROXY_USERNAME);
		manager.robot().paste();

		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		manager.robot().pressAndRelease(KeyEvent.VK_TAB);

		manager.log().debug("Type proxy password +> " + manager.config().PROXY_PASSWORD);
		manager.robot().writeToClipboard(manager.config().PROXY_PASSWORD);
		manager.robot().paste();

		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		manager.log().debug("Send proxy authentification data");
		manager.robot().pressAndRelease(KeyEvent.VK_ENTER);
	}

}
