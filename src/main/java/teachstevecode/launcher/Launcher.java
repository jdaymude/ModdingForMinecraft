package teachstevecode.launcher;


import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teachstevecode.launcher.grenade.EntityGrenade;
import teachstevecode.launcher.grenade.ItemGrenade;

@Mod(modid = Launcher.MODID, name = Launcher.MODNAME, version = Launcher.MODVER)
public class Launcher 
{
	public static final String MODID = "launcher";
    public static final String MODNAME = "Launcher";
    public static final String MODVER = "1.0.0";
    
    @Instance(MODID)
    public static Launcher instance = new Launcher();
    public static ItemLauncher launcher;
    public static Item grenade;

    @SidedProxy(clientSide = "teachstevecode.launcher.ClientProxy", serverSide = "teachstevecode.launcher.ServerProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) 
    {
        this.proxy.preInit(e);
        
        launcher = new ItemLauncher(MODID);
        grenade = new ItemGrenade("grenade");
    }

    @EventHandler
    public void init(FMLInitializationEvent e) 
    {
        this.proxy.init(e);

        // Register Items
        GameRegistry.registerItem(launcher, launcher.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(grenade, grenade.getUnlocalizedName().substring(5));

        int id = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityGrenade.class, "grenade", id);
        EntityRegistry.registerModEntity(EntityGrenade.class, "grenade", id, this, 256, 10, true);        
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) 
    {
        this.proxy.postInit(e);
    }
}
