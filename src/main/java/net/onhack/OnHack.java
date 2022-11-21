package net.onhack;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OnHack implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("onhack");
	public static MinecraftClient client = MinecraftClient.getInstance();
	//0 - autoFishing
	public static boolean[] enabledArray = new boolean[1];
	public static int flyingMode = 0;

	public Flying flying;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		ClientTickEvents.END_CLIENT_TICK.register(this::tick);
//		enabledArray[0] = true;
//		enabledArray[1] = true;
		flyingMode = 0;
		flying = new Flying();
	}

	public void tick(MinecraftClient client) {
		if (enabledArray[0])
			AutoFishing.tick(client);
		if (flyingMode != 0)
			flying.tick(client);
	}
}
