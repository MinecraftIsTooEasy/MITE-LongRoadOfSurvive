package net.oilcake.mitelros.entity.model;

import net.minecraft.Entity;
import net.minecraft.MathHelper;
import net.minecraft.ModelBase;
import net.minecraft.ModelBox;
import net.minecraft.ModelRenderer;

public class ModelAgarician extends ModelBase {
   private final ModelRenderer agarician;
   private final ModelRenderer body;
   private final ModelRenderer head;
   private final ModelRenderer tentacleA4;
   private final ModelRenderer tentacleA3;
   private final ModelRenderer tentacleA2;
   private final ModelRenderer tentacleA1;
   private final ModelRenderer hat;
   private final ModelRenderer cube_r1;
   private final ModelRenderer cube_r2;
   private final ModelRenderer tentacle1;
   private final ModelRenderer tentacle2;
   private final ModelRenderer tentacle3;
   private final ModelRenderer tentacle4;
   private final ModelRenderer tail;
   private final ModelRenderer tentacleB4;
   private final ModelRenderer tentacleB3;
   private final ModelRenderer tentacleB2;
   private final ModelRenderer tentacleB1;

   public ModelAgarician() {
      this.textureWidth = 64;
      this.textureHeight = 64;
      this.agarician = new ModelRenderer(this);
      this.agarician.setRotationPoint(0.0F, 18.0F, 0.0F);
      this.body = new ModelRenderer(this);
      this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.agarician.addChild(this.body);
      this.body.cubeList.add(new ModelBox(this.body, 26, 25, -2.0F, -8.0F, -2.0F, 4, 2, 4, 0.0F));
      this.body.cubeList.add(new ModelBox(this.body, 0, 11, -3.0F, -6.0F, -3.0F, 6, 4, 6, 0.0F));
      this.head = new ModelRenderer(this);
      this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.body.addChild(this.head);
      this.head.cubeList.add(new ModelBox(this.head, 18, 15, -3.0F, -12.0F, -3.0F, 6, 4, 6, 0.0F));
      this.tentacleA4 = new ModelRenderer(this);
      this.tentacleA4.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.body.addChild(this.tentacleA4);
      this.setRotationAngle(this.tentacleA4, 0.0F, -1.5708F, 0.0F);
      this.tentacleA4.cubeList.add(new ModelBox(this.tentacleA4, 0, 0, -2.0F, -4.0F, -4.0F, 4, 4, 1, 0.0F));
      this.tentacleA4.cubeList.add(new ModelBox(this.tentacleA4, 0, 21, -1.0F, 0.0F, -4.0F, 2, 2, 1, 0.0F));
      this.tentacleA3 = new ModelRenderer(this);
      this.tentacleA3.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.body.addChild(this.tentacleA3);
      this.setRotationAngle(this.tentacleA3, 0.0F, 3.1416F, 0.0F);
      this.tentacleA3.cubeList.add(new ModelBox(this.tentacleA3, 0, 0, -2.0F, -4.0F, -4.0F, 4, 4, 1, 0.0F));
      this.tentacleA3.cubeList.add(new ModelBox(this.tentacleA3, 0, 21, -1.0F, 0.0F, -4.0F, 2, 2, 1, 0.0F));
      this.tentacleA2 = new ModelRenderer(this);
      this.tentacleA2.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.body.addChild(this.tentacleA2);
      this.setRotationAngle(this.tentacleA2, 0.0F, 1.5708F, 0.0F);
      this.tentacleA2.cubeList.add(new ModelBox(this.tentacleA2, 0, 0, -2.0F, -4.0F, -4.0F, 4, 4, 1, 0.0F));
      this.tentacleA2.cubeList.add(new ModelBox(this.tentacleA2, 0, 21, -1.0F, 0.0F, -4.0F, 2, 2, 1, 0.0F));
      this.tentacleA1 = new ModelRenderer(this);
      this.tentacleA1.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.body.addChild(this.tentacleA1);
      this.tentacleA1.cubeList.add(new ModelBox(this.tentacleA1, 0, 0, -2.0F, -4.0F, -4.0F, 4, 4, 1, 0.0F));
      this.tentacleA1.cubeList.add(new ModelBox(this.tentacleA1, 0, 21, -1.0F, 0.0F, -4.0F, 2, 2, 1, 0.0F));
      this.hat = new ModelRenderer(this);
      this.hat.setRotationPoint(0.0F, -3.0F, 0.0F);
      this.agarician.addChild(this.hat);
      this.hat.cubeList.add(new ModelBox(this.hat, 0, 0, -5.0F, -10.0F, -5.0F, 10, 1, 10, 0.0F));
      this.hat.cubeList.add(new ModelBox(this.hat, 0, 21, -3.0F, -11.0F, -3.0F, 6, 1, 6, 0.0F));
      this.hat.cubeList.add(new ModelBox(this.hat, 0, 28, 3.0F, -11.0F, -3.0F, 1, 1, 6, 0.0F));
      this.hat.cubeList.add(new ModelBox(this.hat, 0, 28, -4.0F, -11.0F, -3.0F, 1, 1, 6, 0.0F));
      this.hat.cubeList.add(new ModelBox(this.hat, 16, 25, -6.0F, -10.0F, -4.0F, 1, 1, 8, 0.0F));
      this.hat.cubeList.add(new ModelBox(this.hat, 16, 25, 5.0F, -10.0F, -4.0F, 1, 1, 8, 0.0F));
      this.cube_r1 = new ModelRenderer(this);
      this.cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.hat.addChild(this.cube_r1);
      this.setRotationAngle(this.cube_r1, 0.0F, 1.5708F, 0.0F);
      this.cube_r1.cubeList.add(new ModelBox(this.cube_r1, 16, 25, 5.0F, -10.0F, -4.0F, 1, 1, 8, 0.0F));
      this.cube_r2 = new ModelRenderer(this);
      this.cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.hat.addChild(this.cube_r2);
      this.setRotationAngle(this.cube_r2, 0.0F, -1.5708F, 0.0F);
      this.cube_r2.cubeList.add(new ModelBox(this.cube_r2, 16, 25, 5.0F, -10.0F, -4.0F, 1, 1, 8, 0.0F));
      this.cube_r2.cubeList.add(new ModelBox(this.cube_r2, 0, 28, 3.0F, -11.0F, -3.0F, 1, 1, 6, 0.0F));
      this.cube_r2.cubeList.add(new ModelBox(this.cube_r2, 0, 28, -4.0F, -11.0F, -3.0F, 1, 1, 6, 0.0F));
      this.tentacle1 = new ModelRenderer(this);
      this.tentacle1.setRotationPoint(-4.6667F, -8.0F, 4.6667F);
      this.hat.addChild(this.tentacle1);
      this.tentacle1.cubeList.add(new ModelBox(this.tentacle1, 18, 11, -1.3333F, -1.0F, -1.6667F, 3, 1, 3, 0.0F));
      this.tentacle1.cubeList.add(new ModelBox(this.tentacle1, 0, 5, -1.3333F, 0.0F, -0.6667F, 2, 2, 2, 0.0F));
      this.tentacle1.cubeList.add(new ModelBox(this.tentacle1, 0, 11, -0.3333F, 2.0F, -0.6667F, 1, 4, 1, 0.0F));
      this.tentacle2 = new ModelRenderer(this);
      this.tentacle2.setRotationPoint(4.6667F, -8.0F, -4.6667F);
      this.hat.addChild(this.tentacle2);
      this.tentacle2.cubeList.add(new ModelBox(this.tentacle2, 18, 11, -1.6667F, -1.0F, -1.3333F, 3, 1, 3, 0.0F));
      this.tentacle2.cubeList.add(new ModelBox(this.tentacle2, 0, 5, -0.6667F, 0.0F, -1.3333F, 2, 2, 2, 0.0F));
      this.tentacle2.cubeList.add(new ModelBox(this.tentacle2, 0, 11, -0.6667F, 2.0F, -0.3333F, 1, 4, 1, 0.0F));
      this.tentacle3 = new ModelRenderer(this);
      this.tentacle3.setRotationPoint(4.6667F, -8.0F, 4.6667F);
      this.hat.addChild(this.tentacle3);
      this.tentacle3.cubeList.add(new ModelBox(this.tentacle3, 18, 11, -1.6667F, -1.0F, -1.6667F, 3, 1, 3, 0.0F));
      this.tentacle3.cubeList.add(new ModelBox(this.tentacle3, 0, 5, -0.6667F, 0.0F, -0.6667F, 2, 2, 2, 0.0F));
      this.tentacle3.cubeList.add(new ModelBox(this.tentacle3, 0, 11, -0.6667F, 2.0F, -0.6667F, 1, 4, 1, 0.0F));
      this.tentacle4 = new ModelRenderer(this);
      this.tentacle4.setRotationPoint(-4.6667F, -8.0F, -4.6667F);
      this.hat.addChild(this.tentacle4);
      this.tentacle4.cubeList.add(new ModelBox(this.tentacle4, 18, 11, -1.3333F, -1.0F, -1.3333F, 3, 1, 3, 0.0F));
      this.tentacle4.cubeList.add(new ModelBox(this.tentacle4, 0, 5, -1.3333F, 0.0F, -1.3333F, 2, 2, 2, 0.0F));
      this.tentacle4.cubeList.add(new ModelBox(this.tentacle4, 0, 11, -0.3333F, 2.0F, -0.3333F, 1, 4, 1, 0.0F));
      this.tail = new ModelRenderer(this);
      this.tail.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.agarician.addChild(this.tail);
      this.tentacleB4 = new ModelRenderer(this);
      this.tentacleB4.setRotationPoint(1.75F, -2.0F, 1.75F);
      this.tail.addChild(this.tentacleB4);
      this.tentacleB4.cubeList.add(new ModelBox(this.tentacleB4, 8, 28, -1.25F, 0.0F, -0.75F, 2, 2, 2, 0.0F));
      this.tentacleB4.cubeList.add(new ModelBox(this.tentacleB4, 26, 25, -1.25F, 2.0F, -0.75F, 1, 3, 1, 0.0F));
      this.tentacleB3 = new ModelRenderer(this);
      this.tentacleB3.setRotationPoint(1.75F, -2.0F, -1.75F);
      this.tail.addChild(this.tentacleB3);
      this.tentacleB3.cubeList.add(new ModelBox(this.tentacleB3, 8, 28, -1.25F, 0.0F, -0.75F, 2, 2, 2, 0.0F));
      this.tentacleB3.cubeList.add(new ModelBox(this.tentacleB3, 26, 25, -1.25F, 2.0F, 0.25F, 1, 3, 1, 0.0F));
      this.tentacleB2 = new ModelRenderer(this);
      this.tentacleB2.setRotationPoint(-1.75F, -2.0F, -1.75F);
      this.tail.addChild(this.tentacleB2);
      this.tentacleB2.cubeList.add(new ModelBox(this.tentacleB2, 8, 28, -1.25F, 0.0F, -0.75F, 2, 2, 2, 0.0F));
      this.tentacleB2.cubeList.add(new ModelBox(this.tentacleB2, 26, 25, -0.25F, 2.0F, 0.25F, 1, 3, 1, 0.0F));
      this.tentacleB1 = new ModelRenderer(this);
      this.tentacleB1.setRotationPoint(-1.75F, -2.0F, 1.75F);
      this.tail.addChild(this.tentacleB1);
      this.tentacleB1.cubeList.add(new ModelBox(this.tentacleB1, 8, 28, -1.25F, 0.0F, -0.75F, 2, 2, 2, 0.0F));
      this.tentacleB1.cubeList.add(new ModelBox(this.tentacleB1, 26, 25, -0.25F, 2.0F, -0.75F, 1, 3, 1, 0.0F));
   }

