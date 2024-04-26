package com.lunar.tutorial.custom.ModScreens;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.awt.*;
import java.net.URL;

public class TutorialScreen extends Screen {
    public TutorialScreen(Text title, Screen parent) {
        super(Text.literal("Tutorial Screen, meheheheh"));
    }

    public ButtonWidget button1;
    public ButtonWidget button2;

    public ButtonWidget escape;

    @Override
    protected void init() {
        button1 = ButtonWidget.builder(Text.literal("Button Eins"),
                    button -> System.out.println("Hey Man! You Clicked Ze Button!"))
                    .dimensions(width/2 - 205, height/2, 200, 20)
                    .tooltip(Tooltip.of(Text.literal("Button Eins, nicht zwei!")))
                    .build();

        button2 = ButtonWidget.builder(Text.literal("Button 2"),
                        button -> System.out.println("dick"))
                    .dimensions(width / 2 + 5, height/2, 200, 20)
                    .tooltip(Tooltip.of(Text.literal("Button Zwei, nicht eins!")))
                    .build();

        escape = ButtonWidget.builder(Text.literal("Escape"),
                button -> client.setScreen(new TitleScreen(false)))
                .dimensions(width/2 - 100, height/2 + 50, 200, 20)
                .tooltip(Tooltip.of(Text.literal("Escape!")))
                .build();

        addDrawableChild(button1);
        addDrawableChild(button2);
        addDrawableChild(escape);
    }
}
