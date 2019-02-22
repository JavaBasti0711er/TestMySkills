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

		String tier1unid = m.getString("Crystals.Tier1.unidentifiedname");
		String tier2unid = m.getString("Crystals.Tier2.unidentifiedname");
		String tier3unid = m.getString("Crystals.Tier3.unidentifiedname");
		String tier4unid = m.getString("Crystals.Tier4.unidentifiedname");
		String tier5unid = m.getString("Crystals.Tier5.unidentifiedname");
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
			if (args[0].equalsIgnoreCase("1")) {
				ItemStack crystaltier1 = new ItemStack(crystalitem);
				ItemMeta crystaltier1meta = crystaltier1.getItemMeta();
				crystaltier1meta.setDisplayName(tier1unid);
				ArrayList<String> lore1 = new ArrayList<String>();
				for (String lores : unidcrystallore) {
					lore1.add(pl.colorize(lores));
				}
				crystaltier1meta.setLore(lore1);
				crystaltier1.setItemMeta(crystaltier1meta);
				p.getInventory().addItem(crystaltier1);
			} else if (args[0].equalsIgnoreCase("2")) {
				ItemStack crystaltier2 = new ItemStack(crystalitem);
				ItemMeta crystaltier2meta = crystaltier2.getItemMeta();
				crystaltier2meta.setDisplayName(tier2unid);
				ArrayList<String> lore2 = new ArrayList<String>();
				for (String lores : unidcrystallore) {
					lore2.add(pl.colorize(lores));
				}
				crystaltier2meta.setLore(lore2);
				crystaltier2.setItemMeta(crystaltier2meta);
				p.getInventory().addItem(crystaltier2);
			} else if (args[0].equalsIgnoreCase("3")) {
				ItemStack crystaltier3 = new ItemStack(crystalitem);
				ItemMeta crystaltier3meta = crystaltier3.getItemMeta();
				crystaltier3meta.setDisplayName(tier3unid);
				ArrayList<String> lore3 = new ArrayList<String>();
				for (String lores : unidcrystallore) {
					lore3.add(pl.colorize(lores));
				}
				crystaltier3meta.setLore(lore3);
				crystaltier3.setItemMeta(crystaltier3meta);
				p.getInventory().addItem(crystaltier3);
			} else if (args[0].equalsIgnoreCase("4")) {
				ItemStack crystaltier4 = new ItemStack(crystalitem);
				ItemMeta crystaltier4meta = crystaltier4.getItemMeta();
				crystaltier4meta.setDisplayName(tier4unid);
				ArrayList<String> lore4 = new ArrayList<String>();
				for (String lores : unidcrystallore) {
					lore4.add(pl.colorize(lores));
				}
				crystaltier4meta.setLore(lore4);
				crystaltier4.setItemMeta(crystaltier4meta);
				p.getInventory().addItem(crystaltier4);
			} else if (args[0].equalsIgnoreCase("5")) {
				ItemStack crystaltier5 = new ItemStack(crystalitem);
				ItemMeta crystaltier5meta = crystaltier5.getItemMeta();
				crystaltier5meta.setDisplayName(tier5unid);
				ArrayList<String> lore5 = new ArrayList<String>();
				for (String lores : unidcrystallore) {
					lore5.add(pl.colorize(lores));
				}
				crystaltier5meta.setLore(lore5);
				crystaltier5.setItemMeta(crystaltier5meta);
				p.getInventory().addItem(crystaltier5);
			}
		}

		return true;
	}

}
