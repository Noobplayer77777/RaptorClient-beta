package me.ritomg.raptor.client.module.mods.render;

import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import me.ritomg.raptor.client.setting.impl.NumberSetting;

public class Fullbright extends Module {
	
	
	public NumberSetting gamma = new NumberSetting("Gamma", this, 100, 50, 500, 50 );

    public Fullbright() {
        super("Fullbright", Category.Render);
        addSettings(gamma);
    }

    public void onEnable() {
        mc.gameSettings.gammaSetting = (float) gamma.value;
    }

    public void onDisable() {
        mc.gameSettings.gammaSetting = 1;
    }
}
