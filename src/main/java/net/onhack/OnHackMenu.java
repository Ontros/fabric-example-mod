package net.onhack;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class OnHackMenu extends Screen {
    private final Screen parent;
    private final GameOptions settings;

    public OnHackMenu(Screen parent, GameOptions gameOptions) {
        super(Text.of("OnHack Menu"));
        this.parent = parent;
        this.settings = gameOptions;
    }

    Text autoFishingText() {
        if (OnHack.enabledArray[0]) {
            return Text.of("AutoFishing is enabled");
        }
        else {
            return Text.of("AutoFishing is disabled");
        }
    }
    Text flyingText() {
        return Text.of("Flying mode is: "+OnHack.flyingMode);
    }

    protected void init() {
        this.addDrawableChild(new ButtonWidget(this.width/2 -100, this.height /6 +90, 200, 20,autoFishingText(),button -> {
            OnHack.enabledArray[0] = !OnHack.enabledArray[0];
            button.setMessage(autoFishingText());
        }));
        this.addDrawableChild(new ButtonWidget(this.width/2 -100, this.height /6 +90+25, 200, 20,flyingText(),button -> {
            OnHack.flyingMode = (OnHack.flyingMode + 1) % 3;
            button.setMessage(flyingText());
        }));
        this.addDrawableChild(new ButtonWidget(this.width/2 -100, this.height /6 +138, 200, 20, ScreenTexts.BACK, button -> {
            this.client.setScreen(this.parent);
        }));
    }
}
