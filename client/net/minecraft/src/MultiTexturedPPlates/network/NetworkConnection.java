package net.minecraft.src.MultiTexturedPPlates.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.EurysMods.network.INetworkConnections;
import net.minecraft.src.EurysMods.network.PacketIds;
import net.minecraft.src.MultiTexturedPPlates.MultiTexturedPPlates;
import net.minecraft.src.forge.MessageManager;

public class NetworkConnection implements INetworkConnections
{
	private static String modChannel = MultiTexturedPPlates.Core.getModChannel();
	
	@Override
	public void onPacketData(NetworkManager network, String channel, byte[] bytes) 
	{
		//mc.thePlayer.addChatMessage(" Client Packet Received");
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(bytes));
		try
		{
			NetClientHandler net = (NetClientHandler)network.getNetHandler();

			int packetID = data.read();
			switch (packetID) {
			case PacketIds.MTPPLATE_UPDATE:
				PacketUpdateMTPPlate packetPPlate = new PacketUpdateMTPPlate();
				packetPPlate.readData(data);
				MultiTexturedPPlates.Core.getPacketHandler().handleTileEntityPacket(packetPPlate, null);
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
		//mc.thePlayer.addChatMessage("Client Channel Registered");
	}

	@Override
	public void onDisconnect(NetworkManager network, String message, Object[] args)
	{
		
	}
}
