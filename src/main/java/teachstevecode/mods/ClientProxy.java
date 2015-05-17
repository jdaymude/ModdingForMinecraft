package teachstevecode.mods;

import teachstevecode.mods.EntityLavaCreeper;
import teachstevecode.mods.RenderLavaCreeper;
import teachstevecode.mods.EntityLavaBombPrimed;
import teachstevecode.mods.RenderLavaBombPrimed;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderTNTPrimed;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

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
		
		//LavaBomb
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(Item.getItemFromBlock(super.lavabomb), 0, new ModelResourceLocation(LavaBomb.MODID + ":" + super.lavabomb.getUnlocalizedName().substring(5), "inventory"));
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) 
	{
		super.postInit(e);
	}
	
	@Override
	public void registerRenderers() 
	{
		//LavaBomb
		RenderingRegistry.registerEntityRenderingHandler(EntityLavaBombPrimed.class, new RenderLavaBombPrimed(Minecraft.getMinecraft().getRenderManager()));

		//LavaCreeper
		float shadowSize = 0.0f;
		RenderManager rm = Minecraft.getMinecraft().getRenderManager();
		
		RenderingRegistry.registerEntityRenderingHandler(EntityLavaCreeper.class, new RenderLavaCreeper(rm));
	
	
	}
}