   public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
      this.agarician.render(scale);
   }

   public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
      this.tentacle1.rotateAngleX = 0.125F * MathHelper.sin(ageInTicks * 0.05F) + 0.25F;
      this.tentacle1.rotateAngleZ = 0.125F * MathHelper.sin(ageInTicks * 0.05F) + 0.25F;
      this.tentacle2.rotateAngleX = -0.125F * MathHelper.sin(ageInTicks * 0.05F) - 0.25F;
      this.tentacle2.rotateAngleZ = -0.125F * MathHelper.sin(ageInTicks * 0.05F) - 0.25F;
      this.tentacle3.rotateAngleX = 0.125F * MathHelper.sin(ageInTicks * 0.05F) + 0.25F;
      this.tentacle3.rotateAngleZ = -0.125F * MathHelper.sin(ageInTicks * 0.05F) - 0.25F;
      this.tentacle4.rotateAngleX = -0.125F * MathHelper.sin(ageInTicks * 0.05F) - 0.25F;
      this.tentacle4.rotateAngleZ = 0.125F * MathHelper.sin(ageInTicks * 0.05F) + 0.25F;
      this.tentacleB1.rotateAngleX = 0.1F * MathHelper.sin(ageInTicks * 0.05F) + 0.2F;
      this.tentacleB1.rotateAngleZ = 0.1F * MathHelper.sin(ageInTicks * 0.05F) + 0.2F;
      this.tentacleB2.rotateAngleX = -0.1F * MathHelper.sin(ageInTicks * 0.05F) - 0.2F;
      this.tentacleB2.rotateAngleZ = 0.1F * MathHelper.sin(ageInTicks * 0.05F) + 0.2F;
      this.tentacleB3.rotateAngleX = -0.1F * MathHelper.sin(ageInTicks * 0.05F) - 0.2F;
      this.tentacleB3.rotateAngleZ = -0.1F * MathHelper.sin(ageInTicks * 0.05F) - 0.2F;
      this.tentacleB4.rotateAngleX = 0.1F * MathHelper.sin(ageInTicks * 0.05F) + 0.2F;
      this.tentacleB4.rotateAngleZ = -0.1F * MathHelper.sin(ageInTicks * 0.05F) - 0.2F;
   }

   public void setRotationAngle(ModelRenderer bcu, float x, float y, float z) {
      bcu.rotateAngleX = x;
      bcu.rotateAngleY = y;
      bcu.rotateAngleZ = z;
   }
}
