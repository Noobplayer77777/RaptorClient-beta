package me.ritomg.raptor.client.module.mods.combat;

import me.ritomg.raptor.client.events.LocalTickEvent;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import me.ritomg.raptor.client.setting.impl.BooleanSetting;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FastUtil extends Module {

    public BooleanSetting crystals = new BooleanSetting("Crystals", this,true);
    public BooleanSetting xp = new BooleanSetting("XP", this,true);

    public FastUtil() {
        super("FastUtil", Category.Combat);
        addSettings(xp, crystals);
    }

    @SubscribeEvent
    public void onTick(LocalTickEvent event) {
        if (xp.enabled) {
            if (mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE || mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE) {
                mc.rightClickDelayTimer = 0;
            }
        }

        if (crystals.enabled) {
            if (mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
                mc.rightClickDelayTimer = 0;
            }
        }
    }
}
