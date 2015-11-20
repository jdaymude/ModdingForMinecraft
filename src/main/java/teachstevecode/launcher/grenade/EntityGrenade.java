package teachstevecode.launcher.grenade;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityGrenade extends EntityThrowable
{
	float explosionRadius = 2.0F;
	byte entityHitDamage = 6;
	
	public EntityGrenade(World par1World)
    {
        super(par1World);
    }
	
    public EntityGrenade(World par1World, EntityLivingBase par2EntityLivingBase)
    {
        super(par1World, par2EntityLivingBase);
    }
    
    public EntityGrenade(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }
    
    @Override
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
    	this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.bow", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
	
    	
        if (par1MovingObjectPosition.entityHit != null)
        {
            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.setExplosionSource(getExplosion()), (float)entityHitDamage);
        } 
        else
        {
        	getExplosion();
        }
    	
    	this.setDead();
    }
    
    public Explosion getExplosion() 
    {
    	return this.worldObj.createExplosion(this, this.posX, this.posY + (double)(this.height / 2.0F), this.posZ, explosionRadius, true); 
    }

}
