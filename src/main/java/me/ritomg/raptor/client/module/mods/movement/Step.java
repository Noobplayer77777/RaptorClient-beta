package me.ritomg.raptor.client.module.mods.movement;

import me.ritomg.raptor.client.events.LocalTickEvent;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import me.ritomg.raptor.client.setting.impl.BooleanSetting;
import me.ritomg.raptor.client.setting.impl.NumberSetting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Step extends Module {

    public BooleanSetting reverse = new BooleanSetting("Reverse", this, false);
    public NumberSetting height = new NumberSetting("Height", this, 1.0, 0.5, 10.0, 0.5);

    public Step() {
        super("Step", Category.Movement);
        addSettings(reverse, height);
    }

    @SubscribeEvent
    public void onTick(LocalTickEvent event) {
        mc.player.stepHeight = (float) height.getValue();

        if (reverse.enabled && mc.player.onGround && !mc.player.inWater && !mc.player.isInLava()) {
            mc.player.motionY -= 1;
        }
    }

    public void onDisable() {
        mc.player.stepHeight = 0.5f;
    }
}
