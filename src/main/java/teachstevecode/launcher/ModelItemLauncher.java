package teachstevecode.launcher;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelItemLauncher extends ModelBase
{
    ModelRenderer Body;
    ModelRenderer Barrel;
    ModelRenderer BackGrip;
    ModelRenderer FrontGrip;
    ModelRenderer Magazine;
    ModelRenderer Scope;
  
  public ModelItemLauncher()
  {    
      Body = new ModelRenderer(this, 0, 0);
      Body.addBox(0F, 0F, 0F, 19, 6, 6);
      Body.setRotationPoint(-3F, 0F, 8F);
      Body.setTextureSize(64, 32);
      Body.mirror = true;
      setRotation(Body, 0F, 1.570796F, 0F);
      Barrel = new ModelRenderer(this, 0, 12);
      Barrel.addBox(0F, 0F, 0F, 9, 4, 4);
      Barrel.setRotationPoint(-2F, 1F, -11F);
      Barrel.setTextureSize(64, 32);
      Barrel.mirror = true;
      setRotation(Barrel, 0F, 1.570796F, 0F);
      BackGrip = new ModelRenderer(this, 50, 0);
      BackGrip.addBox(0F, 0F, 0F, 3, 7, 2);
      BackGrip.setRotationPoint(-1F, 4F, 6F);
      BackGrip.setTextureSize(64, 32);
      BackGrip.mirror = true;
      setRotation(BackGrip, 0F, 1.570796F, 0.5697622F);
      FrontGrip = new ModelRenderer(this, 16, 20);
      FrontGrip.addBox(0F, 0F, 0F, 2, 5, 2);
      FrontGrip.setRotationPoint(-1F, 6F, -8F);
      FrontGrip.setTextureSize(64, 32);
      FrontGrip.mirror = true;
      setRotation(FrontGrip, 0F, 1.570796F, 0F);
      Magazine = new ModelRenderer(this, 26, 12);
      Magazine.addBox(0F, 0F, 0F, 3, 8, 10);
      Magazine.setRotationPoint(-5F, 1F, -1F);
      Magazine.setTextureSize(64, 32);
      Magazine.mirror = true;
      setRotation(Magazine, 0F, 1.570796F, 0F);
      Scope = new ModelRenderer(this, 0, 20);
      Scope.addBox(0F, 0F, 0F, 6, 2, 2);
      Scope.setRotationPoint(-1F, -2F, -3F);
      Scope.setTextureSize(64, 32);
      Scope.mirror = true;
      setRotation(Scope, 0F, 1.570796F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Body.render(f5);
    Barrel.render(f5);
    BackGrip.render(f5);
    FrontGrip.render(f5);
    Magazine.render(f5);
    Scope.render(f5);
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
