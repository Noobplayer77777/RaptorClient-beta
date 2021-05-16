package me.ritomg.raptor.client.module.mods.misc;

import me.ritomg.raptor.RaptorClient;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import me.ritomg.raptor.client.setting.impl.NumberSetting;
import org.lwjgl.input.Keyboard;

public class ClickGUIOld extends Module {

    public NumberSetting red = new NumberSetting("R", this, 255, 0, 255, 1);
    public NumberSetting green = new NumberSetting("G", this, 0, 0, 255, 1);
    public NumberSetting blue = new NumberSetting("B", this, 0, 0, 255, 1);

    public ClickGUIOld() {
        super("ClickGUIOld", Category.Client);
        keyBind.setKeyCode(Keyboard.KEY_NONE);
        addSettings(red, green, blue);
    }

    public void onEnable() {
        mc.displayGuiScreen(RaptorClient.gui);
        RaptorClient.modManager.getMod("ClickGUI").disable();
    }
}
