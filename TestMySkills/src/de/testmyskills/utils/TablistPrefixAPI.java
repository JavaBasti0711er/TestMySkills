package de.testmyskills.utils;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.google.common.base.Splitter;

import de.testmyskills.Main;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class TablistPrefixAPI implements Listener {

	Main pl;

	public TablistPrefixAPI(Main instance) {
		pl = instance;
	}

	public static void startScheduler() {
		new BukkitRunnable() {

			@Override
			public void run() {
				for (Player all : Bukkit.getOnlinePlayers()) {
					setScoreboard(all);
				}

			}
		}.runTaskTimer(Main.instance, 0, 20 * 20);
	}

	@SuppressWarnings("deprecation")
	public static void setScoreboard(Player p) {
		Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
		SetupTablist t = Main.instance.setupTablist;

		for (String str : t.getConfigurationSection("TablistPrefix").getKeys(false)) {
			String rankprefix = t.getString("TablistPrefix." + str + ".Prefix");
			String groupname = t.getString("TablistPrefix." + str + ".PexGroup");
			String teamrank = t.getString("TablistPrefix." + str + ".Team");

			Team rankteam = getTeam(sb, teamrank, Main.instance.colorize(rankprefix));

			for (Player all : Bukkit.getOnlinePlayers()) {
				if (PermissionsEx.getUser(all).inGroup(groupname)) {
					rankteam.addPlayer(all);
					
				} else {
					rankteam.addPlayer(all);
				}

				p.setScoreboard(sb);
			}
		}

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		SetupTablist t = pl.setupTablist;

		Scoreboard sb = p.getScoreboard();
		for (String str : t.getConfigurationSection("TablistPrefix").getKeys(false)) {
			String groupname = t.getString("TablistPrefix." + str + ".PexGroup");
			String teamrank = t.getString("TablistPrefix." + str + ".Team");
			String rankprefix = Main.instance.colorize(t.getString("TablistPrefix." + str + ".Prefix"));

			Team rankteam = getTeam(sb, teamrank, rankprefix);

			for (Player all : Bukkit.getOnlinePlayers()) {
				if (PermissionsEx.getUser(all).inGroup(groupname)) {
					rankteam.addPlayer(all);
				} else {
					rankteam.addPlayer(all);
				}
				setScoreboard(all);
				setScoreboard(p);
			}
		}
	}

	public static Team getTeam(Scoreboard sb, String Team, String prefix) {
		Team team = sb.getTeam(Team);
		if (team == null) {
			team = sb.registerNewTeam(Team);
		}
		Iterator<String> iterator = Splitter.fixedLength(16).split(prefix).iterator();
		team.setPrefix(iterator.next());
		if (prefix.length() > 32)
			team.setSuffix(iterator.next());
		team.setAllowFriendlyFire(true);
		team.setCanSeeFriendlyInvisibles(true);
		return team;
	}
}
