package me.ritomg.raptor.client.module.mods.movement;

import me.ritomg.raptor.client.events.LocalTickEvent;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import me.ritomg.raptor.client.setting.impl.ModeSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Sprint extends Module {


    public ModeSetting mode = new ModeSetting("Mode", this, "Rage", "Rage", "Legit");

    public Sprint() {
        super("Sprint", Category.Movement);
        addSettings(mode);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), true);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), false);
    }

}