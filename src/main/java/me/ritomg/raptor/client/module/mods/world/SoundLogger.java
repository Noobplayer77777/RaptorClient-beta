package me.ritomg.raptor.client.module.mods.world;

import me.ritomg.raptor.client.events.PacketEvent;
import me.ritomg.raptor.client.misc.util.Utils;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

// currently broken,
// does not show actual sound position if it is out of range
public class SoundLogger extends Module {

    public SoundLogger() {
        super("ThunderHack", Category.Misc);
    }

    @SubscribeEvent
    public void onPacket(PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketSoundEffect) {
            SPacketSoundEffect packet = (SPacketSoundEffect) event.getPacket();
            if (packet.getSound() == SoundEvents.ENTITY_LIGHTNING_THUNDER)
                print(packet);
        }
    }

    private void print(SPacketSoundEffect packet) {
        Utils.printMSG("Lightning strike detected at XYZ: " + (int) packet.getX() + ", " + (int) packet.getY() + ", " + (int) packet.getZ());
    }
}