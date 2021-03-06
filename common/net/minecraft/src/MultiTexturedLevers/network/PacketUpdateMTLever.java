package net.minecraft.src.MultiTexturedLevers.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.TileEntity;
import net.minecraft.src.EurysMods.network.PacketIds;
import net.minecraft.src.EurysMods.network.PacketPayload;
import net.minecraft.src.MultiTexturedLevers.TileEntityMTLever;

public class PacketUpdateMTLever extends PacketMTL
{
    public PacketUpdateMTLever()
    {
    	super(PacketIds.MTLEVER_UPDATE);
    }
    
    public PacketUpdateMTLever(TileEntityMTLever tileentitymtlever)
    {
		super(PacketIds.MTLEVER_UPDATE);
	
		this.payload = tileentitymtlever.getPacketPayload();
		TileEntity entity = (TileEntity)tileentitymtlever;
		this.xPosition = entity.xCoord;
		this.yPosition = entity.yCoord;
		this.zPosition = entity.zCoord;
		this.isChunkDataPacket = true;
	}
    
    public PacketUpdateMTLever(int x, int y, int z, int metaValue)
    {
       	super(PacketIds.MTLEVER_UPDATE);

       	this.payload = new PacketPayload(1,0,0);
		this.xPosition = x;
		this.yPosition = y;
		this.zPosition = z;
		this.payload.intPayload[0] = metaValue;
		this.isChunkDataPacket = true;
    }
    
	public int getItemDamage()
	{
		return this.payload.intPayload[0];
	}
}
