package me.ritomg.raptor.client.module.mods.combat;

import me.ritomg.raptor.client.events.LocalTickEvent;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import me.ritomg.raptor.client.setting.impl.BooleanSetting;
import me.ritomg.raptor.client.setting.impl.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class KillAura extends Module {

    private NumberSetting range = new NumberSetting("Range", this, 4.0, 1.0, 8.0, 0.25);
    private BooleanSetting players = new BooleanSetting("Players", this, true);
    private BooleanSetting mobs = new BooleanSetting("Mobs", this, true);
    private BooleanSetting animals = new BooleanSetting("Animals", this, false);

    public KillAura() {
        super("KillAura", Category.Combat);
        addSettings(range, players, mobs, animals);
    }

    // kill aura pasted from an old client of mine

    @SubscribeEvent
    public void onTick(LocalTickEvent event) {
        List<Entity> targets = mc.world.loadedEntityList.stream()
                .filter(Objects::nonNull)
                .filter(entity -> entity != mc.player)
                .filter(entity -> mc.player.getDistance(entity) <= range.getValue())
                .filter(entity -> !entity.isDead)
                .filter(Entity::isEntityAlive)
                .filter(this::attackCheck)
                .sorted(Comparator.comparing(entity -> mc.player.getDistance(entity)))
                .collect(Collectors.toList());
        if (targets.size() == 0)
            return;

        targets.forEach(this::attack);
    }

    private void attack(Entity entity) {
        if (entity.isDead)
            return;

        if (mc.player.getCooledAttackStrength(0) >= 1) {
            mc.playerController.attackEntity(mc.player, entity);
            mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }

    private boolean attackCheck(Entity entity) {
        if (entity instanceof EntityPlayer && players.enabled) {
            if (((EntityPlayer) entity).getHealth() > 0)
                return true;
        } else if (entity instanceof EntityMob && mobs.enabled) {
            if (((EntityMob) entity).getHealth() > 0)
                return true;
        } else if (entity instanceof EntityAnimal && animals.enabled) {
            if (((EntityAnimal) entity).getHealth() > 0)
                return true;
        }
        return false;
    }
}
