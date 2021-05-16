package me.ritomg.raptor.client.command.commands;

import me.ritomg.raptor.client.command.CommandBase;
import net.minecraft.network.play.client.CPacketChatMessage;

public class SayCMD extends CommandBase {
    public SayCMD() {
        super("say", "say <message>", "say something in chat", "s");
    }

    @Override
    public void onCommand(String[] args, String message) {
        if (args.length == 0) {
            sendSyntaxError();
            return;
        }

        mc.getConnection().sendPacket(new CPacketChatMessage(String.join(" ", args)));
    }
}
