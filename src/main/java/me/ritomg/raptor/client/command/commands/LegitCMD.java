package me.ritomg.raptor.client.command.commands;

import me.ritomg.raptor.RaptorClient;
import me.ritomg.raptor.client.command.CommandBase;
import me.ritomg.raptor.client.misc.util.Utils;
import me.ritomg.raptor.client.module.Module;

public class LegitCMD extends CommandBase {
    public LegitCMD() {
        super("legit", "legit", "disables all modules");
    }

    @Override
    public void onCommand(String[] args, String message) {
        RaptorClient.modManager.getMods().forEach(Module::disable);
        Utils.printMSG("Disabled all modules");
    }
}
