package net.onhack.mixin;

import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.onhack.OnHackMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.text.Text;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {
    protected  GameMenuScreenMixin(Text text) { super(text); }

    @Inject(at= @At("HEAD"), method = "initWidgets")
    private void initWidgets(CallbackInfo callbackInfo) {
        this.addDrawableChild(new ButtonWidget(10,10,90,20,Text.of("OnHack Menu"), button -> {
            this.client.setScreen(new OnHackMenu(this, this.client.options));
        }));
    }
}
