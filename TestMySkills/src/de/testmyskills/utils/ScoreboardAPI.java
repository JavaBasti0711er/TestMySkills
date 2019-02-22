package de.testmyskills.utils;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.testmyskills.Main;
import net.minecraft.server.v1_8_R3.IScoreboardCriteria;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;
import net.minecraft.server.v1_8_R3.ScoreboardScore;

public class ScoreboardAPI implements Listener {

	Main pl;

	public ScoreboardAPI(Main instance) {
		pl = instance;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		sendScoreboard(p);
		for (Player all : Bukkit.getOnlinePlayers()) {
			sendScoreboard(all);
		}
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		for(Player all : Bukkit.getOnlinePlayers()) {
			sendScoreboard(all);
		}
	}

	public static void sendScoreboard(Player p) {
		SetupScoreboard sm = Main.instance.setupScoreboard;
		String displayname = sm.getString("ScoreboardName");
		String score1 = sm.getString("Score1");
		String score2 = sm.getString("Score2");
		String score3 = sm.getString("Score3");
		String score4 = sm.getString("Score4");
		String score5 = sm.getString("Score5");
		String score6 = sm.getString("Score6");
		String score7 = sm.getString("Score7");
		String score8 = sm.getString("Score8");
		String score9 = sm.getString("Score9");
		String score10 = sm.getString("Score10");
		String score11 = sm.getString("Score11");
		String score12 = sm.getString("Score12");
		String score13 = sm.getString("Score13");
		String score14 = sm.getString("Score14");
		String score15 = sm.getString("Score15");

		boolean score1b = sm.getBoolean("Score1boolean");
		boolean score2b = sm.getBoolean("Score2boolean");
		boolean score3b = sm.getBoolean("Score3boolean");
		boolean score4b = sm.getBoolean("Score4boolean");
		boolean score5b = sm.getBoolean("Score5boolean");
		boolean score6b = sm.getBoolean("Score6boolean");
		boolean score7b = sm.getBoolean("Score7boolean");
		boolean score8b = sm.getBoolean("Score8boolean");
		boolean score9b = sm.getBoolean("Score9boolean");
		boolean score10b = sm.getBoolean("Score10boolean");
		boolean score11b = sm.getBoolean("Score11boolean");
		boolean score12b = sm.getBoolean("Score12boolean");
		boolean score13b = sm.getBoolean("Score13boolean");
		boolean score14b = sm.getBoolean("Score14boolean");
		boolean score15b = sm.getBoolean("Score15boolean");

		Scoreboard board = new Scoreboard();
		ScoreboardObjective obj = board.registerObjective("a", IScoreboardCriteria.b);

		obj.setDisplayName(displayname.replaceAll("%onlineplayers%", String.valueOf(Bukkit.getOnlinePlayers().size()))
				.replaceAll("%playername%", p.getName()));

		PacketPlayOutScoreboardObjective createPacket = new PacketPlayOutScoreboardObjective(obj, 0);
		PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(obj, 1);
		PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);

		ScoreboardScore s1 = new ScoreboardScore(board, obj,
				score1.replaceAll("%onlineplayers%", String.valueOf(Bukkit.getOnlinePlayers().size()))
						.replaceAll("%playername%", p.getName()));

		ScoreboardScore s2 = new ScoreboardScore(board, obj,
				score2.replaceAll("%onlineplayers%", String.valueOf(Bukkit.getOnlinePlayers().size()))
						.replaceAll("%playername%", p.getName()));

		ScoreboardScore s3 = new ScoreboardScore(board, obj,
				score3.replaceAll("%onlineplayers%", String.valueOf(Bukkit.getOnlinePlayers().size()))
						.replaceAll("%playername%", p.getName()));
		ScoreboardScore s4 = new ScoreboardScore(board, obj,
				score4.replaceAll("%onlineplayers%", String.valueOf(Bukkit.getOnlinePlayers().size()))
						.replaceAll("%playername%", p.getName()));
		ScoreboardScore s5 = new ScoreboardScore(board, obj,
				score5.replaceAll("%onlineplayers%", String.valueOf(Bukkit.getOnlinePlayers().size()))
						.replaceAll("%playername%", p.getName()));
		ScoreboardScore s6 = new ScoreboardScore(board, obj,
				score6.replaceAll("%onlineplayers%", String.valueOf(Bukkit.getOnlinePlayers().size()))
						.replaceAll("%playername%", p.getName()));
		ScoreboardScore s7 = new ScoreboardScore(board, obj,
				score7.replaceAll("%onlineplayers%", String.valueOf(Bukkit.getOnlinePlayers().size()))
						.replaceAll("%playername%", p.getName()));
		ScoreboardScore s8 = new ScoreboardScore(board, obj,
				score8.replaceAll("%onlineplayers%", String.valueOf(Bukkit.getOnlinePlayers().size()))
						.replaceAll("%playername%", p.getName()));
		ScoreboardScore s9 = new ScoreboardScore(board, obj,
				score9.replaceAll("%onlineplayers%", String.valueOf(Bukkit.getOnlinePlayers().size()))
						.replaceAll("%playername%", p.getName()));
		ScoreboardScore s10 = new ScoreboardScore(board, obj,
				score10.replaceAll("%onlineplayers%", String.valueOf(Bukkit.getOnlinePlayers().size()))
						.replaceAll("%playername%", p.getName()));
		ScoreboardScore s11 = new ScoreboardScore(board, obj,
				score11.replaceAll("%onlineplayers%", String.valueOf(Bukkit.getOnlinePlayers().size()))
						.replaceAll("%playername%", p.getName()));
		ScoreboardScore s12 = new ScoreboardScore(board, obj,
				score12.replaceAll("%onlineplayers%", String.valueOf(Bukkit.getOnlinePlayers().size()))
						.replaceAll("%playername%", p.getName()));
		ScoreboardScore s13 = new ScoreboardScore(board, obj,
				score13.replaceAll("%onlineplayers%", String.valueOf(Bukkit.getOnlinePlayers().size()))
						.replaceAll("%playername%", p.getName()));
		ScoreboardScore s14 = new ScoreboardScore(board, obj,
				score14.replaceAll("%onlineplayers%", String.valueOf(Bukkit.getOnlinePlayers().size()))
						.replaceAll("%playername%", p.getName()));
		ScoreboardScore s15 = new ScoreboardScore(board, obj,
				score15.replaceAll("%onlineplayers%", String.valueOf(Bukkit.getOnlinePlayers().size()))
						.replaceAll("%playername%", p.getName()));

