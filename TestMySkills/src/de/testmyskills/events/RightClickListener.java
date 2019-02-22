package de.testmyskills.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

public class RightClickListener implements Listener {

	Main pl;

	public RightClickListener(Main instance) {
		pl = instance;
	}

	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		SetupMessages m = pl.setupMessages;
		ItemStack iteminhand = p.getInventory().getItemInHand();
		ItemMeta iteminhandmeta = iteminhand.getItemMeta();

		int tier1 = randomPercentage(m.getInt("Crystals.Tier1.minpercentage"),
				m.getInt("Crystals.Tier1.maxpercentage"));
		int tier2 = randomPercentage(m.getInt("Crystals.Tier2.minpercentage"),
				m.getInt("Crystals.Tier2.maxpercentage"));
		int tier3 = randomPercentage(m.getInt("Crystals.Tier3.minpercentage"),
				m.getInt("Crystals.Tier3.maxpercentage"));
		int tier4 = randomPercentage(m.getInt("Crystals.Tier4.minpercentage"),
				m.getInt("Crystals.Tier4.maxpercentage"));
		int tier5 = randomPercentage(m.getInt("Crystals.Tier5.minpercentage"),
				m.getInt("Crystals.Tier5.maxpercentage"));

		String tier1unid = m.getString("Crystals.Tier1.unidentifiedname");
		String tier2unid = m.getString("Crystals.Tier2.unidentifiedname");
		String tier3unid = m.getString("Crystals.Tier3.unidentifiedname");
		String tier4unid = m.getString("Crystals.Tier4.unidentifiedname");
		String tier5unid = m.getString("Crystals.Tier5.unidentifiedname");

		String tier1id = m.getString("Crystals.Tier1.identifiedname");
		String tier2id = m.getString("Crystals.Tier2.identifiedname");
		String tier3id = m.getString("Crystals.Tier3.identifiedname");
		String tier4id = m.getString("Crystals.Tier4.identifiedname");
		String tier5id = m.getString("Crystals.Tier5.identifiedname");

		List<String> idcrystallore = m.getStringList("Crystals.Identified.lore");

