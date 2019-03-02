package de.testmyskills.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.javabasti.coinapi.economy.Coin_API;
import de.testmyskills.Main;
import de.testmyskills.utils.SetupMessages;

public class Coin_CMD implements CommandExecutor, Listener {
	Main pl;

	public Coin_CMD(Main instance) {
		pl = instance;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		Coin_API capi = pl.capi;
		SetupMessages m = pl.setupMessages;
		String prefix = m.getString("Prefix");
		String consoleplayer = m.getString("YouHaveToBeAPlayer");
		if (!(cs instanceof Player)) {
			cs.sendMessage(consoleplayer);
			return true;
		}
		Player p = (Player) cs;

		if (args.length == 2 && args[0].equalsIgnoreCase("withdraw")) {
			int amount = Integer.valueOf(args[1]);
			if (capi.hasEnought(p.getUniqueId(), amount)) {
				if (p.getInventory().firstEmpty() >= 0) {
					Material coinitem = Material.valueOf(m.getString("CoinSystem.CoinWithdraw.Item"));
					List<String> coinitemlore = m.getStringList("CoinSystem.CoinWithdraw.Lore");

					ItemStack coinwithdrawitem = new ItemStack(coinitem, amount);
					ItemMeta coinwithdrawitemm = coinwithdrawitem.getItemMeta();
					coinwithdrawitemm.setDisplayName(pl.colorize(m.getString("CoinSystem.CoinWithdraw.ItemName")
							.replaceAll("%amount%", String.valueOf(amount))));
					ArrayList<String> lore = new ArrayList<String>();
					for (String lores : coinitemlore) {
						lore.add(pl.colorize(lores).replaceAll("%amount%", String.valueOf(amount)));
					}
					coinwithdrawitemm.setLore(lore);
					coinwithdrawitem.setItemMeta(coinwithdrawitemm);
					p.getInventory().addItem(coinwithdrawitem);
					p.sendMessage(pl.colorize(m.getString("CoinSystem.CoinWithdraw.CoinsWithdrawed")
							.replaceAll("%prefix%", prefix).replaceAll("%amount%", String.valueOf(amount))));
					capi.removeCoins(p.getUniqueId(), amount);
				} else {
					p.sendMessage(pl.colorize(m.getString("CoinSystem.CoinWithdraw.NotEnoughSpaceInInventory")
							.replaceAll("%prefix%", prefix).replaceAll("%amount%", String.valueOf(amount))));
				}
			} else {
				p.sendMessage(pl.colorize(m.getString("CoinSystem.CoinWithdraw.NotEnoughCoins")
						.replaceAll("%prefix%", prefix).replaceAll("%amount%", String.valueOf(amount))));
			}
		}

		return true;
	}

	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Coin_API capi = pl.capi;
		SetupMessages m = pl.setupMessages;
		Material coinitem = Material.valueOf(m.getString("CoinSystem.CoinWithdraw.Item"));

		if (!(p.getItemInHand().getType().equals(Material.AIR)) || p.getItemInHand().getType() != null) {
			if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				if (p.getItemInHand().getType().equals(coinitem)) {
					capi.addCoins(p.getUniqueId(), p.getItemInHand().getAmount());
					p.getItemInHand().setType(Material.AIR);
				}
			}
		}

	}

	public static String decimals(Integer decimalSpaces, Double number) {
		return String.format("%1$,." + decimalSpaces + "f", number);
	}

}
