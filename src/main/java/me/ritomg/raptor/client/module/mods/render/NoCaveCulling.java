package me.ritomg.raptor.client.module.mods.render;

import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;

public class NoCaveCulling extends Module {
    public NoCaveCulling() {
        super("NoCaveCulling", Category.Render);
    }

    public static boolean stopCaveCulling;

    public void onEnable() {
        stopCaveCulling = true;

        if (mc.world == null)
            return;

        mc.renderGlobal.loadRenderers();
    }

    public void onDisable() {
        stopCaveCulling = false;

        if (mc.world == null)
            return;

        mc.renderGlobal.loadRenderers();
    }
}
