package me.ritomg.raptor.client.ui.hud;

import me.ritomg.raptor.RaptorClient;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class HudEditorScreen extends GuiScreen {

    public ArrayList<HudComponent> components = new ArrayList<>();

    public HudEditorScreen() {
        // where components are added
    }

    private void add(HudComponent comp) {
        components.add(comp);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

    }

    @Override
    public void initGui() {
        RaptorClient.modManager.getMod("ClickGUI").disable();
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            mc.displayGuiScreen(null);
            RaptorClient.modManager.getMod("HudEditor").disable();
        }
    }
}
