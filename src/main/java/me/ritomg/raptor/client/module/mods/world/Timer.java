package me.ritomg.raptor.client.module.mods.world;

import me.ritomg.raptor.client.events.LocalTickEvent;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import me.ritomg.raptor.client.setting.impl.NumberSetting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Timer extends Module {

    public NumberSetting timer = new NumberSetting("TickLength", this, 60, 5, 200, 5);

    public Timer() {
        super("Timer", Category.Misc);

        addSettings(timer);
    }

    @SubscribeEvent
    public void onTick(LocalTickEvent event) {
        mc.timer.tickLength = (float) timer.getValue();
    }

    public void onDisable() {
        mc.timer.tickLength = 50f;
    }
}
