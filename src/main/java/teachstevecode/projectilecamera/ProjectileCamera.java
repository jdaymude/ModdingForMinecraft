package teachstevecode.projectilecamera;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = ProjectileCamera.MODID, name = ProjectileCamera.MODNAME, version = ProjectileCamera.MODVER)
public class ProjectileCamera 
{
	public static final String MODID = "projectilecamera";
    public static final String MODNAME = "Projectile Camera";
    public static final String MODVER = "1.0.0";
    
    public static KeyBinding useCameraKey;
    public static boolean useCameraKeyPressed;
    
    @Instance(MODID)
    public static ProjectileCamera projectileCamera = new ProjectileCamera();

    @SidedProxy(clientSide = "teachstevecode.projectilecamera.ClientProxy", serverSide = "teachstevecode.projectilecamera.ServerProxy")
    public static CommonProxy commonProxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) 
    {
        this.commonProxy.preInit(e);
        
        //Register the key for using the camera
        int cameraKey = Keyboard.KEY_C;
        useCameraKey = new KeyBinding("key.useCameraKey", cameraKey, "key.categories.projectilecamera");
        ClientRegistry.registerKeyBinding(useCameraKey);
        MinecraftForge.EVENT_BUS.register(new ProjectileCameraEventHandler());
        
        //Register the camera entity
        EntityRegistry.registerModEntity(EntityProjectileCamera.class, "EntityProjectileCamera", EntityRegistry.findGlobalUniqueEntityId(), this, 50, 5, false);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) 
    {
        this.commonProxy.init(e);
   }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) 
    {
        this.commonProxy.postInit(e);
    }
}
