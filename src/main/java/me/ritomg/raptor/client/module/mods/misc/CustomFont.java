package me.ritomg.raptor.client.module.mods.misc;

import me.ritomg.raptor.client.misc.util.FontUtil;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;

public class CustomFont extends Module {
    public CustomFont() {
        super("CustomFont", Category.Client);
    }

    public void onEnable() {
        FontUtil.doCustomFont = true;
    }

    public void onDisable() {
        FontUtil.doCustomFont = false;
    }
}
