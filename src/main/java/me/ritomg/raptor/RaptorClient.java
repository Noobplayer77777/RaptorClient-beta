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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = RaptorClient.MODID, name = RaptorClient.NAME, version = RaptorClient.VERSION)
public class RaptorClient {
    public static final String MODID = "raptor";
    public static final String NAME = "RaptorClient";
    public static final String VERSION = "0.4";

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        new ClientBootstrapper();
    }

    public static CFontRenderer cfont;
    public static ModuleManager modManager;
    public static CommandManager commandManager;
    public static GuiHackManager gui;
    public static HudEditorScreen hudEditor;
    public static CapeUtil capeUtil;
    public static EventProcessor eventProcessor;
    public static ConfigSystem configSystem;
    public static HUD hud;
    public static ClickGuiScreen clickGui;

    public static void log(String info) {
        logger.info(info);
    }
}