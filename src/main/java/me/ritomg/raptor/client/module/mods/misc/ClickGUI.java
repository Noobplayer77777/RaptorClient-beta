package me.ritomg.raptor.client.module.mods.misc;

import me.ritomg.raptor.RaptorClient;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import me.ritomg.raptor.client.setting.impl.BooleanSetting;
import me.ritomg.raptor.client.setting.impl.NumberSetting;
import org.lwjgl.input.Keyboard;

public class ClickGUI extends Module {

    public ClickGUI() {
        super("ClickGUI", Category.Client);
        keyBind.setKeyCode(Keyboard.KEY_RCONTROL);
        redValue = new NumberSetting("R", this, 0, 0, 255, 1);
        greenValue = new NumberSetting("G", this, 255, 0, 255, 1);
        blueValue = new NumberSetting("B", this, 0, 0, 255, 1);
        Scrollspeed = new NumberSetting("ScrollSpeed",this,2,1,10,2);
        addSettings(redValue, blueValue, greenValue,Scrollspeed);
    }

    public static NumberSetting redValue, blueValue, greenValue, rainbowSpeed,Scrollspeed;
    public static BooleanSetting rainbowSetting;

    public void onEnable() {
        mc.displayGuiScreen(RaptorClient.clickGui);
        RaptorClient.modManager.getMod("ClickGUIOld").disable();
    }
}
