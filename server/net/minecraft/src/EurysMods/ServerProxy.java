package net.minecraft.src.EurysMods;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.mod_EurysMods;
import net.minecraft.src.EurysMods.core.IProxy;
import net.minecraft.src.EurysMods.network.PacketPayload;
import net.minecraft.src.forge.DimensionManager;
import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.NetworkMod;

public class ServerProxy implements IProxy
{
	@Override
	public PacketPayload getPayload(int[] dataInt, float[] dataFloat, String[] dataString)
	{
		int dILength, dFLength, dSLength;
		if (dataInt != null) dILength = dataInt.length; else dILength = 0;
		if (dataFloat != null) dFLength = dataFloat.length; else dFLength = 0;
		if (dataString != null) dSLength = dataString.length; else dSLength = 0;
        PacketPayload payload = new PacketPayload(dILength, dFLength, dSLength);
        payload.intPayload = dataInt;
        payload.floatPayload = dataFloat;
        payload.stringPayload = dataString;
        return payload;
	}

	@Override
	public void sendPacket(EntityPlayer entityplayer, Packet packet)
	{
		EntityPlayerMP playermp = (EntityPlayerMP)entityplayer;
		playermp.playerNetServerHandler.netManager.addToSendQueue(packet);
	}

	@Override
	public void sendPacketToAll(Packet packet, int x, int y, int z, int maxDistance, NetworkMod mod)
	{
		if (packet != null)
		{
			World[] worlds = DimensionManager.getWorlds();
			for (int i = 0; i < worlds.length; i++)
			{
				for (int j = 0; j < worlds[i].playerEntities
						.size(); j++)
				{
					EntityPlayerMP player = (EntityPlayerMP)worlds[i].playerEntities.get(j);

					if (Math.abs(player.posX - x) <= maxDistance
							&& Math.abs(player.posY - y) <= maxDistance
							&& Math.abs(player.posZ - z) <= maxDistance)
						player.playerNetServerHandler.netManager.addToSendQueue(packet);
				}
			}
		}
	}
}