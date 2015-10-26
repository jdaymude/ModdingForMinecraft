package teachstevecode.tank;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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
		
		// Register Renderer
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
        		.register(Tank.itemTank, 0, new ModelResourceLocation(Tank.MODID + ":" + Tank.itemTank.getUnlocalizedName().substring(5), "inventory"));
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

