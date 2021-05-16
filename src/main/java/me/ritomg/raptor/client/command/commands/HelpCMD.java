package me.ritomg.raptor.client.command.commands;

import me.ritomg.raptor.RaptorClient;
import me.ritomg.raptor.client.command.CommandBase;
import me.ritomg.raptor.client.misc.util.Utils;

public class HelpCMD extends CommandBase {
    public HelpCMD() {
        super("help", "help", "shows all commands");
    }

    @Override
    public void onCommand(String[] args, String message) {

        StringBuilder sb = new StringBuilder();
        for (CommandBase commandBase : RaptorClient.commandManager.getCommands()) {
            sb.append(commandBase.getName().concat(", "));
        }

        String cmds = sb.substring(0, sb.length() - 2);

        Utils.printMSG("Commands: " + cmds);
    }
}