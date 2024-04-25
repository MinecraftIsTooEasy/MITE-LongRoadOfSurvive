package net.oilcake.mitelros.entity.model;

import net.minecraft.*;

public class ModelAgarician extends bbo {
    private final bcu agarician;
    private final bcu body;
    private final bcu head;
    private final bcu tentacleA4;
    private final bcu tentacleA3;
    private final bcu tentacleA2;
    private final bcu tentacleA1;
    private final bcu hat;
    private final bcu cube_r1;
    private final bcu cube_r2;
    private final bcu tentacle1;
    private final bcu tentacle2;
    private final bcu tentacle3;
    private final bcu tentacle4;
    private final bcu tail;
    private final bcu tentacleB4;
    private final bcu tentacleB3;
    private final bcu tentacleB2;
    private final bcu tentacleB1;

    public ModelAgarician() {
        t = 64;
        u = 64;

        agarician = new bcu(this);
        agarician.a(0.0F, 18.0F, 0.0F);


        body = new bcu(this);
        body.a(0.0F, 0.0F, 0.0F);
        agarician.a(body);
        body.l.add(new bcp(body, 26, 25, -2.0F, -8.0F, -2.0F, 4, 2, 4, 0.0F));
        body.l.add(new bcp(body, 0, 11, -3.0F, -6.0F, -3.0F, 6, 4, 6, 0.0F));

        head = new bcu(this);
        head.a(0.0F, 0.0F, 0.0F);
        body.a(head);
        head.l.add(new bcp(head, 18, 15, -3.0F, -12.0F, -3.0F, 6, 4, 6, 0.0F));

        tentacleA4 = new bcu(this);
        tentacleA4.a(0.0F, 0.0F, 0.0F);
        body.a(tentacleA4);
        setRotationAngle(tentacleA4, 0.0F, -1.5708F, 0.0F);
        tentacleA4.l.add(new bcp(tentacleA4, 0, 0, -2.0F, -4.0F, -4.0F, 4, 4, 1, 0.0F));
        tentacleA4.l.add(new bcp(tentacleA4, 0, 21, -1.0F, 0.0F, -4.0F, 2, 2, 1, 0.0F));

        tentacleA3 = new bcu(this);
        tentacleA3.a(0.0F, 0.0F, 0.0F);
        body.a(tentacleA3);
        setRotationAngle(tentacleA3, 0.0F, 3.1416F, 0.0F);
        tentacleA3.l.add(new bcp(tentacleA3, 0, 0, -2.0F, -4.0F, -4.0F, 4, 4, 1, 0.0F));
        tentacleA3.l.add(new bcp(tentacleA3, 0, 21, -1.0F, 0.0F, -4.0F, 2, 2, 1, 0.0F));

        tentacleA2 = new bcu(this);
        tentacleA2.a(0.0F, 0.0F, 0.0F);
        body.a(tentacleA2);
        setRotationAngle(tentacleA2, 0.0F, 1.5708F, 0.0F);
        tentacleA2.l.add(new bcp(tentacleA2, 0, 0, -2.0F, -4.0F, -4.0F, 4, 4, 1, 0.0F));
        tentacleA2.l.add(new bcp(tentacleA2, 0, 21, -1.0F, 0.0F, -4.0F, 2, 2, 1, 0.0F));

        tentacleA1 = new bcu(this);
        tentacleA1.a(0.0F, 0.0F, 0.0F);
        body.a(tentacleA1);
        tentacleA1.l.add(new bcp(tentacleA1, 0, 0, -2.0F, -4.0F, -4.0F, 4, 4, 1, 0.0F));
        tentacleA1.l.add(new bcp(tentacleA1, 0, 21, -1.0F, 0.0F, -4.0F, 2, 2, 1, 0.0F));

        hat = new bcu(this);
        hat.a(0.0F, -3.0F, 0.0F);
        agarician.a(hat);
        hat.l.add(new bcp(hat, 0, 0, -5.0F, -10.0F, -5.0F, 10, 1, 10, 0.0F));
        hat.l.add(new bcp(hat, 0, 21, -3.0F, -11.0F, -3.0F, 6, 1, 6, 0.0F));
        hat.l.add(new bcp(hat, 0, 28, 3.0F, -11.0F, -3.0F, 1, 1, 6, 0.0F));
        hat.l.add(new bcp(hat, 0, 28, -4.0F, -11.0F, -3.0F, 1, 1, 6, 0.0F));
        hat.l.add(new bcp(hat, 16, 25, -6.0F, -10.0F, -4.0F, 1, 1, 8, 0.0F));
        hat.l.add(new bcp(hat, 16, 25, 5.0F, -10.0F, -4.0F, 1, 1, 8, 0.0F));

        cube_r1 = new bcu(this);
        cube_r1.a(0.0F, 0.0F, 0.0F);
        hat.a(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 1.5708F, 0.0F);
        cube_r1.l.add(new bcp(cube_r1, 16, 25, 5.0F, -10.0F, -4.0F, 1, 1, 8, 0.0F));

        cube_r2 = new bcu(this);
        cube_r2.a(0.0F, 0.0F, 0.0F);
        hat.a(cube_r2);
        setRotationAngle(cube_r2, 0.0F, -1.5708F, 0.0F);
        cube_r2.l.add(new bcp(cube_r2, 16, 25, 5.0F, -10.0F, -4.0F, 1, 1, 8, 0.0F));
        cube_r2.l.add(new bcp(cube_r2, 0, 28, 3.0F, -11.0F, -3.0F, 1, 1, 6, 0.0F));
        cube_r2.l.add(new bcp(cube_r2, 0, 28, -4.0F, -11.0F, -3.0F, 1, 1, 6, 0.0F));

        tentacle1 = new bcu(this);
        tentacle1.a(-4.6667F, -8.0F, 4.6667F);
        hat.a(tentacle1);
        tentacle1.l.add(new bcp(tentacle1, 18, 11, -1.3333F, -1.0F, -1.6667F, 3, 1, 3, 0.0F));
        tentacle1.l.add(new bcp(tentacle1, 0, 5, -1.3333F, 0.0F, -0.6667F, 2, 2, 2, 0.0F));
        tentacle1.l.add(new bcp(tentacle1, 0, 11, -0.3333F, 2.0F, -0.6667F, 1, 4, 1, 0.0F));

        tentacle2 = new bcu(this);
        tentacle2.a(4.6667F, -8.0F, -4.6667F);
        hat.a(tentacle2);
        tentacle2.l.add(new bcp(tentacle2, 18, 11, -1.6667F, -1.0F, -1.3333F, 3, 1, 3, 0.0F));
        tentacle2.l.add(new bcp(tentacle2, 0, 5, -0.6667F, 0.0F, -1.3333F, 2, 2, 2, 0.0F));
        tentacle2.l.add(new bcp(tentacle2, 0, 11, -0.6667F, 2.0F, -0.3333F, 1, 4, 1, 0.0F));

        tentacle3 = new bcu(this);
        tentacle3.a(4.6667F, -8.0F, 4.6667F);
        hat.a(tentacle3);
        tentacle3.l.add(new bcp(tentacle3, 18, 11, -1.6667F, -1.0F, -1.6667F, 3, 1, 3, 0.0F));
        tentacle3.l.add(new bcp(tentacle3, 0, 5, -0.6667F, 0.0F, -0.6667F, 2, 2, 2, 0.0F));
        tentacle3.l.add(new bcp(tentacle3, 0, 11, -0.6667F, 2.0F, -0.6667F, 1, 4, 1, 0.0F));

        tentacle4 = new bcu(this);
        tentacle4.a(-4.6667F, -8.0F, -4.6667F);
        hat.a(tentacle4);
        tentacle4.l.add(new bcp(tentacle4, 18, 11, -1.3333F, -1.0F, -1.3333F, 3, 1, 3, 0.0F));
        tentacle4.l.add(new bcp(tentacle4, 0, 5, -1.3333F, 0.0F, -1.3333F, 2, 2, 2, 0.0F));
        tentacle4.l.add(new bcp(tentacle4, 0, 11, -0.3333F, 2.0F, -0.3333F, 1, 4, 1, 0.0F));

        tail = new bcu(this);
        tail.a(0.0F, 0.0F, 0.0F);
        agarician.a(tail);


        tentacleB4 = new bcu(this);
        tentacleB4.a(1.75F, -2.0F, 1.75F);
        tail.a(tentacleB4);
        tentacleB4.l.add(new bcp(tentacleB4, 8, 28, -1.25F, 0.0F, -0.75F, 2, 2, 2, 0.0F));
        tentacleB4.l.add(new bcp(tentacleB4, 26, 25, -1.25F, 2.0F, -0.75F, 1, 3, 1, 0.0F));

        tentacleB3 = new bcu(this);
        tentacleB3.a(1.75F, -2.0F, -1.75F);
        tail.a(tentacleB3);
        tentacleB3.l.add(new bcp(tentacleB3, 8, 28, -1.25F, 0.0F, -0.75F, 2, 2, 2, 0.0F));
        tentacleB3.l.add(new bcp(tentacleB3, 26, 25, -1.25F, 2.0F, 0.25F, 1, 3, 1, 0.0F));

        tentacleB2 = new bcu(this);
        tentacleB2.a(-1.75F, -2.0F, -1.75F);
        tail.a(tentacleB2);
        tentacleB2.l.add(new bcp(tentacleB2, 8, 28, -1.25F, 0.0F, -0.75F, 2, 2, 2, 0.0F));
        tentacleB2.l.add(new bcp(tentacleB2, 26, 25, -0.25F, 2.0F, 0.25F, 1, 3, 1, 0.0F));

        tentacleB1 = new bcu(this);
        tentacleB1.a(-1.75F, -2.0F, 1.75F);
        tail.a(tentacleB1);
        tentacleB1.l.add(new bcp(tentacleB1, 8, 28, -1.25F, 0.0F, -0.75F, 2, 2, 2, 0.0F));
        tentacleB1.l.add(new bcp(tentacleB1, 26, 25, -0.25F, 2.0F, -0.75F, 1, 3, 1, 0.0F));
    }

