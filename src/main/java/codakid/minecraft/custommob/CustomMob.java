package codakid.minecraft.custommob;

import java.util.Random;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class CustomMob extends EntityMob {
	
	public float mobSpeed;
	public float attackDamage;
	public float health;
	public float followRange;
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		
		//Set Mob Attributes
		this.health = 15F;
		this.attackDamage = 10F;
		this.mobSpeed = 60F;
		this.followRange = 24F;
		
		
		//Apply Attributes
		Helper.applyAI(this);
	}
	
	@Override
	protected void dropFewItems(boolean flag, int x)
	{
		
		//Add Items to Drop
		this.dropItem(Items.carrot, 3);
		this.dropItem(Items.coal, 1);
		this.dropItem(Items.snowball, 8);
		
	}
	
	public CustomMob(World world)
	{

		super(world);
		
		Helper.addAI(this);
		
	}//End of Constructor

}//End of Class