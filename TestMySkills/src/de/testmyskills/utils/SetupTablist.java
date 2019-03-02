package de.testmyskills.utils;

import java.io.File;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import de.testmyskills.Main;
import net.md_5.bungee.api.ChatColor;

public class SetupTablist {
	Main m;
	File file;
	YamlConfiguration ymlconfig;

	public SetupTablist() {
		this.file = new File(Main.getInstance().getDataFolder(), "tablist.yml");
		if (!this.file.exists()) {
			Main.getInstance().saveResource("tablist.yml", false);
		}
		this.ymlconfig = YamlConfiguration.loadConfiguration(this.file);
	}

	public void load() {
		this.file = new File(Main.getInstance().getDataFolder(), "tablist.yml");
		if (!this.file.exists()) {
			Main.getInstance().saveResource("tablist.yml", false);
		}
		this.ymlconfig = YamlConfiguration.loadConfiguration(this.file);
	}

	public YamlConfiguration getConfiguration() {
		return this.ymlconfig;
	}

	public File getFile() {
		return this.file;
	}

	public String getString(String str) {
		if (this.ymlconfig.contains(str)) {
			return ChatColor.translateAlternateColorCodes('&', this.ymlconfig.getString(str));
		}
		return ChatColor.translateAlternateColorCodes('&', "&cString: " + str + " doesnt exist!");
	}

	public int getInt(String str) {
		if (this.ymlconfig.contains(str)) {
			return this.ymlconfig.getInt(str);
		}
		return 0;
	}

	public Boolean getBoolean(String str) {
		if (this.ymlconfig.contains(str)) {
			return this.ymlconfig.getBoolean(str);
		}
		return null;
	}

	public List<String> getStringList(String str) {
		if (this.ymlconfig.contains(str)) {
			return this.ymlconfig.getStringList(str);
		}
		return null;
	}

	public ConfigurationSection getConfigurationSection(String str) {
		if (this.ymlconfig.contains(str)) {
			return this.ymlconfig.getConfigurationSection(str);
		}
		return null;
	}

	public Boolean contains(String str) {
		if (this.ymlconfig.contains(str)) {
			return true;
		}
		return false;
	}

}