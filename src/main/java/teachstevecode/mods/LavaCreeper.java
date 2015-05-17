package teachstevecode.mods;

import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.registry.EntityRegistry;


@Mod(modid = LavaCreeper.MOD_ID, name = LavaCreeper.MOD_NAME, version = LavaCreeper.MOD_VER)
public class LavaCreeper  {

    public static final String MOD_ID = "lavacreeper";
    public static final String MOD_NAME = "Lava Creeper";
    public static final String MOD_VER = "1.0.0";	
        
    @SidedProxy(clientSide = "teachstevecode.mods.ClientProxy", serverSide = "teachstevecode.mods.ServerProxy")
    public static CommonProxy proxy;
    
    @Instance(MOD_ID)
    private static LavaCreeper instance;
    
    public static LavaCreeper instance() 
    {
    	return instance;
    }

    /**
     * Add Blocks, Items, Worldgen, and More
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) 
    {
    	this.proxy.preInit(e);
    }

    /**
     * Add TileEntities, Events, Renderers
     */
    @EventHandler
    public void init(FMLInitializationEvent e) 
    {
    	//this.proxy.init(e);
    	FMLCommonHandler.instance().bus().register(LavaCreeper.instance);
    	
    	this.proxy.registerRenderers();
    	
    	int id = EntityRegistry.findGlobalUniqueEntityId();
    	EntityRegistry.registerGlobalEntityID(EntityLavaCreeper.class, "LavaCreeper", id, 0x955109, 0xf18511);
    }

    /**
     * Add Addons and other Mods
     */
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) 
    {
    	this.proxy.postInit(e);
    	
    	// Check net.minecraft.world.biome.BiomeGenBase.java constructor to see rarity of mobs
    	int spawnProb = 50;
    	int min = 4;
    	int max = 4;    	
    	EntityRegistry.addSpawn(EntityLavaCreeper.class, spawnProb, min, max, EnumCreatureType.MONSTER);
    }
    

}
