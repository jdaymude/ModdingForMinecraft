package teachstevecode.launcher;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import teachstevecode.launcher.grenade.EntityGrenade;
import teachstevecode.launcher.grenade.ItemGrenade;

public class ClientProxy extends CommonProxy 
{
	@Override
	public void preInit(FMLPreInitializationEvent e) 
	{
		super.preInit(e);
	}

	@Override
	public void init(FMLInitializationEvent e) 
	{
		super.init(e);
		
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		
        // Register Renderer    
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderSnowball(renderManager, Launcher.grenade, renderItem));
		renderItem.getItemModelMesher().register(Launcher.grenade, 0, new ModelResourceLocation(Launcher.MODID + ":grenade", "inventory"));
		
		renderItem.getItemModelMesher().register(Launcher.launcher, 0, new ModelResourceLocation(Launcher.MODID + ":launcher", "inventory"));
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) 
	{
		super.postInit(e);
	}
	
	@Override
	public void registerRenderers() 
	{
		
	}
}