		Material unidcrystalitem = Material.valueOf(m.getString("Crystals.UnIdentified.Item"));
		Material idcrystalitem = Material.valueOf(m.getString("Crystals.Identified.Item"));
		if (iteminhand == null || iteminhandmeta == null) {
			return;
		}
		if (iteminhandmeta.getDisplayName().equals(tier1unid) && iteminhand.getType().equals(unidcrystalitem)) {
			if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
				if (iteminhand.getAmount() >= 1) {
					removeItems(p, iteminhand, 1);
					ItemStack newCrystal = new ItemStack(idcrystalitem);
					ItemMeta newCrystalMeta = newCrystal.getItemMeta();
					newCrystalMeta.setDisplayName(tier1id);
					ArrayList<String> lore = new ArrayList<String>();
					for (String lores : idcrystallore) {
						lore.add(pl.colorize(lores).replaceAll("%tier%", "1").replaceAll("%effect%", randomEffect())
								.replaceAll("%percentage%", String.valueOf(tier1)));
					}
					newCrystalMeta.setLore(lore);
					newCrystal.setItemMeta(newCrystalMeta);
					p.getInventory().addItem(newCrystal);
				} else if (iteminhand.getAmount() == 1) {
					removeItems(p, iteminhand, 1);
					ItemStack newCrystal = new ItemStack(idcrystalitem);
					ItemMeta newCrystalMeta = newCrystal.getItemMeta();
					newCrystalMeta.setDisplayName(tier1id);
					ArrayList<String> lore = new ArrayList<String>();
					for (String lores : idcrystallore) {
						lore.add(pl.colorize(lores).replaceAll("%tier%", "1").replaceAll("%effect%", randomEffect())
								.replaceAll("%percentage%", String.valueOf(tier1)));
					}
					newCrystalMeta.setLore(lore);
					newCrystal.setItemMeta(newCrystalMeta);
					p.getInventory().addItem(newCrystal);

				}
			}
		}
		if (iteminhandmeta.getDisplayName().equals(tier2unid) && iteminhand.getType().equals(unidcrystalitem)) {
			if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
				if (iteminhand.getAmount() >= 1) {
					removeItems(p, iteminhand, 1);
					ItemStack newCrystal = new ItemStack(idcrystalitem);
					ItemMeta newCrystalMeta = newCrystal.getItemMeta();
					newCrystalMeta.setDisplayName(tier2id);
					ArrayList<String> lore = new ArrayList<String>();
					for (String lores : idcrystallore) {
						lore.add(pl.colorize(lores).replaceAll("%tier%", "2").replaceAll("%effect%", randomEffect())
								.replaceAll("%percentage%", String.valueOf(tier2)));
					}
					newCrystalMeta.setLore(lore);
					newCrystal.setItemMeta(newCrystalMeta);
					p.getInventory().addItem(newCrystal);
				} else if (iteminhand.getAmount() == 1) {
					removeItems(p, iteminhand, 1);
					ItemStack newCrystal = new ItemStack(idcrystalitem);
					ItemMeta newCrystalMeta = newCrystal.getItemMeta();
					newCrystalMeta.setDisplayName(tier2id);
					ArrayList<String> lore = new ArrayList<String>();
					for (String lores : idcrystallore) {
						lore.add(pl.colorize(lores).replaceAll("%tier%", "2").replaceAll("%effect%", randomEffect())
								.replaceAll("%percentage%", String.valueOf(tier2)));
					}
					newCrystalMeta.setLore(lore);
					newCrystal.setItemMeta(newCrystalMeta);
					p.getInventory().addItem(newCrystal);
				}
			}
		}
		if (iteminhandmeta.getDisplayName().equals(tier3unid) && iteminhand.getType().equals(unidcrystalitem)) {
			if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
				if (iteminhand.getAmount() >= 1) {
					removeItems(p, iteminhand, 1);
					ItemStack newCrystal = new ItemStack(idcrystalitem);
					ItemMeta newCrystalMeta = newCrystal.getItemMeta();
					newCrystalMeta.setDisplayName(tier3id);
					ArrayList<String> lore = new ArrayList<String>();
					for (String lores : idcrystallore) {
						lore.add(pl.colorize(lores).replaceAll("%tier%", "3").replaceAll("%effect%", randomEffect())
								.replaceAll("%percentage%", String.valueOf(tier3)));
					}
					newCrystalMeta.setLore(lore);
					newCrystal.setItemMeta(newCrystalMeta);
					p.getInventory().addItem(newCrystal);
				} else if (iteminhand.getAmount() == 1) {
					removeItems(p, iteminhand, 1);
					ItemStack newCrystal = new ItemStack(idcrystalitem);
					ItemMeta newCrystalMeta = newCrystal.getItemMeta();
					newCrystalMeta.setDisplayName(tier3id);
					ArrayList<String> lore = new ArrayList<String>();
					for (String lores : idcrystallore) {
						lore.add(pl.colorize(lores).replaceAll("%tier%", "3").replaceAll("%effect%", randomEffect())
								.replaceAll("%percentage%", String.valueOf(tier3)));
					}
					newCrystalMeta.setLore(lore);
					newCrystal.setItemMeta(newCrystalMeta);
					p.getInventory().addItem(newCrystal);
				}
			}
		}
		if (iteminhandmeta.getDisplayName().equals(tier4unid) && iteminhand.getType().equals(unidcrystalitem)) {
			if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
				if (iteminhand.getAmount() >= 1) {
					removeItems(p, iteminhand, 1);
					ItemStack newCrystal = new ItemStack(idcrystalitem);
					ItemMeta newCrystalMeta = newCrystal.getItemMeta();
					newCrystalMeta.setDisplayName(tier4id);
					ArrayList<String> lore = new ArrayList<String>();
					for (String lores : idcrystallore) {
						lore.add(pl.colorize(lores).replaceAll("%tier%", "4").replaceAll("%effect%", randomEffect())
								.replaceAll("%percentage%", String.valueOf(tier4)));
					}
					newCrystalMeta.setLore(lore);
					newCrystal.setItemMeta(newCrystalMeta);
					p.getInventory().addItem(newCrystal);
				} else if (iteminhand.getAmount() == 1) {
					removeItems(p, iteminhand, 1);
					ItemStack newCrystal = new ItemStack(idcrystalitem);
					ItemMeta newCrystalMeta = newCrystal.getItemMeta();
					newCrystalMeta.setDisplayName(tier4id);
					ArrayList<String> lore = new ArrayList<String>();
					for (String lores : idcrystallore) {
						lore.add(pl.colorize(lores).replaceAll("%tier%", "4").replaceAll("%effect%", randomEffect())
								.replaceAll("%percentage%", String.valueOf(tier4)));
					}
					newCrystalMeta.setLore(lore);
					newCrystal.setItemMeta(newCrystalMeta);
					p.getInventory().addItem(newCrystal);
				}
			}
		}
		if (iteminhandmeta.getDisplayName().equals(tier5unid) && iteminhand.getType().equals(unidcrystalitem)) {
			if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
				if (iteminhand.getAmount() >= 1) {
					removeItems(p, iteminhand, 1);
					ItemStack newCrystal = new ItemStack(idcrystalitem);
					ItemMeta newCrystalMeta = newCrystal.getItemMeta();
					newCrystalMeta.setDisplayName(tier5id);
					ArrayList<String> lore = new ArrayList<String>();
					for (String lores : idcrystallore) {
						lore.add(pl.colorize(lores).replaceAll("%tier%", "5").replaceAll("%effect%", randomEffect())
								.replaceAll("%percentage%", String.valueOf(tier5)));
					}
					newCrystalMeta.setLore(lore);
					newCrystal.setItemMeta(newCrystalMeta);
					p.getInventory().addItem(newCrystal);
				} else if (iteminhand.getAmount() == 1) {
					removeItems(p, iteminhand, 1);
					ItemStack newCrystal = new ItemStack(idcrystalitem);
					ItemMeta newCrystalMeta = newCrystal.getItemMeta();
					newCrystalMeta.setDisplayName(tier5id);
					ArrayList<String> lore = new ArrayList<String>();
					for (String lores : idcrystallore) {
						lore.add(pl.colorize(lores).replaceAll("%tier%", "5").replaceAll("%effect%", randomEffect())
								.replaceAll("%percentage%", String.valueOf(tier5)));
					}
					newCrystalMeta.setLore(lore);
					newCrystal.setItemMeta(newCrystalMeta);
					p.getInventory().addItem(newCrystal);
				}
			}
		}
	}

	private int randomPercentage(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}

	private String randomEffect() {
		SetupMessages m = pl.setupMessages;
		Random r = new Random();
		Integer x = r.nextInt(3);
		if (x == 0) {
			return m.getString("Crystals.Effect1");
		}
		if (x == 1) {
			return m.getString("Crystals.Effect2");
		}
		if (x == 2) {
			return m.getString("Crystals.Effect3");
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
