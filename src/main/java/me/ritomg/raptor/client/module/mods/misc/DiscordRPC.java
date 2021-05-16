package me.ritomg.raptor.client.module.mods.misc;

import me.ritomg.raptor.client.misc.DiscordService;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;

public class DiscordRPC extends Module {
    public DiscordRPC() {
        super("DiscordRPC", Category.Client);
    }

    public void onEnable() {
        DiscordService.startRPC();
    }

    public void onDisable() {
        DiscordService.stopRPC();
    }
}
