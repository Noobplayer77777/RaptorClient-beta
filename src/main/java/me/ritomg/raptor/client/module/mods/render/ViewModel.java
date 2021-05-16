package me.ritomg.raptor.client.module.mods.render;

import me.ritomg.raptor.client.events.RenderPlayerHandEvent;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.Module;
import me.ritomg.raptor.client.setting.impl.BooleanSetting;
import me.ritomg.raptor.client.setting.impl.NumberSetting;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

// from postman :DDDDD
// idk how to make view model
public class ViewModel extends Module {

    public BooleanSetting cancelEating = new BooleanSetting("NoEat", this, false);
    public NumberSetting leftX = new NumberSetting("LeftX", this, 0, -2, 2, 0.1);
    public NumberSetting leftY = new NumberSetting("LeftY", this, 0, -2, 2, 0.1);
    public NumberSetting leftZ = new NumberSetting("LeftZ", this, 0, -2, 2, 0.1);
    public NumberSetting rightX = new NumberSetting("RightX", this, 0, -2, 2, 0.1);
    public NumberSetting rightY = new NumberSetting("RightY", this, 0, -2, 2, 0.1);
    public NumberSetting rightZ = new NumberSetting("RightZ", this, 0, -2, 2, 0.1);

    public ViewModel() {
        super("ViewModel", Category.Render);
        addSettings(leftX, leftY, leftZ, rightX, rightY, rightZ);
    }


    @SubscribeEvent
    public void transformEvent(RenderPlayerHandEvent event) {
        if (event.getHand() == EnumHandSide.RIGHT) {
            GlStateManager.translate(rightX.getValue(), rightY.getValue(), rightZ.getValue());
        } else if (event.getHand() == EnumHandSide.LEFT) {
            GlStateManager.translate(leftX.getValue(), leftY.getValue(), leftZ.getValue());
        }
    }
}
