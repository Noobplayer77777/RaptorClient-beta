package me.ritomg.raptor.client.module.mods.combat;

import me.ritomg.raptor.client.events.LocalTickEvent;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import me.ritomg.raptor.client.setting.impl.ModeSetting;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Offhand extends Module {

    public ModeSetting item = new ModeSetting("Item", this, "Gapple", "Gapple", "Crystal");

    public Offhand() {
        super("Offhand", Category.Combat);
        addSettings(item);
    }

    @SubscribeEvent
    public void onTick(LocalTickEvent event) {
        if (mc.player.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).getItem() == getModeItem())
            return;

        final int slot = this.getItemSlot();

        if (slot != -1) {
            mc.playerController.windowClick(mc.player.inventoryContainer.windowId, slot, 0, ClickType.PICKUP, mc.player);
            mc.playerController.windowClick(mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, mc.player);
            mc.playerController.windowClick(mc.player.inventoryContainer.windowId, slot, 0, ClickType.PICKUP, mc.player);
            mc.playerController.updateController();
        }
    }

    private int getItemSlot() {
        for (int i = 0; i < 36; i++) {
            final Item item = mc.player.inventory.getStackInSlot(i).getItem();
            if (item == getModeItem()) {
                if (i < 9) {
                    i += 36;
                }
                return i;
            }
        }
        return -1;
    }

    private Item getModeItem() {
        if (item.is("Gapple")) {
            return Items.GOLDEN_APPLE;
        } else {
            return Items.END_CRYSTAL;
        }
    }
}
