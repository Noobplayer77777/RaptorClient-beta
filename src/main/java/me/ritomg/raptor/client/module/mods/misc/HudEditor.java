package me.ritomg.raptor.client.module.mods.misc;

import me.ritomg.raptor.RaptorClient;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;

public class HudEditor extends Module {
    public HudEditor() {
        super("HudEditor", Category.Client);
    }

    public void onEnable() {
        mc.displayGuiScreen(RaptorClient.hudEditor);
    }
}
