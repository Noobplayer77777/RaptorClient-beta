package me.ritomg.raptor.client.module.mods.render;

import me.ritomg.raptor.client.events.RenderWeatherEvent;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoWeather extends Module {
    public NoWeather() {
        super("NoWeather", Category.Render);
    }

    @SubscribeEvent
    public void onWeather(RenderWeatherEvent event) {
        event.setCanceled(true);
    }
}
