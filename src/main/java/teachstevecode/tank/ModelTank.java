// Date: 12/1/2015 1:27:04 PM

package teachstevecode.tank;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelTank extends ModelBase
{
  //fields
    ModelRenderer Barrel;
    ModelRenderer Head;
    ModelRenderer Neck;
    ModelRenderer Body1;
    ModelRenderer Body2;
    ModelRenderer Body3;
    ModelRenderer Cap;
    ModelRenderer WheelsRight;
    ModelRenderer WheelsLeft;
    ModelRenderer BackCap;
  
  public ModelTank()
  {
	textureWidth = 128;
    textureHeight = 128;
    
      Barrel = new ModelRenderer(this, 71, 0);
      Barrel.addBox(0F, 0F, 0F, 3, 3, 25);
      Barrel.setRotationPoint(-1F, 0F, 0F);
      Barrel.setTextureSize(128, 128);
      Barrel.mirror = true;

      Head = new ModelRenderer(this, 80, 110);
      Head.addBox(0F, 0F, 0F, 11, 5, 13);
      Head.setRotationPoint(-5F, -1F, -13F);
      Head.setTextureSize(128, 128);
      Head.mirror = true;

      Neck = new ModelRenderer(this, 71, 58);
      Neck.addBox(0F, 0F, 0F, 7, 3, 9);
      Neck.setRotationPoint(-3F, 4F, -11F);
      Neck.setTextureSize(128, 128);
      Neck.mirror = true;

      Body1 = new ModelRenderer(this, 0, 95);
      Body1.addBox(0F, 0F, 0F, 15, 8, 25);
      Body1.setRotationPoint(-7F, 7F, -14F);
      Body1.setTextureSize(128, 128);
      Body1.mirror = true;

      Body2 = new ModelRenderer(this, 71, 29);
      Body2.addBox(0F, 0F, 0F, 15, 6, 9);
      Body2.setRotationPoint(-7F, 7F, 11F);
      Body2.setTextureSize(128, 128);
      Body2.mirror = true;

      Body3 = new ModelRenderer(this, 71, 47);
      Body3.addBox(0F, 0F, 0F, 15, 4, 5);
      Body3.setRotationPoint(-7F, 7F, 20F);
      Body3.setTextureSize(128, 128);
      Body3.mirror = true;

      Cap = new ModelRenderer(this, 80, 96);
      Cap.addBox(0F, 0F, 0F, 9, 1, 11);
      Cap.setRotationPoint(-4F, -2F, -12F);
      Cap.setTextureSize(128, 128);
      Cap.mirror = true;

      WheelsRight = new ModelRenderer(this, 0, 42);
      WheelsRight.addBox(0F, 0F, 0F, 2, 7, 33);
      WheelsRight.setRotationPoint(8F, 9F, -16F);
      WheelsRight.setTextureSize(128, 128);
      WheelsRight.mirror = true;

      WheelsLeft = new ModelRenderer(this, 0, 0);
      WheelsLeft.addBox(0F, 0F, 0F, 2, 7, 33);
      WheelsLeft.setRotationPoint(-9F, 9F, -16F);
      WheelsLeft.setTextureSize(128, 128);
      WheelsLeft.mirror = true;

      BackCap = new ModelRenderer(this, 71, 72);
      BackCap.addBox(0F, 0F, 0F, 13, 6, 1);
      BackCap.setRotationPoint(-6F, 8F, -15F);
      BackCap.setTextureSize(128, 128);
      BackCap.mirror = true;
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Barrel.render(f5);
    Head.render(f5);
    Neck.render(f5);
    Body1.render(f5);
    Body2.render(f5);
    Body3.render(f5);
    Cap.render(f5);
    WheelsRight.render(f5);
    WheelsLeft.render(f5);
    BackCap.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
