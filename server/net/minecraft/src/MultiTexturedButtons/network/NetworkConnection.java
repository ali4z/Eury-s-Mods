package net.minecraft.src.MultiTexturedButtons.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.src.ModLoader;
import net.minecraft.src.NetServerHandler;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.EurysMods.network.INetworkConnections;
import net.minecraft.src.EurysMods.network.PacketIds;
import net.minecraft.src.MultiTexturedButtons.MTBCore;
import net.minecraft.src.MultiTexturedButtons.MultiTexturedButtons;
import net.minecraft.src.forge.MessageManager;

public class NetworkConnection implements INetworkConnections
{
	private static String modName = MultiTexturedButtons.Core.getModName();
	private static String modVersion = MTBCore.version;
	private static String modChannel = MultiTexturedButtons.Core.getModChannel();
	
	@Override
	public void onPacketData(NetworkManager network, String channel, byte[] bytes) 
	{
		//mc.thePlayer.addChatMessage(" Client Packet Received");
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(bytes));
		try
		{
			NetServerHandler net = (NetServerHandler)network.getNetHandler();

			int packetID = data.read();
			switch (packetID) {
			case PacketIds.MTBUTTON_UPDATE:
				PacketUpdateMTButton packetButton = new PacketUpdateMTButton();
				packetButton.readData(data);
				MultiTexturedButtons.Core.getPacketHandler().handleTileEntityPacket(packetButton, net.getPlayerEntity());
				break;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void onConnect(NetworkManager network)
	{
	}

	@Override
	public void onLogin(NetworkManager network, Packet1Login login)
	{
		MessageManager.getInstance().registerChannel(network, this, modChannel);
		ModLoader.getMinecraftServerInstance().log("Channel Registered: " + modName + " " + modVersion);
	}

	@Override
	public void onDisconnect(NetworkManager network, String message, Object[] args)
	{
		
	}
}
