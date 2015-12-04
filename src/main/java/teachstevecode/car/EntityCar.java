package teachstevecode.car;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityCar extends Entity
{
    /** true if no player in car */
    private boolean isCarEmpty;
    private double speedMultiplier;
    private int carPosRotationIncrements;
    private double carX;
    private double carY;
    private double carZ;
    private double carYaw;
    private double carPitch;
    @SideOnly(Side.CLIENT)
    private double velocityX;
    @SideOnly(Side.CLIENT)
    private double velocityY;
    @SideOnly(Side.CLIENT)
    private double velocityZ;
    private static final String __OBFID = "CL_00001667";
    private EntityLivingBase rider;
    
    private static Item itemCar;

    public EntityCar(World worldIn)
    {
        super(worldIn);
        this.isCarEmpty = true;
        this.speedMultiplier = 0.07D;
        this.preventEntitySpawning = true;
        this.setSize(1.5F, 0.6F);
        rider = null;
        
        this.itemCar = (Item) Item.itemRegistry.getObject(new ResourceLocation("itemCar"));
    }
    
    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    protected void entityInit()
    {
        this.dataWatcher.addObject(17, new Integer(0));
        this.dataWatcher.addObject(18, new Integer(1));
        this.dataWatcher.addObject(19, new Float(0.0F));
    }

    /**
     * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be
     * pushable on contact, like boats or minecarts.
     */
    public AxisAlignedBB getCollisionBox(Entity entityIn)
    {
        return entityIn.getEntityBoundingBox();
    }

    /**
     * returns the bounding box for this entity
     */
    public AxisAlignedBB getBoundingBox()
    {
        return this.getEntityBoundingBox();
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed()
    {
        return true;
    }

    public EntityCar(World worldIn, double p_i1705_2_, double p_i1705_4_, double p_i1705_6_)
    {
        this(worldIn);
        this.setPosition(p_i1705_2_, p_i1705_4_, p_i1705_6_);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = p_i1705_2_;
        this.prevPosY = p_i1705_4_;
        this.prevPosZ = p_i1705_6_;
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset()
    {
        return (double)this.height * 0.0D - 0.30000001192092896D;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else if (source.getDamageType().equals("explosion") || source.getDamageType().equals("explosion.player")) { 
        	this.setDead();
        	return true; 
        }
        else if (!this.worldObj.isRemote && !this.isDead)
        {
            if (this.riddenByEntity != null && this.riddenByEntity == source.getEntity() && source instanceof EntityDamageSourceIndirect)
            {
                return false;
            }
            else
            {
                this.setForwardDirection(-this.getForwardDirection());
                this.setTimeSinceHit(10);
                this.setDamageTaken(this.getDamageTaken() + amount / 100.0F);
                this.setBeenAttacked();
                boolean flag = source.getEntity() instanceof EntityPlayer && ((EntityPlayer)source.getEntity()).capabilities.isCreativeMode;

                if (flag || this.getDamageTaken() > 1000.0F)
                {
                    if (this.riddenByEntity != null)
                    {
                        this.riddenByEntity.mountEntity(this);
                    }

                    if (!flag)
                    {
                        this.dropItemWithOffset(itemCar, 1, 0.0F);
                    }
                    this.setDead();
                }

                return true;
            }
        }
        else
        {
            return true;
        }
    }

    /**
     * Setups the entity to do the hurt animation. Only used by packets in multiplayer.
     */
    @SideOnly(Side.CLIENT)
    public void performHurtAnimation()
    {
        this.setForwardDirection(-this.getForwardDirection());
        this.setTimeSinceHit(10);
        this.setDamageTaken(this.getDamageTaken() / 100.0F);
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    @SideOnly(Side.CLIENT)
    public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_)
    {
        if (p_180426_10_ && this.riddenByEntity != null)
        {
            this.prevPosX = this.posX = p_180426_1_;
            this.prevPosY = this.posY = p_180426_3_;
            this.prevPosZ = this.posZ = p_180426_5_;
            this.rotationYaw = p_180426_7_;
            this.rotationPitch = p_180426_8_;
            this.carPosRotationIncrements = 0;
            this.setPosition(p_180426_1_, p_180426_3_, p_180426_5_);
            this.motionX = this.velocityX = 0.0D;
            this.motionY = this.velocityY = 0.0D;
            this.motionZ = this.velocityZ = 0.0D;
        }
        else
        {
            if (this.isCarEmpty)
            {
                this.carPosRotationIncrements = p_180426_9_ + 5;
            }
            else
            {
                double d3 = p_180426_1_ - this.posX;
                double d4 = p_180426_3_ - this.posY;
                double d5 = p_180426_5_ - this.posZ;
                double d6 = d3 * d3 + d4 * d4 + d5 * d5;

                if (d6 <= 1.0D)
                {
                    return;
                }

                this.carPosRotationIncrements = 3;
            }

            this.carX = p_180426_1_;
            this.carY = p_180426_3_;
            this.carZ = p_180426_5_;
            this.carYaw = (double)p_180426_7_;
            this.carPitch = (double)p_180426_8_;
            this.motionX = this.velocityX;
            this.motionY = this.velocityY;
            this.motionZ = this.velocityZ;
        }
    }

    /**
     * Sets the velocity to the args. Args: x, y, z
     */
    @SideOnly(Side.CLIENT)
    public void setVelocity(double x, double y, double z)
    {
        this.velocityX = this.motionX = x;
        this.velocityY = this.motionY = y;
        this.velocityZ = this.motionZ = z;
    }
    
    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (this.getTimeSinceHit() > 0)
        {
            this.setTimeSinceHit(this.getTimeSinceHit() - 1);
        }

        if (this.getDamageTaken() > 0.0F)
        {
            this.setDamageTaken(this.getDamageTaken() - 1.0F);
        }

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        
        if (this.rand.nextBoolean())
        {
        this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.prevPosX-1D , this.posY + 0.125D, this.prevPosZ, this.motionX, this.motionY, this.motionZ, new int[0]);

        this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.prevPosX+1D , this.posY + 0.125D, this.prevPosZ, this.motionX, this.motionY, this.motionZ, new int[0]);

        }
        
        byte b0 = 5;
        double d0 = 0.0D;

        double d9 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        double d2;
        double d4;
        int j;
                
        if (d9 > 0.2975D)
        {
            d2 = Math.cos((double)this.rotationYaw * Math.PI / 180.0D);
            d4 = Math.sin((double)this.rotationYaw * Math.PI / 180.0D);

            for (j = 0; (double)j < 1.0D + d9 * 60.0D; ++j)
            {
                double d5 = (double)(this.rand.nextFloat() * 2.0F - 1.0F);
                double d6 = (double)(this.rand.nextInt(2) * 2 - 1) * 0.7D;
                double d7;
                double d8;

                if (this.rand.nextBoolean())
                {
                    d7 = this.posX - d2 * d5 * 0.8D + d4 * d6;
                    d8 = this.posZ - d4 * d5 * 0.8D - d2 * d6;
                    this.worldObj.spawnParticle(EnumParticleTypes.BLOCK_DUST, d7, this.posY - 0.125D, d8, this.motionX, this.motionY, this.motionZ, new int[0]);
                }
                else 
                {
                    d7 = this.posX + d2 + d4 * d5 * 0.7D;
                    d8 = this.posZ + d4 - d2 * d5 * 0.7D;
                    this.worldObj.spawnParticle(EnumParticleTypes.BLOCK_DUST, d7, this.posY - 0.125D, d8, this.motionX, this.motionY, this.motionZ, new int[0]);
                }
            }
        }

        double d10;
        double d11;

        if (this.worldObj.isRemote && this.isCarEmpty)
        {
            if (this.carPosRotationIncrements > 0)
            {
                d2 = this.posX + (this.carX - this.posX) / (double)this.carPosRotationIncrements;
                d4 = this.posY + (this.carY - this.posY) / (double)this.carPosRotationIncrements;
                d10 = this.posZ + (this.carZ - this.posZ) / (double)this.carPosRotationIncrements;
                d11 = MathHelper.wrapAngleTo180_double(this.carYaw - (double)this.rotationYaw);
                this.rotationYaw = (float)((double)this.rotationYaw + d11 / (double)this.carPosRotationIncrements);
                this.rotationPitch = (float)((double)this.rotationPitch + (this.carPitch - (double)this.rotationPitch) / (double)this.carPosRotationIncrements);
                --this.carPosRotationIncrements;
                this.setPosition(d2, d4, d10);
                this.setRotation(this.rotationYaw, this.rotationPitch);
            }
            else
            {
                d2 = this.posX + this.motionX;
                d4 = this.posY + this.motionY;
                d10 = this.posZ + this.motionZ;
                this.setPosition(d2, d4, d10);

                if (this.onGround)
                {
                    this.motionX *= 1.0D;
                    this.motionY *= 1.0D;
                    this.motionZ *= 1.0D;
                }

                this.motionX *= 0.9900000095367432D;
                this.motionY *= 0.949999988079071D;
                this.motionZ *= 0.9900000095367432D;
            }
        }
        else
        {
            if (d0 < 1.0D)
            {
                d2 = d0 * 2.0D - 1.0D;
                this.motionY += 0.03999999910593033D * d2;
            }
            else
            {
                if (this.motionY < 0.0D)
                {
                    this.motionY /= 2.0D;
                }

                this.motionY += 0.007000000216066837D;
            }

            if (this.riddenByEntity instanceof EntityLivingBase)
            {
                EntityLivingBase entitylivingbase = (EntityLivingBase)this.riddenByEntity;
                rider = entitylivingbase;
                float f = this.riddenByEntity.rotationYaw + -entitylivingbase.moveStrafing * 90.0F;
                this.motionX += -Math.sin((double)(f * (float)Math.PI / 180.0F)) * this.speedMultiplier * (double)entitylivingbase.moveForward * 0.05000000074505806D;
                this.motionZ += Math.cos((double)(f * (float)Math.PI / 180.0F)) * this.speedMultiplier * (double)entitylivingbase.moveForward * 0.05000000074505806D;
                //make rider invisible
                rider.setInvisible(true);
            }
            
            //make rider visible
            else if (rider != null)
            {
            	rider.setInvisible(false);
            	rider = null;
            }

            d2 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

            if (d2 > 0.35D)
            {
                d4 = 0.35D / d2;
                this.motionX *= d4;
                this.motionZ *= d4;
                d2 = 0.35D;
            }

            if (d2 > d9 && this.speedMultiplier < 0.35D)
            {
                this.speedMultiplier += (0.35D - this.speedMultiplier) / 35.0D;

                if (this.speedMultiplier > 0.35D)
                {
                    this.speedMultiplier = 0.35D;
                }
            }
            else
            {
                this.speedMultiplier -= (this.speedMultiplier - 0.07D) / 35.0D;

                if (this.speedMultiplier < 0.07D)
                {
                    this.speedMultiplier = 0.07D;
                }
            }

            int l;

            for (l = 0; l < 4; ++l)
            {
                int i1 = MathHelper.floor_double(this.posX + ((double)(l % 2) - 0.5D) * 0.8D);
                j = MathHelper.floor_double(this.posZ + ((double)(l / 2) - 0.5D) * 0.8D);

                for (int j1 = 0; j1 < 2; ++j1)
                {
                    int k = MathHelper.floor_double(this.posY) + j1;
                    BlockPos blockpos = new BlockPos(i1, k, j);
                    Block block = this.worldObj.getBlockState(blockpos).getBlock();

                    if (block == Blocks.snow_layer)
                    {
                        this.worldObj.setBlockToAir(blockpos);
                        this.isCollidedHorizontally = false;
                    }
                    else if (block == Blocks.waterlily)
                    {
                        this.worldObj.destroyBlock(blockpos, true);
                        this.isCollidedHorizontally = false;
                    }
                }
            }

            if (this.onGround)
            {
            	this.motionX *= 1.07D;
                this.motionY *= 1.07D;
                this.motionZ *= 1.07D;
            }
            if(this.riddenByEntity == null || this.worldObj.isRemote == true)
            {
            this.moveEntity(0, 0, 0);
            }
            else{
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            }
            if (this.isCollidedHorizontally)
            {            	
                if (!this.worldObj.isRemote && !this.isDead)
                {   
                	int facingDirection = (int) this.rotationYaw;
                	
                	if (facingDirection < 0) {
                		facingDirection += 360;
                	}
                	                	
                	int changeX = 0;
                	int changeZ = 0;
                	
                	if (facingDirection > 0 && facingDirection < 180) {
                		changeX = 1;
                	}
                	else {
                		changeX = -1;
                	}
                	
                	if (facingDirection > 90 && facingDirection < 270) {
                		changeZ = 1;
                	}
                	else {
                		changeZ = -1;
                	}
                	
                	double newX = this.posX + changeX;
                	double newY = this.posY + 1;
                	double newZ = this.posZ + changeZ;
                	
                	BlockPos blockPos = new BlockPos(newX, newY, newZ);
                    Block block = this.worldObj.getBlockState(blockPos).getBlock();
                    
                    if (block == Blocks.air || block == Blocks.water) {
                    	this.setPosition(newX, newY, newZ);
                    }
                }
            }
            else
            {	
            	this.motionX *= 0.95;
            	this.motionY *= 0.95;
            	this.motionZ *= 0.95;
            }

            this.rotationPitch = 0.0F;
            d4 = (double)this.rotationYaw;
            d10 = this.prevPosX - this.posX;
            d11 = this.prevPosZ - this.posZ;

            if (d10 * d10 + d11 * d11 > 0.001D)
            {
                d4 = (double)((float)(Math.atan2(d11, d10) * 180.0D / Math.PI));
            }

            double d12 = MathHelper.wrapAngleTo180_double(d4 - (double)this.rotationYaw);

            if (d12 > 20.0D)
            {
                d12 = 20.0D;
            }

            if (d12 < -20.0D)
            {
                d12 = -20.0D;
            }

            this.rotationYaw = (float)((double)this.rotationYaw + d12);
            this.setRotation(this.rotationYaw, this.rotationPitch);

            if (!this.worldObj.isRemote)
            {
                List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));

                if (list != null && !list.isEmpty())
                {
                    for (int k1 = 0; k1 < list.size(); ++k1)
                    {
                        Entity entity = (Entity)list.get(k1);

                        if (entity != this.riddenByEntity && entity.canBePushed() && entity instanceof EntityCar)
                        {
                            entity.applyEntityCollision(this);
                        }
                    }
                }

                if (this.riddenByEntity != null && this.riddenByEntity.isDead)
                {
                    this.riddenByEntity = null;
                }
            }
        }
    }

    public void updateRiderPosition()
    {
        if (this.riddenByEntity != null)
        {
            double d0 = Math.cos((double)this.rotationYaw * Math.PI / 180.0D) * 0.4D;
            double d1 = Math.sin((double)this.rotationYaw * Math.PI / 180.0D) * 0.4D;
            this.riddenByEntity.setPosition(this.posX + d0, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + d1);
            this.riddenByEntity.setPosition(this.posX, this.posY+.1, this.posZ+.1);
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound tagCompound) {}

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound tagCompund) {}

    /**
     * First layer of player interaction
     */
    public boolean interactFirst(EntityPlayer playerIn)
    {
        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != playerIn)
        {
            return true;
        }
        else
        {
            if (!this.worldObj.isRemote)
            {
                playerIn.mountEntity(this);
            }

            return true;
        }
    }

    protected void func_180433_a(double p_180433_1_, boolean p_180433_3_, Block p_180433_4_, BlockPos p_180433_5_)
    {
        if (p_180433_3_)
        {
            if (this.fallDistance > 300.0F)
            {
                this.fall(this.fallDistance, 10.0F);

                if (!this.worldObj.isRemote && !this.isDead)
                {
                    //this.setDead();
                    int i;

                    for (i = 0; i < 3; ++i)
                    {
                    	 
                        //this.dropItemWithOffset(Item.getItemFromBlock(Blocks.planks), 1, 0.0F);
                    }

                    for (i = 0; i < 2; ++i)
                    {
                        //this.dropItemWithOffset(Items.stick, 1, 0.0F);
                    }
                }

                this.fallDistance = 0.0F;
            }
        }
        else if (this.worldObj.getBlockState((new BlockPos(this)).down()).getBlock().getMaterial() != Material.water && p_180433_1_ < 0.0D)
        {
            this.fallDistance = (float)((double)this.fallDistance - p_180433_1_);
        }
    }

    /**
     * Sets the damage taken from the last hit.
     */
    public void setDamageTaken(float p_70266_1_)
    {
        this.dataWatcher.updateObject(19, Float.valueOf(p_70266_1_));
    }

    /**
     * Gets the damage taken from the last hit.
     */
    public float getDamageTaken()
    {
        return this.dataWatcher.getWatchableObjectFloat(19);
    }

    /**
     * Sets the time to count down from since the last time entity was hit.
     */
    public void setTimeSinceHit(int p_70265_1_)
    {
        this.dataWatcher.updateObject(17, Integer.valueOf(p_70265_1_));
    }

    /**
     * Gets the time since the last hit.
     */
    public int getTimeSinceHit()
    {
        return this.dataWatcher.getWatchableObjectInt(17);
    }

    /**
     * Sets the forward direction of the entity.
     */
    public void setForwardDirection(int p_70269_1_)
    {
        this.dataWatcher.updateObject(18, Integer.valueOf(p_70269_1_));
    }

    /**
     * Gets the forward direction of the entity.
     */
    public int getForwardDirection()
    {
        return this.dataWatcher.getWatchableObjectInt(18);
    }

    /**
     * true if no player in car
     */
    @SideOnly(Side.CLIENT)
    public void setIsCarEmpty(boolean p_70270_1_)
    {
        this.isCarEmpty = p_70270_1_;
    }
}
