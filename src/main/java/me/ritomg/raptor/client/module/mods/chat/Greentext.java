package me.ritomg.raptor.client.module.mods.chat;

import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Greentext extends Module {
    public Greentext() {
        super("Greentext", Category.Misc);
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        event.setMessage(">" + event.getMessage());
    }
}
