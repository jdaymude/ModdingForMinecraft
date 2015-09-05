package teachstevecode.lavabomb;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy 
{
	public static Block lavabomb;
	
    public void preInit(FMLPreInitializationEvent e) 
    {
        GameRegistry.registerBlock(lavabomb = new BlockLavaBomb("lavabomb"), "lavabomb");
    }

    public void init(FMLInitializationEvent e) 
    {

    }

    public void postInit(FMLPostInitializationEvent e) 
    {

    }
    
    public void registerRenderers() 
    {
    	
    }
}