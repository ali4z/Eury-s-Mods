package net.minecraft.src.EurysMods.network;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;

public interface IPacketHandling
{
	//MinecraftServer mcServer = ModLoader.getMinecraftServerInstance();
	
    public void handleTileEntityPacket(PacketUpdate packet, EntityPlayer var2);
    
	public void handleGuiPacket(PacketUpdate packet, EntityPlayer var2);
}