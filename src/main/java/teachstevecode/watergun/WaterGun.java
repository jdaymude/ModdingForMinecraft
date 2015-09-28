package teachstevecode.watergun;

import teachstevecode.car.Car;
import teachstevecode.car.CommonProxy;
import teachstevecode.car.EntityCar;
import teachstevecode.car.ItemCar;
import teachstevecode.car.RenderCar;
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

@Mod(modid = WaterGun.MODID, name = WaterGun.MODNAME, version = WaterGun.MODVER)
public class WaterGun 
{
	public static final String MODID = "watergun";
    public static final String MODNAME = "Water Gun";
    public static final String MODVER = "1.0.0";
    
    @Instance(value = "watergun")
    public static WaterGun instance = new WaterGun();
    public static ItemWaterGun waterGun;

    @SidedProxy(clientSide = "teachstevecode.car.ClientProxy", serverSide = "teachstevecode.car.ServerProxy")
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
        
        GameRegistry.registerItem(waterGun = new ItemWaterGun(), "Water Gun");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) 
    {
        this.proxy.postInit(e);
    }
}
