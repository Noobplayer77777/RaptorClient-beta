package me.ritomg.raptor.client.command;

import me.ritomg.raptor.RaptorClient;
import me.ritomg.raptor.client.misc.util.Utils;
import net.minecraft.client.Minecraft;

import java.util.Arrays;
import java.util.List;

public abstract class CommandBase {

    protected Minecraft mc = Minecraft.getMinecraft();

    private String name, syntax, desc;
    private List<String > aliases;

    public CommandBase(String name, String syntax, String description,String... aliases) {
        this.name = name;
        this.syntax = syntax;
        this.desc = description;
        this.aliases = Arrays.asList(aliases);
    }

    public abstract void onCommand(String[] args, String message);

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getSyntax() {
        return syntax;
    }

    public void sendSyntaxError() {
        Utils.printMSG("Syntax: " +  RaptorClient.commandManager.prefix + getSyntax());
    }
}