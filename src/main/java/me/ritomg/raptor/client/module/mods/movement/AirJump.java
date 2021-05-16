package me.ritomg.raptor.client.module.mods.movement;

import me.ritomg.raptor.client.events.KeyPressEvent;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AirJump extends Module {
    public AirJump() {
        super("AirJump", Category.Movement);
    }

    @SubscribeEvent
    public void onKey(KeyPressEvent event) {
        if (mc.player == null)
            return;

        if (event.getKey() == mc.gameSettings.keyBindJump.getKeyCode()) {
            mc.player.jump();
        }
    }
}
