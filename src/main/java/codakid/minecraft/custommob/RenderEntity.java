package codakid.minecraft.custommob;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntity extends RenderLiving {

	private static final ResourceLocation bipedTexture = new ResourceLocation(
			CustomMobMod.MODID, "mobs/bipedTexture.png");
	private static final ResourceLocation pigTexture = new ResourceLocation(
			CustomMobMod.MODID, "mobs/pigTexture.png");
	private static final ResourceLocation wolfTexture = new ResourceLocation(
			CustomMobMod.MODID, "mobs/wolfTexture.png");

	public RenderEntity(RenderManager renderManager, ModelBase var1, float var2) {
		super(renderManager, var1, var2);
	}

	public void func_177_a(CustomMob entity, double d, double d1, double d2,
			float f, float f1) {
		super.doRender(entity, d, d1, d2, f, f1);
	}

	public void doRenderLiving(EntityLiving entityliving, double d, double d1,
			double d2, float f, float f1) {
		func_177_a((CustomMob) entityliving, d, d1, d2, f, f1);
	}

	public void doRender(Entity entity, double d, double d1, double d2,
			float f, float f1) {
		func_177_a((CustomMob) entity, d, d1, d2, f, f1);
	}

	protected ResourceLocation func_110872_a(CustomMob par1Entity) {
		
		switch(CustomMobMod.mobType)
		{
			case 0:
				return bipedTexture;
			case 1:
				return pigTexture;
			case 2:
				return wolfTexture;
		}
		
		return null;
	}

	protected ResourceLocation getEntityTexture(Entity var1) {
		return func_110872_a((CustomMob) var1);
	}
}