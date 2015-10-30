package teachstevecode.projectilecamera;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class ProjectileCameraEventHandler 
{
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{			
		//Make sure we're running on client side only
		if (FMLCommonHandler.instance().getEffectiveSide().isClient())
		{
			//Check if use camera key is being pressed
			ProjectileCamera.useCameraKeyPressed = ProjectileCamera.useCameraKey.isKeyDown();
			
			//Use camera key is being pressed and a projectile has spawned
			if (event.entity instanceof IProjectile && ProjectileCamera.useCameraKeyPressed)
			{			
				//Get projectile
				Entity projectile = event.entity;
				
				//Get camera
				EntityProjectileCamera entityProjectileCamera = EntityProjectileCamera.getInstance();
				
				//Start camera attached to projectile
				entityProjectileCamera.startCamera(projectile);
			}
		}
	}
}
