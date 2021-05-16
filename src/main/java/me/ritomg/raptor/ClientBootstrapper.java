package me.ritomg.raptor;

import me.ritomg.raptor.client.command.CommandManager;
import me.ritomg.raptor.client.misc.ConfigSystem;
import me.ritomg.raptor.client.misc.EventProcessor;
import me.ritomg.raptor.client.misc.font.CFontRenderer;
import me.ritomg.raptor.client.misc.util.CapeUtil;
import me.ritomg.raptor.client.module.ModuleManager;
import me.ritomg.raptor.client.ui.gui.GuiHackManager;
import me.ritomg.raptor.client.ui.hud.HUD;
import me.ritomg.raptor.client.ui.hud.HudEditorScreen;
import me.ritomg.raptor.client.ui.rewrite.impl.ClickGuiScreen;

import java.awt.*;

public class ClientBootstrapper {

    public ClientBootstrapper() {
        initializeClient();
    }

    void initializeClient() {
        RaptorClient.eventProcessor = new EventProcessor();
        RaptorClient.log("Event Processor initialized");

        RaptorClient.cfont = new CFontRenderer(new Font("Veranda", Font.PLAIN, 18), true,  true);
        RaptorClient.log("Hal Font Renderer initialized");

        RaptorClient.modManager = new ModuleManager();
        RaptorClient.log("Module Manager initialized");

        RaptorClient.commandManager = new CommandManager();
        RaptorClient.log("Command Manager initialized");

        RaptorClient.capeUtil = new CapeUtil();
        RaptorClient.log("Cape Util initialized");

        RaptorClient.clickGui = new ClickGuiScreen();
        RaptorClient.log("ClickGUI initialized");

        RaptorClient.gui = new GuiHackManager();
        RaptorClient.log("Old ClickGUI initialized");

        RaptorClient.hudEditor = new HudEditorScreen();
        RaptorClient.log("HudEditor initialized");
        
        if(!RaptorClient.modManager.getMod("DiscordRPC").isEnabled()) {
        	RaptorClient.modManager.getMod("DiscordRPC").enable();
        }
        RaptorClient.log("RPC Enabled");
        
        RaptorClient.hud = new HUD();
        RaptorClient.log("HUD initialized");

        RaptorClient.configSystem = new ConfigSystem();
        RaptorClient.configSystem.loadModulesAndSettings();
        RaptorClient.configSystem.loadOtherValues();
        Runtime.getRuntime().addShutdownHook(RaptorClient.configSystem);
        RaptorClient.log("Config System initialized");

        RaptorClient.log("Completed RaptorClient initialization");
    }
}
