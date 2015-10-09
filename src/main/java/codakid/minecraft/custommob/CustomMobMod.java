/*************************************************************
 ***********CODAKID MINECRAFT MODDING TEMPLATES***************
 ************************************************************/

package codakid.minecraft.custommob;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = CustomMobMod.MODID, version = CustomMobMod.VERSION)
public class CustomMobMod
{
	public static final String MODID = "custommob";
	public static final String VERSION = "1.0";

	//Declare Here
	public static int mobType;
	public static int eggColor;
	public static int eggSpotColor;

	@EventHandler
	public void init(FMLInitializationEvent event)
	{

		//Pick Mob Type (makeBiped(), makePig(), or makeWolf())
		makeWolf();

		//Pick Egg Colors
		this.eggColor = Colors.blue;
		this.eggSpotColor = Colors.orange;
		
		//Register Mob
		Helper.createMob();

	}

	private void makeBiped() {

		this.mobType = 0;
	}

	private void makePig() {

		this.mobType = 1;
	}

	private void makeWolf() {

		this.mobType = 2;

	}
	
}
