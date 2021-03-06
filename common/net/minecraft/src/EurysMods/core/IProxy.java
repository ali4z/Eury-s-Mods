package net.minecraft.src.EurysMods.core;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Packet;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.EurysMods.network.PacketPayload;
import net.minecraft.src.forge.NetworkMod;

public interface IProxy
{
	public PacketPayload getPayload(int[] dataInt, float[] dataFloat, String[] dataString);
	public void sendPacket(EntityPlayer entityplayer, Packet packet);
	public void sendPacketToAll(Packet packet, int x, int y, int z, int maxDistance, NetworkMod mod);
}