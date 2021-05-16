package me.ritomg.raptor.client.events;

import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class PlayerjumpEvent extends Event {

    boolean cancelled;

    public void Cancel() {
        this.cancelled = true;
    }

    public double motion_x;

    public double getMotion_x() {
        return motion_x;
    }

    public void setMotion_x(double motion_x) {
        this.motion_x = motion_x;
    }

    public double getMotion_y() {
        return motion_y;
    }

    public void setMotion_y(double motion_y) {
        this.motion_y = motion_y;
    }

    public double motion_y;

    public void PlayerJumpEvent(double motion_x, double motion_y) {

        this.motion_x = motion_x;
        this.motion_y = motion_y;
    }


}
