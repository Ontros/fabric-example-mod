package net.onhack;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Hand;

public class AutoFishing {
    public static int recastRodIn = -1;

    public static void tick (MinecraftClient client) {
        if (recastRodIn > 0) {
            recastRodIn--;
        }
        if (recastRodIn ==0 && OnHack.enabledArray[0]) {
            client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);
            recastRodIn = -1;
        }
    }
    public static void SetRecastRodIn(int countdown) {
        recastRodIn = countdown;
    }
}
