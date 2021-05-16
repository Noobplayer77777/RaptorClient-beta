package me.ritomg.raptor.client.module.mods.chat;

import me.ritomg.raptor.RaptorClient;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import me.ritomg.raptor.client.setting.impl.ModeSetting;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatSuffix extends Module {
	public ModeSetting modeSetting = new ModeSetting("Separator", this, "|", "<<", "|", ">>", " ");
	
    public ChatSuffix() {
        super("ChatSuffix", Category.Misc);
        addSettings(modeSetting);
    }

    String suffix = " " + modeSetting.getMode() + "RaptorClient";
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onChat(ClientChatEvent event) {
        if (event.getMessage().startsWith("/") || event.getMessage().startsWith(String.valueOf(RaptorClient.commandManager.prefix)))
            return;

        event.setCanceled(true);

        // done to make sure the suffix is not added onto your message
        // history when you press the up arrow in the chat gui
        mc.getConnection().sendPacket(new CPacketChatMessage(event.getOriginalMessage().concat(suffix)));
        mc.ingameGUI.getChatGUI().addToSentMessages(event.getOriginalMessage());
    }
}