		if (score1b == true) {
			s1.setScore(1);
		}
		if (score2b == true) {
			s2.setScore(2);
		}
		if (score3b == true) {
			s3.setScore(3);
		}
		if (score4b == true) {
			s4.setScore(4);
		}
		if (score5b == true) {
			s5.setScore(5);
		}
		if (score6b == true) {
			s6.setScore(6);
		}
		if (score7b == true) {
			s7.setScore(7);
		}
		if (score8b == true) {
			s8.setScore(8);
		}
		if (score9b == true) {
			s9.setScore(9);
		}
		if (score10b == true) {
			s10.setScore(10);
		}
		if (score11b == true) {
			s11.setScore(11);
		}
		if (score12b == true) {
			s12.setScore(12);
		}
		if (score13b == true) {
			s13.setScore(13);
		}
		if (score14b == true) {
			s14.setScore(14);
		}
		if (score15b == true) {
			s15.setScore(15);
		}
		PacketPlayOutScoreboardScore p1 = new PacketPlayOutScoreboardScore(s1);
		PacketPlayOutScoreboardScore p2 = new PacketPlayOutScoreboardScore(s2);
		PacketPlayOutScoreboardScore p3 = new PacketPlayOutScoreboardScore(s3);
		PacketPlayOutScoreboardScore p4 = new PacketPlayOutScoreboardScore(s4);
		PacketPlayOutScoreboardScore p5 = new PacketPlayOutScoreboardScore(s5);
		PacketPlayOutScoreboardScore p6 = new PacketPlayOutScoreboardScore(s6);
		PacketPlayOutScoreboardScore p7 = new PacketPlayOutScoreboardScore(s7);
		PacketPlayOutScoreboardScore p8 = new PacketPlayOutScoreboardScore(s8);
		PacketPlayOutScoreboardScore p9 = new PacketPlayOutScoreboardScore(s9);
		PacketPlayOutScoreboardScore p10 = new PacketPlayOutScoreboardScore(s10);
		PacketPlayOutScoreboardScore p11 = new PacketPlayOutScoreboardScore(s11);
		PacketPlayOutScoreboardScore p12 = new PacketPlayOutScoreboardScore(s12);
		PacketPlayOutScoreboardScore p13 = new PacketPlayOutScoreboardScore(s13);
		PacketPlayOutScoreboardScore p14 = new PacketPlayOutScoreboardScore(s14);
		PacketPlayOutScoreboardScore p15 = new PacketPlayOutScoreboardScore(s15);

		sendPacket(p, removePacket);
		sendPacket(p, createPacket);
		sendPacket(p, display);
		if (score1b == true) {
			sendPacket(p, p1);
		}
		if (score2b == true) {
			sendPacket(p, p2);
		}
		if (score3b == true) {
			sendPacket(p, p3);
		}
		if (score4b == true) {
			sendPacket(p, p4);
		}
		if (score5b == true) {
			sendPacket(p, p5);
		}
		if (score6b == true) {
			sendPacket(p, p6);
		}
		if (score7b == true) {
			sendPacket(p, p7);
		}
		if (score8b == true) {
			sendPacket(p, p8);
		}
		if (score9b == true) {
			sendPacket(p, p9);
		}
		if (score10b == true) {
			sendPacket(p, p10);
		}
		if (score11b == true) {
			sendPacket(p, p11);
		}
		if (score12b == true) {
			sendPacket(p, p12);
		}
		if (score13b == true) {
			sendPacket(p, p13);
		}
		if (score14b == true) {
			sendPacket(p, p14);
		}
		if (score15b == true) {
			sendPacket(p, p15);
		}

	}

	public static void sendPacket(Player p, Packet<?> packet) {
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	}
}
