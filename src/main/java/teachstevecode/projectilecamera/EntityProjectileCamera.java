package teachstevecode.projectilecamera;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityProjectileCamera extends EntityLiving 
{
	private static EntityProjectileCamera instance;
	
	//Saved Minecraft game settings
	private boolean hideGUI;
	private float fovSetting;
	private int thirdPersonView;
	
	//Camera information
	private Entity projectile; //object (e.g., EntityArrow) that camera follows
	private boolean isCameraOn; //true if camera is on
	
	public EntityProjectileCamera()
	{
		super(null);
		
		//Make camera invisible
		this.setSize(0.0F, 0.0F);
	}
	
	public EntityProjectileCamera(World world)
	{
		super(world);
	}
	
	public static EntityProjectileCamera getInstance()
	{
		if (instance == null)
		{
			instance = new EntityProjectileCamera();
		}
		
		return instance;
	}
	
	public void startCamera(Entity projectile)
	{
		//Stop any existing cameras
		this.stopCamera();

		//Get Minecraft game settings
		Minecraft minecraft = Minecraft.getMinecraft();
		GameSettings gameSettings = minecraft.gameSettings;
		
		//Save current game settings so we can reset them later
		this.hideGUI = gameSettings.hideGUI;
		this.fovSetting = gameSettings.fovSetting;
		this.thirdPersonView = gameSettings.thirdPersonView;

		//Set new game settings
		gameSettings.hideGUI = true;
		gameSettings.thirdPersonView = 1;
		minecraft.setRenderViewEntity(this);
		
		//Set camera information
		this.projectile = projectile;
		this.isCameraOn = true;
		
		//Spawn the camera
		this.worldObj = projectile.worldObj;
		this.worldObj.spawnEntityInWorld(this);
		
		//Set camera's position and rotation to match player's
		EntityPlayerSP player = minecraft.thePlayer;
		this.setPosition(player.posX, player.posY, player.posZ);
		this.setRotation(player.rotationYaw, player.rotationPitch);
		
		//Move the camera
		this.moveCamera();
	}
	
	public void stopCamera()
	{
		if (this.isCameraOn)
		{
			//Turn off camera
			this.isCameraOn = false;
			
			//Get Minecraft game settings
			Minecraft minecraft = Minecraft.getMinecraft();
			GameSettings gameSettings = minecraft.gameSettings;
			
			//Reset game settings to the saved settings
			gameSettings.hideGUI = this.hideGUI;
			gameSettings.fovSetting = this.fovSetting;
			gameSettings.thirdPersonView = this.thirdPersonView;
			minecraft.setRenderViewEntity(minecraft.thePlayer);
		}
	}
	
	public void moveCamera()
	{
		int positionSmoother = 5;
		
		//Calculate camera's new position
		double newPosX = this.posX + (this.projectile.posX - this.posX) / positionSmoother;
		double newPosY = this.posY + (this.projectile.posY - this.posY) / positionSmoother;
		double newPosZ = this.posZ + (this.projectile.posZ - this.posZ) / positionSmoother;
		
		//Calculate camera's new pitch
		float newPitch = -1 * this.projectile.rotationPitch;
		
		//Set camera's new position and rotation
		this.setPosition(newPosX, newPosY, newPosZ);
		this.setRotation(this.rotationYaw, newPitch);
	}
	
	@Override
	public void onEntityUpdate()
	{
		super.onEntityUpdate();
		
		//Do not update if camera is off
		if (!this.isCameraOn)
		{
			if (this.worldObj != null)
			{
				//Destroy the camera
				this.worldObj.removeEntity(this);
				this.instance = null;
			}
			
			return;
		}
		
		//Stop camera if we are no longer pressing the use camera key
		if (!ProjectileCamera.useCameraKeyPressed)
		{
			this.stopCamera();
			return;
		}
		
		//Update camera's movement
		this.moveCamera();
		
		//Check if use camera key is still being pressed
		ProjectileCamera.useCameraKeyPressed = ProjectileCamera.useCameraKey.isKeyDown();
	}
}
