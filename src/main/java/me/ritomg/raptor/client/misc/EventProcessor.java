package me.ritomg.raptor.client.misc;

import me.ritomg.raptor.RaptorClient;
import me.ritomg.raptor.client.events.*;
import me.ritomg.raptor.client.events.KeyPressEvent;
import me.ritomg.raptor.client.events.LocalTickEvent;
import me.ritomg.raptor.client.events.Render2dEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class EventProcessor {

    Minecraft mc = Minecraft.getMinecraft();

    public EventProcessor() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Text event) {
        if (mc.gameSettings.showDebugInfo)
            return;

        GlStateManager.pushMatrix();

        // where all on-screen renders are passed through
        ScaledResolution sr = new ScaledResolution(mc);
        MinecraftForge.EVENT_BUS.post(new Render2dEvent(sr));

        GlStateManager.popMatrix();
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (mc.world == null || mc.player == null)
            return;

        MinecraftForge.EVENT_BUS.post(new LocalTickEvent());
    }


    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            RaptorClient.modManager.getMods().forEach(module -> {
                if (Keyboard.getEventKey() == module.keyBind.getKeyCode())
                    module.toggle();
            });

            MinecraftForge.EVENT_BUS.post(new KeyPressEvent(Keyboard.getEventKey()));
        }
    }
}