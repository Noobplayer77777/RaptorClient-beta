package me.ritomg.raptor.client.ui.rewrite.impl;

import com.google.common.collect.Lists;
import me.ritomg.raptor.RaptorClient;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.module.mods.misc.ClickGUI;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ClickGuiScreen extends GuiScreen {

    ArrayList<GuiPanel> panels;

    public static Color guiColor;

    public ClickGuiScreen() {
        panels = new ArrayList<>();

        int xOffset = 0;
        for (Category c : Category.values()) {
            panels.add(new GuiPanel(20 + xOffset, 20, c));
            xOffset += 110;
        }

        guiColor = new Color(5, 253, 73);
    }

    @Override
    public void updateScreen() {
        int r = (int) ClickGUI.redValue.value;
        int g = (int) ClickGUI.greenValue.value;
        int b = (int) ClickGUI.blueValue.value;
        guiColor = new Color(r, g, b, 255);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        panels.forEach(panel -> panel.draw(mouseX, mouseY));
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for (GuiPanel p : Lists.reverse(panels)) {

            if (p.open) {
                if (p.isMouseWithin(mouseX, mouseY)) {
                    p.mouseClicked(mouseX, mouseY, mouseButton);
                    movePanel(p);
                    return;
                }
            } else {
                if (p.button.isMouseWithin(mouseX, mouseY)) {
                    p.mouseClicked(mouseX, mouseY, mouseButton);
                    movePanel(p);
                    return;
                }
            }
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        panels.forEach(panel -> panel.mouseReleased(mouseX, mouseY, mouseButton));
    }

    @Override
    public void keyTyped(char keyChar, int keyCode) {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            RaptorClient.modManager.getMod("ClickGUI").disable();
            mc.displayGuiScreen(null);
            return;
        }

        panels.forEach(panel -> panel.keyTyped(keyChar, keyCode));
    }

    private void movePanel(GuiPanel p) {
        panels.remove(p);
        panels.add(p);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void handleInput() throws IOException {
        panels.forEach(panel -> panel.y += (Mouse.getEventDWheel() / 2));
        super.handleInput();
    }
}
