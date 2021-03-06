package net.minecraft.src.MultiTexturedDoors;

import java.io.File;

import net.minecraft.src.Block;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.EurysMods.ServerCore;
import net.minecraft.src.EurysMods.ServerProxy;
import net.minecraft.src.EurysMods.EurysCore;
import net.minecraft.src.EurysMods.core.ICore;
import net.minecraft.src.EurysMods.core.IMod;
import net.minecraft.src.MultiTexturedDoors.network.NetworkConnection;
import net.minecraft.src.MultiTexturedDoors.network.PacketHandles;
import net.minecraft.src.forge.Configuration;
import net.minecraft.src.forge.MinecraftForge;

public class MultiTexturedDoors
{
	public static String minecraftDir = EurysCore.getMinecraftDir();
    public static ICore Core;
	private static boolean initialized = false;

    public static void initialize()
    {
		if (initialized) return;
		initialized = true;
    	Core = new ServerCore(new ServerProxy(), new PacketHandles());
    	Core.setModName("MultiTexturedDoors");
    	Core.setModChannel("MTD");
    	load();
    }
    
    public static void load()
    {
    	EurysCore.console(Core.getModName(), "Registering Items...");
        ModLoader.registerTileEntity(TileEntityMTDoor.class, "mtDoor");
    	MTDCore.addItems();
    	EurysCore.console(Core.getModName(), "Naming Items...");
    	MTDCore.addNames();
    	EurysCore.console(Core.getModName(), "Registering Recipes...");
    	MTDCore.addRecipes();
    }
}
