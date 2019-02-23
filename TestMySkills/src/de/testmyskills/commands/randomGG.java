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
	boolean gg = false;

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
		gg = true;
		Bukkit.broadcastMessage(randomGG.replaceAll("%prefix%", prefix));

		count = Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
			public void run() {

				gg = false;

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

		if (gg == true) {
			for (Player all : Bukkit.getOnlinePlayers()) {

				pl.clickable(all,
						chatformat.replaceAll("%player%", p.getDisplayName()).replaceAll("%rank%", rankprefix)
								.replaceAll("%message%", pl.colorize(String.valueOf(randomColor()))),
						"SUGGEST_COMMAND", commandonclick.replaceAll("%player%", p.getName()),
						hovername.replaceAll("%player%", p.getName()).replaceAll("%rank%", rankprefix2));
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
		e.setCancelled(true);

	}

	private String randomColor() {
		Random r = new Random();
		Integer x = r.nextInt(8);
		if (x == 0) {
			return "&a&lG&2&lG";
		}
		if (x == 1) {
			return "&b&lG&9&lG";
		}
		if (x == 2) {
			return "&c&lG&4&lG";
		}
		if (x == 3) {
			return "&d&lG&5&lG";
		}
		if (x == 4) {
			return "&e&lGG";
		}
		if (x == 5) {
			return "&3&l&oGG";
		}
		if (x == 6) {
			return "&6&lG&e&lG";
		}
		if (x == 7) {
			return "&9&lGG";
		}
		return null;
	}

}