    @Override
    public void a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        agarician.a(scale);
    }
    @Override
    public void a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {

        this.tentacle1.f = 0.125F * MathHelper.sin(ageInTicks * 0.05F ) + 0.25F;
        this.tentacle1.h = 0.125F * MathHelper.sin(ageInTicks * 0.05F ) + 0.25F;

        this.tentacle2.f = - 0.125F * MathHelper.sin(ageInTicks * 0.05F ) - 0.25F;
        this.tentacle2.h = - 0.125F * MathHelper.sin(ageInTicks * 0.05F ) - 0.25F;

        this.tentacle3.f = 0.125F * MathHelper.sin(ageInTicks * 0.05F ) + 0.25F;
        this.tentacle3.h = - 0.125F * MathHelper.sin(ageInTicks * 0.05F ) - 0.25F;

        this.tentacle4.f = - 0.125F * MathHelper.sin(ageInTicks * 0.05F ) - 0.25F;
        this.tentacle4.h = 0.125F * MathHelper.sin(ageInTicks * 0.05F ) + 0.25F;

        this.tentacleB1.f = 0.1F * MathHelper.sin(ageInTicks * 0.05F ) + 0.2F;
        this.tentacleB1.h = 0.1F * MathHelper.sin(ageInTicks * 0.05F ) + 0.2F;

        this.tentacleB2.f = - 0.1F * MathHelper.sin(ageInTicks * 0.05F ) - 0.2F;
        this.tentacleB2.h = 0.1F * MathHelper.sin(ageInTicks * 0.05F ) + 0.2F;

        this.tentacleB3.f = - 0.1F * MathHelper.sin(ageInTicks * 0.05F ) - 0.2F;
        this.tentacleB3.h = - 0.1F * MathHelper.sin(ageInTicks * 0.05F ) - 0.2F;

        this.tentacleB4.f = 0.1F * MathHelper.sin(ageInTicks * 0.05F ) + 0.2F;
        this.tentacleB4.h = - 0.1F * MathHelper.sin(ageInTicks * 0.05F ) - 0.2F;

    }
    public void setRotationAngle(bcu bcu, float x, float y, float z) {
        bcu.f = x;
        bcu.g = y;
        bcu.h = z;
    }
}
