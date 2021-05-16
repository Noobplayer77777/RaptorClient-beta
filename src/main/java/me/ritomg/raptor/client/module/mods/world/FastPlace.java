package me.ritomg.raptor.client.module.mods.world;

import me.ritomg.raptor.client.events.LocalTickEvent;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import me.ritomg.raptor.client.setting.impl.BooleanSetting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FastPlace extends Module {

    public BooleanSetting noBreakDelay = new BooleanSetting("NoBreakDelay", this, false);

    public FastPlace() {
        super("FastPlace", Category.Misc);
        addSettings(noBreakDelay);
    }

    @SubscribeEvent
    public void onTick(LocalTickEvent event) {
        mc.rightClickDelayTimer = 0;

        if (noBreakDelay.enabled) {
            mc.playerController.blockHitDelay = 0;
        }
    }
}
