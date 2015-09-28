package teachstevecode.watergun;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityWaterBullet extends EntityThrowable
{
	float explosionRadius = 1.0F;
	
	public EntityWaterBullet(World par1World)
    {
        super(par1World);
    }
	
    public EntityWaterBullet(World par1World, EntityLivingBase par2EntityLivingBase)
    {
        super(par1World, par2EntityLivingBase);
    }
    
    public EntityWaterBullet(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }
    
    @Override
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
    	this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.bow", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
    	IBlockState blockState = Blocks.water.getDefaultState();
    	BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
        this.worldObj.setBlockState(blockpos, blockState);
    	this.setDead();
    }
}
