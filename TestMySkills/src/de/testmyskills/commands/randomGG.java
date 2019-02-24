package de.testmyskills.commands;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.testmyskills.Main;
import de.testmyskills.utils.SetupMessages;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class randomGG implements CommandExecutor, Listener {
	
	Main pl;

	public randomGG(Main instance) {
		pl = instance;
	}

	int startgg = 11;
	int count;
	
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		SetupMessages m = pl.setupMessages;

		String prefix = m.getString("Prefix");
		String consoleplayer = m.getString("YouHaveToBeAPlayer");
		String randomGG = m.getString("RandomGGBroadcast");
		String randomGGended = m.getString("RandomGGBroadcastEnded");
		if (!(cs instanceof Player)) {
			cs.sendMessage(consoleplayer);
			return true;
		}
		pl.gg = true;
		Bukkit.broadcastMessage(randomGG.replaceAll("%prefix%", prefix));

		count = Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
			public void run() {

				pl.gg = false;

				Bukkit.broadcastMessage(randomGGended.replaceAll("%prefix%", prefix));
			}
		}, 200L);

		return true;
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		SetupMessages m = pl.setupMessages;
		String chatformat = m.getString("Chatformat");
		String hovername = m.getString("HoverOverName");
		String commandonclick = m.getString("HoverOverNameCommandOnClick");
		PermissionUser pu = PermissionsEx.getUser(e.getPlayer());
		String rankprefix = pu.getPrefix();
		String rankprefix2 = pu.getPrefix();
		if (rankprefix2.contains(":")) {
			rankprefix2 = pu.getPrefix().replace(":", "");
		}
		Player p = e.getPlayer();

		if (pl.gg == true) {
			for (Player all : Bukkit.getOnlinePlayers()) {

				pl.clickable(all,
						chatformat.replaceAll("%player%", p.getDisplayName()).replaceAll("%rank%", rankprefix)
								.replaceAll("%message%", pl.colorize(String.valueOf(randomGGs()))),
						"SUGGEST_COMMAND", commandonclick.replaceAll("%player%", p.getName()),
						hovername.replaceAll("%player%", p.getName()).replaceAll("%rank%", rankprefix2));
				e.setCancelled(true);
			}

		} else {
			for (Player all : Bukkit.getOnlinePlayers()) {
				String msg = e.getMessage();
				pl.clickable(all,
						chatformat.replaceAll("%player%", p.getDisplayName()).replaceAll("%rank%", rankprefix)
								.replaceAll("%message%", msg),
						"SUGGEST_COMMAND", commandonclick.replaceAll("%player%", p.getName()),
						hovername.replaceAll("%player%", p.getName()).replaceAll("%rank%", rankprefix2));
				Bukkit.getConsoleSender().sendMessage(pl.colorize(chatformat.replaceAll("%rank%", rankprefix)
						.replaceAll("%player%", p.getDisplayName()).replaceAll("%message%", msg)));
				e.setCancelled(true);
			}

		}

	}

	@SuppressWarnings("unused")
	private String randomGGs() {
		SetupMessages m = pl.setupMessages;
		Random r = new Random();
		Integer i = 0;
		for (String str : m.getConfigurationSection("RandomGG.randomggs").getKeys(false)) {
			i++;
		}
		Integer x = r.nextInt(i) + 1;
		for (String stri : m.getConfigurationSection("RandomGG.randomggs").getKeys(false)) {
			if (String.valueOf(x).equals(stri)) {
				return m.getString("RandomGG.randomggs." + stri);
			}
		}
		return null;

	}

}
