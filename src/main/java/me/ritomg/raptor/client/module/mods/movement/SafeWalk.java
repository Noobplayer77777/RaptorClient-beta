package me.ritomg.raptor.client.module.mods.movement;

import me.ritomg.raptor.client.events.PlayerMoveEvent;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import me.ritomg.raptor.client.setting.impl.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SafeWalk extends Module {

    public NumberSetting height = new NumberSetting("Height", this, 1, 0, 32, 1);

    public SafeWalk() {
        super("SafeWalk", Category.Movement);
        addSettings(height);
    }
    @SubscribeEvent
    public void onMove(PlayerMoveEvent event) {
        final Minecraft mc = Minecraft.getMinecraft();
        double x = event.getX();
        double y = event.getY();
        double z = event.getZ();


        if (mc.player.onGround && !mc.player.noClip) {
            double increment;
            for (increment = 0.05D; x != 0.0D && isOffsetBBEmpty(x, -this.height.getValue(), 0.0D); ) {
                if (x < increment && x >= -increment) {
                    x = 0.0D;
                } else if (x > 0.0D) {
                    x -= increment;
                } else {
                    x += increment;
                }
            }
            for (; z != 0.0D && isOffsetBBEmpty(0.0D, -this.height.getValue(), z); ) {
                if (z < increment && z >= -increment) {
                    z = 0.0D;
                } else if (z > 0.0D) {
                    z -= increment;
                } else {
                    z += increment;
                }
            }
            for (; x != 0.0D && z != 0.0D && isOffsetBBEmpty(x, -this.height.getValue(), z); ) {
                if (x < increment && x >= -increment) {
                    x = 0.0D;
                } else if (x > 0.0D) {
                    x -= increment;
                } else {
                    x += increment;
                }
                if (z < increment && z >= -increment) {
                    z = 0.0D;
                } else if (z > 0.0D) {
                    z -= increment;
                } else {
                    z += increment;
                }
            }
        }
        event.setX(x);
        event.setY(y);
        event.setZ(z);
    }

    private boolean isOffsetBBEmpty(double x, double y, double z) {
        return Minecraft.getMinecraft().world.getCollisionBoxes(Minecraft.getMinecraft().player, Minecraft.getMinecraft().player.getEntityBoundingBox().offset(x, y, z)).isEmpty();
    }
}
