package me.ritomg.raptor.client.module.mods.world;

import me.ritomg.raptor.client.events.PacketEvent;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoRotate extends Module {
    public NoRotate() {
        super("NoRotate", Category.Misc);
    }

    @SubscribeEvent
    public void onPacket(PacketEvent.Receive event) {
        if (mc.player == null)
            return;

        if (event.getPacket() instanceof SPacketPlayerPosLook) {
            ((SPacketPlayerPosLook) event.getPacket()).pitch = mc.player.rotationPitch;
            ((SPacketPlayerPosLook) event.getPacket()).yaw = mc.player.rotationYaw;
        }
    }
}
