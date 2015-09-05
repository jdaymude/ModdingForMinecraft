package teachstevecode.lavabomb;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityLavaBombPrimed extends Entity {
	/** How long the fuse is */
    public int fuse;
    private EntityLivingBase lavaBombPlacedBy;
    private static final String __OBFID = "CL_00001681";

    public EntityLavaBombPrimed(World worldIn)
    {
        super(worldIn);
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.fuse = 80;
    }

    public EntityLavaBombPrimed(World worldIn, double p_i1730_2_, double p_i1730_4_, double p_i1730_6_, EntityLivingBase p_i1730_8_)
    {
        this(worldIn);
        this.setPosition(p_i1730_2_, p_i1730_4_, p_i1730_6_);
        float f = (float)(Math.random() * Math.PI * 2.0D);
        this.motionX = (double)(-((float)Math.sin((double)f)) * 0.02F);
        this.motionY = 0.20000000298023224D;
        this.motionZ = (double)(-((float)Math.cos((double)f)) * 0.02F);
        this.fuse = 80;
        this.prevPosX = p_i1730_2_;
        this.prevPosY = p_i1730_4_;
        this.prevPosZ = p_i1730_6_;
        this.lavaBombPlacedBy = p_i1730_8_;
    }

    protected void entityInit() {}

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.03999999910593033D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
            this.motionY *= -0.5D;
        }

        if (this.fuse-- <= 0)
        {
            this.setDead();

            if (!this.worldObj.isRemote)
            {
                this.explode();
            }
        }
        else
        {
            this.handleWaterMovement();
            this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
        }
    }

    private void explode()
    {
        //Play exploding sound effect
    	this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
        
    	//Type of block to spawn
        IBlockState blockState = Blocks.lava.getDefaultState();
        
        //Size of sphere
        int radius = 5;
        
        //Keep radius between 0 and 10; set to 5 by default otherwise
        if (radius < 0 || radius > 10)
        {
        	radius = 5;
        }
        
        int center = radius / 2;
        
        //Generates blocks in a sphere shape
        for (int x = center - radius; x < center + radius; x++) 
        {
        	for (int y = center - radius; y < center + radius; y++) 
        	{
        		for (int z = center - radius; z < center + radius; z++) 
        		{
        			int squareDistance = (x - center) * (x - center) + (y - center) * (y - center) + (z - center) * (z - center);
        			
        			if (squareDistance <= radius * radius) 
        			{
        				BlockPos blockpos = new BlockPos((double)((float)this.posX + x), (double)((float)this.posY + y), (double)((float)this.posZ + z));
                        this.worldObj.setBlockState(blockpos, blockState);
        			}
        		}
        	}
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound tagCompound)
    {
        tagCompound.setByte("Fuse", (byte)this.fuse);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound tagCompund)
    {
        this.fuse = tagCompund.getByte("Fuse");
    }

    /**
     * returns null or the entityliving it was placed or ignited by
     */
    public EntityLivingBase gettestPlacedBy()
    {
        return this.lavaBombPlacedBy;
    }

    public float getEyeHeight()
    {
        return 0.0F;
    }
}
