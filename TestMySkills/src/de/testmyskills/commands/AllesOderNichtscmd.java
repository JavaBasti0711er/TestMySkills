package de.testmyskills.commands;

import java.util.Random;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.testmyskills.Main;
import de.testmyskills.utils.SetupMessages;

public class AllesOderNichtscmd implements CommandExecutor {

	Main pl;

	public AllesOderNichtscmd(Main instance) {
		pl = instance;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		SetupMessages m = pl.setupMessages;

		String prefix = m.getString("Prefix");
		String consoleplayer = m.getString("YouHaveToBeAPlayer");
		String everythingornothing4 = m.getString("EverythingOrNothing.endmessage");
		int money = randomMoney(m.getInt("EverythingOrNothing.Money.minmoney"),
				m.getInt("EverythingOrNothing.Money.maxmoney"));
		int coins = randomCoins(m.getInt("EverythingOrNothing.Coins.mincoins"),
				m.getInt("EverythingOrNothing.Coins.maxcoins"));
		if (!(cs instanceof Player)) {
			cs.sendMessage(consoleplayer);
			return true;
		}
		Player p = (Player) cs;

		p.sendMessage(everythingornothing4.replaceAll("%randommoney%", String.valueOf(money))
				.replaceAll("%randomcoins%", String.valueOf(coins)).replaceAll("%prefix%", prefix));

		return true;
	}

	private int randomMoney(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}

	private int randomCoins(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}

}
