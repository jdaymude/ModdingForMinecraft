package teachstevecode.car;

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

@Mod(modid = Car.MODID, name = Car.MODNAME, version = Car.MODVER)
public class Car
{
    public static final String MODID = "car";
    public static final String MODNAME = "Car";
    public static final String MODVER = "1.0.0";
    
    @Instance(value = "car")
    public static Car instance = new Car();
    public static ItemCar itemCar;

    @SidedProxy(clientSide = "teachstevecode.car.ClientProxy", serverSide = "teachstevecode.car.ServerProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) 
    {        
        itemCar = new ItemCar("car");
        
        this.proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) 
    {
        this.proxy.init(e);
        
        GameRegistry.registerItem(itemCar, itemCar.getUnlocalizedName().substring(5));     
        EntityRegistry.registerModEntity(EntityCar.class, "car", 51, this, 256, 1, false);        
        RenderingRegistry.registerEntityRenderingHandler(EntityCar.class, new RenderCar(Minecraft.getMinecraft().getRenderManager()));        
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) 
    {
        this.proxy.postInit(e);
    }
}