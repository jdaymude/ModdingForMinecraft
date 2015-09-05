package teachstevecode.lavabomb;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = LavaBomb.MODID, name = LavaBomb.MODNAME, version = LavaBomb.MODVER)
public class LavaBomb 
{
    public static final String MODID = "lavabomb";
    public static final String MODNAME = "Lava Bomb";
    public static final String MODVER = "1.0.0";
    
    @Instance(value = "lavabomb")
    public static LavaBomb instance = new LavaBomb();

    @SidedProxy(clientSide = "teachstevecode.lavabomb.ClientProxy", serverSide = "teachstevecode.lavabomb.ServerProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) 
    {
        this.proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) 
    {
        this.proxy.init(e);
        
        EntityRegistry.registerModEntity(EntityLavaBombPrimed.class, "lavabombprimed", 51, this, 256, 1, false);
        this.proxy.registerRenderers();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) 
    {
        this.proxy.postInit(e);
    }
}