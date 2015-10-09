package codakid.minecraft.custommob;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

class Helper {
	
	public static void createMob()
	{
		int ID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(CustomMob.class, "customEntity", ID, CustomMobMod.eggColor, CustomMobMod.eggSpotColor);

		switch(CustomMobMod.mobType)
		{
			case 0:
				RenderingRegistry.registerEntityRenderingHandler(CustomMob.class, new RenderEntity(Minecraft.getMinecraft().getRenderManager(), new MyBipedModel(), 0.5F));
				break;
			case 1:
				RenderingRegistry.registerEntityRenderingHandler(CustomMob.class, new RenderEntity(Minecraft.getMinecraft().getRenderManager(), new MyPigModel(), 0.5F));
				break;
			case 2:
				RenderingRegistry.registerEntityRenderingHandler(CustomMob.class, new RenderEntity(Minecraft.getMinecraft().getRenderManager(), new MyWolfModel(), 0.5F));
				break;
		}
		
		//Register Entity
		EntityRegistry.addSpawn(CustomMob.class, 500, 5, 10, EnumCreatureType.MONSTER,
				BiomeGenBase.beach,
				BiomeGenBase.birchForest,
				BiomeGenBase.birchForestHills,
				BiomeGenBase.coldBeach,
				BiomeGenBase.coldTaiga,
				BiomeGenBase.coldTaigaHills,
				BiomeGenBase.deepOcean,
				BiomeGenBase.desert,
				BiomeGenBase.desertHills,
				BiomeGenBase.extremeHills,
				BiomeGenBase.extremeHillsEdge,
				BiomeGenBase.extremeHillsPlus,
				BiomeGenBase.forest,
				BiomeGenBase.forestHills,
				BiomeGenBase.frozenOcean,
				BiomeGenBase.frozenRiver,
				BiomeGenBase.iceMountains,
				BiomeGenBase.icePlains,
				BiomeGenBase.jungle,
				BiomeGenBase.jungleEdge,
				BiomeGenBase.jungleHills,
				BiomeGenBase.megaTaiga,
				BiomeGenBase.megaTaigaHills,
				BiomeGenBase.mesa,
				BiomeGenBase.mesaPlateau,
				BiomeGenBase.mesaPlateau_F,
				BiomeGenBase.mushroomIsland,
				BiomeGenBase.mushroomIslandShore,
				BiomeGenBase.ocean,
				BiomeGenBase.plains,
				BiomeGenBase.river,
				BiomeGenBase.roofedForest,
				BiomeGenBase.savanna,
				BiomeGenBase.savannaPlateau,
				BiomeGenBase.stoneBeach,
				BiomeGenBase.swampland,
				BiomeGenBase.taiga,
				BiomeGenBase.taigaHills
				);
	}
	
	protected static void addAI(CustomMob mob)
	{
		mob.tasks.addTask(0, new EntityAIWander(mob, mob.mobSpeed));
		mob.tasks.addTask(1, new EntityAISwimming(mob));
		mob.tasks.addTask(2, new EntityAIWatchClosest(mob, EntityPlayer.class, 32));
		mob.tasks.addTask(3, new EntityAIAttackOnCollide(mob, EntityPlayer.class, mob.mobSpeed, false));
		
		mob.targetTasks.addTask(0, new EntityAIHurtByTarget(mob, false, new Class[0]));
		mob.targetTasks.addTask(1, new EntityAINearestAttackableTarget(mob, EntityPlayer.class, true));
	}
	
	protected static void applyAI(CustomMob mob)
	{
		mob.mobSpeed /= 100;
		
		mob.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue((float)mob.attackDamage);
		mob.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue((float)mob.followRange);
		mob.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((float)mob.health);
		mob.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((float)mob.mobSpeed);
	}

}
