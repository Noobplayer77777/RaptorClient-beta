package me.ritomg.raptor.client.ui.rewrite.impl;

import me.ritomg.raptor.client.misc.util.FontUtil;
import me.ritomg.raptor.client.module.Module;
import me.ritomg.raptor.client.setting.Setting;
import me.ritomg.raptor.client.setting.impl.BooleanSetting;
import me.ritomg.raptor.client.setting.impl.ModeSetting;
import me.ritomg.raptor.client.setting.impl.NumberSetting;
import me.ritomg.raptor.client.ui.rewrite.api.button.AbstractButton;
import me.ritomg.raptor.client.ui.rewrite.impl.setting.BooleanComponent;
import me.ritomg.raptor.client.ui.rewrite.impl.setting.KeybindComponent;
import me.ritomg.raptor.client.ui.rewrite.impl.setting.ModeComponent;
import me.ritomg.raptor.client.ui.rewrite.impl.setting.NumberComponent;
import net.minecraft.client.gui.Gui;

import java.util.ArrayList;

public class ModuleButton extends AbstractButton {

    // this code is kinda trash tbh
    public Module module;
    public ArrayList<BaseComponent> components;
    public boolean open;
    public ModuleButton(int x, int y, int width, int height, Module parent) {
        super(x, y, width, height);
        this.module = parent;
        components = new ArrayList<>();

        for (Setting s : parent.settings) {
            if (s instanceof ModeSetting) {
                components.add(new ModeComponent((ModeSetting)s, x, y, width - 5, height - 3));
            }

            if (s instanceof BooleanSetting) {
                components.add(new BooleanComponent((BooleanSetting)s, x, y, width - 5, height - 3));
            }

            if (s instanceof NumberSetting) {
                components.add(new NumberComponent((NumberSetting) s, x, y, width - 5, height - 3));
            }
        }

        components.add(new KeybindComponent(module.keyBind, x, y, width - 5, height - 3));
    }

    int yOff = 0;
    @Override
    public void draw(int mouseX, int mouseY) {
        if (module.isEnabled())
            Gui.drawRect(x + 1, y + 1, x + width, y + height, ClickGuiScreen.guiColor.getRGB());
        FontUtil.drawStringWithShadow(module.getName(), x + 4, y + 4, -1);

        if (open) {
            yOff = height + 1;
            components.forEach(comp -> {
                comp.x = x + 4;
                comp.y = y + yOff;
                Gui.drawRect(x + 1, comp.y, x + 2, comp.y + comp.height + 1, ClickGuiScreen.guiColor.getRGB());
                comp.draw(mouseX, mouseY);
                yOff += comp.height + 1;
            });
            yOff = 0;
        }
    }

    @Override
    public void handleClick(int mouseButton) {
        if (mouseButton == 0) {
            module.toggle();
        }

        if (mouseButton == 1) {
            open = !open;
        }
    }

    public int getSettingsHeight() {
        if (!open)
            return 0;


        int _height = 0;
        for (BaseComponent comp : components) {
            _height += comp.height + 1;
        }

        return _height;
    }
}
