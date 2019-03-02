package de.testmyskills.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.testmyskills.Main;
import de.testmyskills.events.TablistPrefixAPI;
import de.testmyskills.utils.ScoreboardAPI;
import de.testmyskills.utils.SetupMessages;
import de.testmyskills.utils.SetupScoreboard;
import de.testmyskills.utils.SetupTablist;

public class ReloadCFG implements CommandExecutor {

	Main pl;

	public ReloadCFG(Main instance) {
		pl = instance;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		SetupMessages m = pl.setupMessages;
		SetupScoreboard sm = pl.setupScoreboard;
		SetupTablist t = pl.setupTablist;
		
		String prefix = m.getString("Prefix");
		String noperms = m.getString("NoPermissions");
		String adminperms = m.getString("AdminPermissions");

		String reloaded = m.getString("ConfigReloaded");
		String consoleplayer = m.getString("YouHaveToBeAPlayer");

		if (!(cs instanceof Player)) {
			cs.sendMessage(consoleplayer);
			return true;
		}
		Player p = (Player) cs;
		if (p.hasPermission(adminperms)) {
			m.load();
			sm.load();
			t.load();
			for(Player all : Bukkit.getOnlinePlayers()) {
			ScoreboardAPI.sendScoreboard(all);
			TablistPrefixAPI.setScoreboard(all);
			}
			ScoreboardAPI.sendScoreboard(p);
			TablistPrefixAPI.setScoreboard(p);
			p.sendMessage(reloaded.replaceAll("%prefix%", prefix));
			p.sendMessage(pl.colorize("&cPlugin coded by JavaBasti"));
			
		} else {
			p.sendMessage(noperms.replaceAll("%prefix%", prefix));
		}
		return true;

	}

}
