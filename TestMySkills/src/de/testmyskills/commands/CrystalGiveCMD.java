package de.testmyskills.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.testmyskills.Main;
import de.testmyskills.utils.SetupMessages;

public class CrystalGiveCMD implements CommandExecutor {

	Main pl;

	public CrystalGiveCMD(Main instance) {
		pl = instance;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		SetupMessages m = pl.setupMessages;
		String prefix = m.getString("Prefix");
		String consoleplayer = m.getString("YouHaveToBeAPlayer");
		String usethis = m.getString("WrongCommand");

		List<String> unidcrystallore = m.getStringList("Crystals.UnIdentified.lore");
		Material crystalitem = Material.valueOf(m.getString("Crystals.UnIdentified.Item"));

		if (!(cs instanceof Player)) {
			cs.sendMessage(consoleplayer);
			return true;
		}

		Player p = (Player) cs;
		if (args.length == 0) {
			p.sendMessage(usethis.replaceAll("%prefix%", prefix));
		} else if (args.length == 1) {
			for (String str : m.getConfigurationSection("CrystalTiers").getKeys(false)) {
				if (args[0].equalsIgnoreCase(str)) {
					ItemStack crystal = new ItemStack(crystalitem);
					ItemMeta crystalm = crystal.getItemMeta();
					crystalm.setDisplayName(pl.colorize(m.getString("CrystalTiers." + str + ".unidentifiedname")));
					ArrayList<String> lore = new ArrayList<String>();
					for (String lores : unidcrystallore) {
						lore.add(pl.colorize(lores).replaceAll("%tier%", str));

					}
					crystalm.setLore(lore);
					crystal.setItemMeta(crystalm);
					p.getInventory().addItem(crystal);
				}
			}
		}

		return true;
	}

}
