package net.onhack.mixin;

import net.minecraft.entity.data.TrackedData;
import net.minecraft.util.Hand;
import net.onhack.AutoFishing;
import net.onhack.OnHack;
import net.minecraft.entity.projectile.FishingBobberEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingBobberEntity.class)
public class FishBobberEntityMixin {

    @Shadow private boolean caughtFish;

    @Inject(at=@At("TAIL"), method = "onTrackedDataSet")
    public void tick(TrackedData<?> data, CallbackInfo callbackInfo) {
        if (caughtFish && OnHack.enabledArray[0]) {
            AutoFishing.SetRecastRodIn(10);
            OnHack.client.interactionManager.interactItem(OnHack.client.player, Hand.MAIN_HAND);
        }
    }
}
