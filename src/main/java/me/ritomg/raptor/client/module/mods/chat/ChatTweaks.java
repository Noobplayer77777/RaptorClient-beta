package me.ritomg.raptor.client.module.mods.chat;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import me.ritomg.raptor.client.setting.impl.BooleanSetting;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.text.SimpleDateFormat;

public class ChatTweaks extends Module {

    public BooleanSetting customFont = new BooleanSetting("CustomFont", this, true);
    public BooleanSetting clearBox = new BooleanSetting("ClearBox", this, true);
    public BooleanSetting timeStamps = new BooleanSetting("Timestamps", this, true);

    public ChatTweaks() {
        super("ChatTweaks", Category.Misc);
        addSettings(customFont, clearBox, timeStamps);
    }


    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String time = formatter.format(System.currentTimeMillis());

        TextComponentString timeStamp = new TextComponentString(ChatFormatting.DARK_GRAY + "<" + time + "> " + ChatFormatting.RESET);

        if (timeStamps.enabled) {
            event.setMessage(timeStamp.appendSibling(event.getMessage()));
        }
    }

}
