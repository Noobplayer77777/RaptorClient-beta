package me.ritomg.raptor.client.module.mods.movement;

import me.ritomg.raptor.client.events.LocalTickEvent;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoWalk extends Module {
    public AutoWalk() {
        super("AutoWalk", Category.Movement);
    }

    @SubscribeEvent
    public void onTick(LocalTickEvent event) {
        mc.gameSettings.keyBindForward.pressed = true;
    }

    public void onDisable() {
        mc.gameSettings.keyBindForward.pressed = false;
    }
}
