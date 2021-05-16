package me.ritomg.raptor.client.module.mods.movement;

import ibxm.Player;
import me.ritomg.raptor.client.events.PlayerMoveEvent;
import me.ritomg.raptor.client.events.PlayerjumpEvent;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import me.ritomg.raptor.client.setting.impl.BooleanSetting;
import me.ritomg.raptor.client.setting.impl.ModeSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.init.MobEffects;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Strafe extends Module{

	public BooleanSetting mode = new BooleanSetting("Mode", this, false);
	public ModeSetting speed_mode = new ModeSetting("SpeedMode", this, "Strafe", "On Ground");
	public BooleanSetting auto_Sprint = new BooleanSetting("Auto Sprint", this, true);
	public BooleanSetting on_Water = new BooleanSetting("On Water", this, true);
	public BooleanSetting autoJump = new BooleanSetting("Autor Jump", this, true);
	public BooleanSetting backward = new BooleanSetting("BackWards", this, true);
	public BooleanSetting bypass = new BooleanSetting("Bypass", this, false);
	
	public Strafe() {
		super("Strafe", Category.Movement);
		addSettings(speed_mode, auto_Sprint, on_Water, autoJump, backward);
	}

//	@SubscribeEvent
//	public void onMove(PlayerMoveEvent event) {
//		final Minecraft mc = Minecraft.getMinecraft();
//
//		if (mc.player == null)
//			return;
//
//		if (mc.player.isSneaking() || mc.player.isOnLadder() || mc.player.isInWeb || mc.player.isInLava() || mc.player.isInWater() || mc.player.capabilities.isFlying)
//			return;
//
//		//if (this.elytraCheck.getValue() && mc.player.isElytraFlying())
//		//return;
//
//		// check to bypass option on ground or not
//		if (!this.ground.isEnabled()) {
//			if (mc.player.onGround)
//				return;
//		}
//
//		// check for flight, could be an option maybe but it bugs out  packet fly
//		/*final FlightModule flightModule = (FlightModule) Seppuku.INSTANCE.getModuleManager().find(FlightModule.class);
//		if (flightModule != null && flightModule.isEnabled()) {
//			return;
//		}*/
//
//		// movement data variables
//		float playerSpeed = 0.2873f;
//		float moveForward = mc.player.movementInput.moveForward;
//		float moveStrafe = mc.player.movementInput.moveStrafe;
//		float rotationPitch = mc.player.rotationPitch;
//		float rotationYaw = mc.player.rotationYaw;
//
//		// check for speed potion
//		if (mc.player.isPotionActive(MobEffects.SPEED)) {
//			final int amplifier = mc.player.getActivePotionEffect(MobEffects.SPEED).getAmplifier();
//			playerSpeed *= (1.0f + 0.2f * (amplifier + 1));
//		}
//
//		// not movement input, stop all motion
//		if (moveForward == 0.0f && moveStrafe == 0.0f) {
//			event.setX(0.0d);
//			event.setZ(0.0d);
//		} else {
//			if (moveForward != 0.0f) {
//				if (moveStrafe > 0.0f) {
//					rotationYaw += ((moveForward > 0.0f) ? -45 : 45);
//				} else if (moveStrafe < 0.0f) {
//					rotationYaw += ((moveForward > 0.0f) ? 45 : -45);
//				}
//				moveStrafe = 0.0f;
//				if (moveForward > 0.0f) {
//					moveForward = 1.0f;
//				} else if (moveForward < 0.0f) {
//					moveForward = -1.0f;
//				}
//			}
//			event.setX((moveForward * playerSpeed) * Math.cos(Math.toRadians((rotationYaw + 90.0f))) + (moveStrafe * playerSpeed) * Math.sin(Math.toRadians((rotationYaw + 90.0f))));
//			event.setZ((moveForward * playerSpeed) * Math.sin(Math.toRadians((rotationYaw + 90.0f))) - (moveStrafe * playerSpeed) * Math.cos(Math.toRadians((rotationYaw + 90.0f))));
//		}
//
//		// we need to ensure we don't interfere with safewalk's limitations, so we run it's checks again on the same event
//		/*final SafeWalkModule safeWalkModule = (SafeWalkModule) Seppuku.INSTANCE.getModuleManager().find(SafeWalkModule.class);
//		if (safeWalkModule != null && safeWalkModule.isEnabled()) {
//			safeWalkModule.onMove(event);
//		}*/
//	}

	public void update() {

		if (mc.player.isRiding()) return;

		if (mc.player.isInWater() || mc.player.isInLava()) {
			if (!on_Water.isEnabled()) return;
		}

		if (mc.player.moveForward != 0 || mc.player.moveStrafing != 0) {

			if (mc.player.moveForward < 0 && !backward.isEnabled()) return;

			if (auto_Sprint.isEnabled()) {
				mc.player.setSprinting(true);
			}

			if (mc.player.onGround && speed_mode.is("Strafe")) {

				if (autoJump.isEnabled()) {
					mc.player.motionY = 0.405f;
				}

				final float yaw = get_rotation_yaw() * 0.017453292F;
				mc.player.motionX -= MathHelper.sin(yaw) * 0.2f;
				mc.player.motionZ += MathHelper.cos(yaw) * 0.2f;

			} else if (mc.player.onGround && speed_mode.is("On Ground")) {

				final float yaw = get_rotation_yaw();
				mc.player.motionX -= MathHelper.sin(yaw) * 0.2f;
				mc.player.motionZ += MathHelper.cos(yaw) * 0.2f;
				mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY+0.4, mc.player.posZ, false));

			}

		}

		if (mc.gameSettings.keyBindJump.isKeyDown() && mc.player.onGround) {
			mc.player.motionY = 0.405f;
		}

	}

	@SubscribeEvent
	public void playerjump (PlayerjumpEvent event){

		if (speed_mode.is("Strafe")) {
			event.Cancel();
		}

	}

	@SubscribeEvent
	public void playermove (PlayerMoveEvent event) {

		if (speed_mode.is("On Ground")) return;

		if (mc.player.isInWater() || mc.player.isInLava()) {
			if (!mode.isEnabled()) return;
		}

		if (mc.player.isSneaking() || mc.player.isOnLadder() || mc.player.isInWeb || mc.player.isInLava() || mc.player.isInWater() || mc.player.capabilities.isFlying) return;

		float player_speed = 0.2873f;
		float move_forward = mc.player.movementInput.moveForward;
		float move_strafe = mc.player.movementInput.moveStrafe;
		float rotation_yaw = mc.player.rotationYaw;

		if (mc.player.isPotionActive(MobEffects.SPEED)) {
			final int amp = mc.player.getActivePotionEffect(MobEffects.SPEED).getAmplifier();
			player_speed *= (1.2f * (amp+1));
		}

		if (bypass.isEnabled()) {
			player_speed *= 1.0064f;
		}

		if (move_forward == 0 && move_strafe == 0) {
			event.setX(0.0d);
			event.setZ(0.0d);
		} else {
			if (move_forward != 0.0f) {
				if (move_strafe > 0.0f) {
					rotation_yaw += ((move_forward > 0.0f) ? -45 : 45);
				} else if (move_strafe < 0.0f) {
					rotation_yaw += ((move_forward > 0.0f) ? 45 : -45);
				}
				move_strafe = 0.0f;
				if (move_forward > 0.0f) {
					move_forward = 1.0f;
				} else if (move_forward < 0.0f) {
					move_forward = -1.0f;
				}
			}

			event.setX((move_forward * player_speed) * Math.cos(Math.toRadians((rotation_yaw + 90.0f))) + (move_strafe * player_speed) * Math.sin(Math.toRadians((rotation_yaw + 90.0f))));
			event.setZ((move_forward * player_speed) * Math.sin(Math.toRadians((rotation_yaw + 90.0f))) - (move_strafe * player_speed) * Math.cos(Math.toRadians((rotation_yaw + 90.0f))));

		}

		event.cancel();

	}

	private float get_rotation_yaw() {
		float rotation_yaw = mc.player.rotationYaw;
		if (mc.player.moveForward < 0.0f) {
			rotation_yaw += 180.0f;
		}
		float n = 1.0f;
		if (mc.player.moveForward < 0.0f) {
			n = -0.5f;
		}
		else if (mc.player.moveForward > 0.0f) {
			n = 0.5f;
		}
		if (mc.player.moveStrafing > 0.0f) {
			rotation_yaw -= 90.0f * n;
		}
		if (mc.player.moveStrafing < 0.0f) {
			rotation_yaw += 90.0f * n;
		}
		return rotation_yaw * 0.017453292f;
	}

}
