package net.onhack;

import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public class Flying {
    int toggle = 0;
    int MAX_SPEED = 3;
    double FALL_SPEED = -0.04;
    double acceleration = 0.1;

    public void tick(MinecraftClient client) {
        if (client.player!=null && OnHack.flyingMode != 0) {
            boolean jumpPressed = client.options.jumpKey.isPressed();
            boolean crouchPressed = client.options.sneakKey.isPressed();
            boolean forwardPressed = client.options.forwardKey.isPressed();
            boolean leftPressed = client.options.leftKey.isPressed();
            boolean rightPressed = client.options.rightKey.isPressed();
            boolean backPressed = client.options.backKey.isPressed();

            Entity object = client.player;
            if (client.player.hasVehicle()) {
                object = client.player.getVehicle();
            }

            Vec3d velocity = object.getVelocity();
            Vec3d newVelocity = new Vec3d(velocity.x, 0, velocity.z);
            if (OnHack.flyingMode == 1) {
            if (leftPressed && !client.player.hasVehicle()) {
                newVelocity = client.player.getRotationVector().multiply(acceleration).rotateY(3.1415927F/2);
                newVelocity = new Vec3d(newVelocity.x,0, newVelocity.z);
            }
            if (rightPressed && !client.player.hasVehicle()) {
                newVelocity = client.player.getRotationVector().multiply(acceleration).rotateY(-3.1415927F/2);
                newVelocity = new Vec3d(newVelocity.x,0, newVelocity.z);
            }
            if (forwardPressed) {
                newVelocity = client.player.getRotationVector().multiply(acceleration);
            }
            if (backPressed) {
                newVelocity = client.player.getRotationVector().negate().multiply(acceleration);
            }
            if (jumpPressed) {
//                newVelocity = client.player.getRotationVector().multiply(acceleration).rotateX(3.1415927F/2);
                newVelocity = new Vec3d(newVelocity.x, newVelocity.y + acceleration, newVelocity.z);
            }
            if (crouchPressed) {
//                newVelocity = client.player.getRotationVector().multiply(acceleration).rotateX(-3.1415927F/2);
                newVelocity = new Vec3d(newVelocity.x, newVelocity.y - acceleration, newVelocity.z);
            }
            }
            else if (OnHack.flyingMode == 2) {
                Vec3d rotationVector = new Vec3d(client.player.getRotationVector().x,0,client.player.getRotationVector().z).normalize();
                if (jumpPressed) {
                    newVelocity = new Vec3d(newVelocity.x, newVelocity.y + acceleration, newVelocity.z);
                }
                if (crouchPressed) {
                    newVelocity = new Vec3d(newVelocity.x, newVelocity.y - acceleration, newVelocity.z);
                }
                if (forwardPressed) {
                    Vec3d localVelocity = rotationVector.multiply(acceleration);
                    newVelocity = new Vec3d(newVelocity.x + localVelocity.x, newVelocity.y, newVelocity.z+ localVelocity.z);
                }
                if (backPressed) {
                    Vec3d localVelocity = rotationVector.negate().multiply(acceleration);
                    newVelocity = new Vec3d(newVelocity.x + localVelocity.x, newVelocity.y, newVelocity.z+ localVelocity.z);
                }
                if (leftPressed) {
                    Vec3d localVelocity = rotationVector.multiply(acceleration).rotateY(3.1415927F/2);
                    newVelocity = new Vec3d(newVelocity.x + localVelocity.x, newVelocity.y, newVelocity.z+ localVelocity.z);
                }
                if (rightPressed) {
                    Vec3d localVelocity = rotationVector.multiply(acceleration).rotateY(-3.1415927F/2);
                    newVelocity = new Vec3d(newVelocity.x + localVelocity.x, newVelocity.y, newVelocity.z+ localVelocity.z);
                }
            }

            newVelocity = new Vec3d(newVelocity.x, (toggle==0 && newVelocity.y > FALL_SPEED)?FALL_SPEED:newVelocity.y, newVelocity.z);
            object.setVelocity(newVelocity);
            if (forwardPressed || leftPressed || rightPressed || backPressed) {
                if (acceleration < MAX_SPEED)
                    acceleration += 0.1;
                else if (acceleration > 0.2)
                    acceleration -= 0.2;
            }

            if (toggle == 0 || newVelocity.y <= -FALL_SPEED) {
                toggle = 40;
            }
            toggle --;
        }
    }
}
