package me.ritomg.raptor.client.command.commands;

import me.ritomg.raptor.RaptorClient;
import me.ritomg.raptor.client.command.CommandBase;
import me.ritomg.raptor.client.misc.util.Utils;
import me.ritomg.raptor.client.module.Module;
import org.lwjgl.input.Keyboard;

public class BindCMD extends CommandBase {
    public BindCMD() {
        super("bind", "bind <module> <key>", "binds a module to the specified key", "b");
    }

    @Override
    public void onCommand(String[] args, String message) {
        if (args.length == 2) {
            Module targetMod = RaptorClient.modManager.getMod(args[0]);
            String keyName = args[1].toUpperCase();

            if (targetMod != null) {
                targetMod.keyBind.setKeyCode(Keyboard.getKeyIndex(keyName));
                Utils.printMSG("Bound " + targetMod.getName() + " to " + keyName);
                return;
            } else {
                Utils.printMSG("Unable to find the specified module");
                sendSyntaxError();
                return;
            }
        } else {
            sendSyntaxError();
            return;
        }
    }
}