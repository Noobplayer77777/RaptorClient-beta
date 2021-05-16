package me.ritomg.raptor.client.module.mods.chat;

import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.StringEscapeUtils;

public class Shrug extends Module {
    public Shrug() {
        super("Shrug", Category.Misc);
    }

    @SubscribeEvent
    public void onChatt(ClientChatEvent event) {
        String shrugText = "¯\\_(ツ)_/¯";
        event.setMessage(event.getMessage() + " " + shrugText);
    }
}
