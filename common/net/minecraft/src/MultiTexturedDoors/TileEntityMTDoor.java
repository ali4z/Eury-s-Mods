package net.minecraft.src.MultiTexturedDoors;

import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Packet;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.EurysMods.network.PacketPayload;
import net.minecraft.src.MultiTexturedDoors.network.PacketUpdateMTDoor;

public class TileEntityMTDoor extends TileEntity
{
	public int metaValue;
    public int doorPiece;
	public int getMetaValue() { return this.metaValue; }
	public void setMetaValue(int meta) { this.metaValue = meta; }
	public int getDoorPiece() { return this.doorPiece; }
	public void setDoorPiece(int piece) { this.doorPiece = piece; }
	
	public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setInteger("metaValue", this.getMetaValue());
        nbttagcompound.setInteger("doorPiece", this.getDoorPiece());
    }
	
    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        this.setMetaValue(nbttagcompound.getInteger("metaValue"));
        this.setDoorPiece(nbttagcompound.getInteger("doorPiece"));
    }
    
	public PacketPayload getPacketPayload() {

		int[] dataInt = new int[2];
		float[] dataFloat = new float[1];
		String[] dataString = new String[1];
		dataInt[0] = this.metaValue;
		dataInt[1] = this.doorPiece;
		dataFloat[0] = 0;
		dataString[0] = "";
		PacketPayload p = new PacketPayload();
		p.intPayload = dataInt;
		p.floatPayload = dataFloat;
		p.stringPayload = dataString;
		return p;
	}
	
	public Packet getDescriptionPacket()
	{
		return getUpdatePacket();
	}
	
	public Packet getUpdatePacket()
	{
		return new PacketUpdateMTDoor(this).getPacket();
	}
	
	public void handleUpdatePacket(PacketUpdateMTDoor packet, World world)
	{
		this.setMetaValue(packet.getItemDamage());
		this.setDoorPiece(packet.getDoorPiece());
		this.onInventoryChanged();
		world.markBlockNeedsUpdate(packet.xPosition, packet.yPosition, packet.zPosition);
	}
}
