package teachstevecode.tank;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
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

@Mod(modid = Tank.MODID, name = Tank.MODNAME, version = Tank.MODVER)
public class Tank
{
    public static final String MODID = "tank";
    public static final String MODNAME = "Tank";
    public static final String MODVER = "1.0.0";
    
    @Instance(value = "tank")
    public static Tank instance = new Tank();
    public static ItemTank itemTank;

    @SidedProxy(clientSide = "teachstevecode.tank.ClientProxy", serverSide = "teachstevecode.tank.ServerProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) 
    {        
        itemTank = new ItemTank("tank");
        
        this.proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) 
    {
        this.proxy.init(e);
        
        GameRegistry.registerItem(itemTank, itemTank.getUnlocalizedName().substring(5));     
        EntityRegistry.registerModEntity(EntityTank.class, "car", 51, this, 256, 1, false);        
        RenderingRegistry.registerEntityRenderingHandler(EntityTank.class, new RenderTank(Minecraft.getMinecraft().getRenderManager()));        
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) 
    {
        this.proxy.postInit(e);
    }
}