package de.testmyskills;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.testmyskills.commands.AllesOderNichtscmd;
import de.testmyskills.commands.CrystalGiveCMD;
import de.testmyskills.commands.ReloadCFG;
import de.testmyskills.commands.randomGG;
import de.testmyskills.events.ConfigurationSectionLearning;
import de.testmyskills.events.MotdEvent;
import de.testmyskills.utils.ProtocolLibImplementation;
import de.testmyskills.utils.ScoreboardAPI;
import de.testmyskills.utils.SetupMessages;
import de.testmyskills.utils.SetupScoreboard;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Main extends JavaPlugin {

	public static Main instance;
	public SetupMessages setupMessages;
	public SetupScoreboard setupScoreboard;
	public boolean gg = false;
	
	public static Main getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(colorize("&8-----------------------------"));
		Bukkit.getConsoleSender().sendMessage(colorize("&6Author: " + getDescription().getAuthors()));
		Bukkit.getConsoleSender().sendMessage(colorize("&6Version: " + getDescription().getVersion()));
		Bukkit.getConsoleSender().sendMessage(colorize("&6If you want to suggest a new feature.. / or need help.."));
		Bukkit.getConsoleSender().sendMessage(colorize("&6message me on Discord: JavaBasti#2246"));
		Bukkit.getConsoleSender().sendMessage(colorize("&8-----------------------------"));

		instance = this;
		ProtocolLibImplementation pli = new ProtocolLibImplementation(this);

		this.setupMessages = new SetupMessages();
		this.setupScoreboard = new SetupScoreboard();

		Bukkit.getPluginCommand("givecrystal").setExecutor(new CrystalGiveCMD(this));
		Bukkit.getPluginCommand("allesodernichts").setExecutor(new AllesOderNichtscmd(this));
		Bukkit.getPluginCommand("randomgg").setExecutor(new randomGG(this));
		Bukkit.getPluginCommand("configreload").setExecutor(new ReloadCFG(this));
		Bukkit.getPluginManager().registerEvents(new randomGG(this), this);
		Bukkit.getPluginManager().registerEvents(new ConfigurationSectionLearning(this), this);
		Bukkit.getPluginManager().registerEvents(new MotdEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new ScoreboardAPI(this), this);
		pli.listenToServerlistPackets();

	}

	public void clickable(Player p, String commandshown, String event, String cmd, String showmessage) {

		TextComponent clickablemsg = new TextComponent(colorize(commandshown));
		clickablemsg.setClickEvent(new ClickEvent(ClickEvent.Action.valueOf(event.toUpperCase()), cmd));
		clickablemsg.setHoverEvent(
				new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(colorize(showmessage)).create()));

		p.spigot().sendMessage(clickablemsg);
	}

	public String colorize(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

	public String decimals(Integer decimalSpaces, Double number) {
		return String.format("%1$,." + decimalSpaces + "f", number);
	}
}
