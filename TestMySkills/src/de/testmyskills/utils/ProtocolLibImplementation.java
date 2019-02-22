package de.testmyskills.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedServerPing;

import de.testmyskills.Main;

public class ProtocolLibImplementation {
	private static Main pl;

	public ProtocolLibImplementation(Main instance) {
		pl = instance;
	}

	public void listenToServerlistPackets() {
		ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(
				PacketAdapter.params(pl, new PacketType[] { PacketType.Status.Server.SERVER_INFO }).optionAsync()) {
			public void onPacketSending(PacketEvent event) {
				WrappedServerPing ping = (WrappedServerPing) event.getPacket().getServerPings().read(0);

				activateHoverText(ping, ProtocolLibImplementation.pl);
			}

		});
		;
	}

	public void activateHoverText(WrappedServerPing ping, Main pl) {
		SetupMessages m = pl.setupMessages;
		List<WrappedGameProfile> players = new ArrayList<WrappedGameProfile>();
		for (String string : m.getStringList("PlayersOnlineHoverText")) {
			players.add(new WrappedGameProfile(UUID.randomUUID(), formatText(string)));
		}
		ping.setPlayers(players);
	}

	private String formatText(String hoverLine) {
		String formatted = hoverLine;
		formatted = pl.colorize(hoverLine).replaceAll("%line%", "\n");
		return formatted;
	}

}
