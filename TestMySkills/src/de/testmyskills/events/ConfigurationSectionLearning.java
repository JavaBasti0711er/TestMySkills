package de.testmyskills.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.testmyskills.Main;
import de.testmyskills.utils.SetupMessages;

public class ConfigurationSectionLearning implements Listener {

	Main pl;

	public ConfigurationSectionLearning(Main instance) {
		pl = instance;
	}

	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		SetupMessages m = pl.setupMessages;
		List<String> idcrystallore = m.getStringList("Crystals.Identified.lore");
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (e.getItem() != null) {
				ItemStack pitem = p.getItemInHand();
				String tierlores = "";
				for (String loresinhand : pitem.getItemMeta().getLore()) {

					if (loresinhand.contains("Tier")) {
						String[] t = ChatColor.stripColor(pl.colorize(loresinhand)).split(" ");
						tierlores = t[1];
						break;
					}
				}

				for (String str : m.getConfigurationSection("CrystalTiers").getKeys(false)) {
					if (tierlores.equals(str)) {

						ItemStack crystal = new ItemStack(Material.NETHER_STAR);
						ItemMeta crystam = crystal.getItemMeta();

						crystam.setDisplayName(pl.colorize(m.getString("CrystalTiers." + str + ".identifiedname")));
						ArrayList<String> lore = new ArrayList<String>();
						for (String lores : idcrystallore) {
							lore.add(pl.colorize(lores)
									.replaceAll("%tier%", m.getString("CrystalTiers." + str + ".LoreTier"))
									.replaceAll("%effect%", String.valueOf(randomEffect())).replaceAll("%percentage%",
											String.valueOf(
													randomPercentage(m.getInt("CrystalTiers." + str + ".minpercentage"),
															m.getInt("CrystalTiers." + str + ".maxpercentage")))));
						}
						crystam.setLore(lore);
						crystal.setItemMeta(crystam);
						removeItems(p, pitem, 1);
						p.getInventory().addItem(crystal);
					}
				}
			} else {
				return;
			}
		}
	}

	private int randomPercentage(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}

	@SuppressWarnings("unused")
	private String randomEffect() {
		SetupMessages m = pl.setupMessages;
		Random r = new Random();
		Integer i = 0;
		for (String str : m.getConfigurationSection("Crystals.CrystalEffects").getKeys(false)) {
			i++;

		}

		Integer x = r.nextInt(i) + 1;

		for (String stri : m.getConfigurationSection("Crystals.CrystalEffects").getKeys(false)) {
			if (String.valueOf(x).equals(stri)) {
				return m.getString("Crystals.CrystalEffects." + stri);
			}
		}
		return null;

	}

	public void removeItems(Player p, ItemStack itemStack, int amount) {

		int removedItems = 0;

		int i1 = p.getInventory().getSize();

		while (i1 != -1) {

			org.bukkit.inventory.ItemStack it = p.getInventory().getItem(i1);

			if (removedItems != amount && it != null && it.equals(itemStack)) {

				p.getInventory().clear(i1);
				;
				removedItems += it.getAmount();

			}

			i1--;

		}

		if (removedItems > amount) {

			int diff = removedItems - amount;

			for (int i = diff; i != 0; i--) {

				org.bukkit.inventory.ItemStack m = itemStack;
				m.setAmount(1);
				p.getInventory().addItem(m);

			}
		}
	}
}
