package de.testmyskills.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import de.testmyskills.Main;
import de.testmyskills.utils.SetupMessages;

public class MotdEvent implements Listener {

	Main pl;

	public MotdEvent(Main instance) {
		pl = instance;
	}

	@EventHandler
	public void onServerList(ServerListPingEvent e) {

		SetupMessages m = pl.setupMessages;
		String motdline1 = m.getString("MotdLine1");
		String motdline2 = m.getString("MotdLine2");
		int maxplayers = m.getInt("MaxPlayers");
		e.setMotd(motdline1 + "\n" + pl.colorize("&7") + motdline2);
		e.setMaxPlayers(maxplayers);
		
	}
}
