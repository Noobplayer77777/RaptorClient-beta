package me.ritomg.raptor.client.ui.gui;

import me.ritomg.raptor.RaptorClient;
import me.ritomg.raptor.client.module.Category;
import me.ritomg.raptor.client.setting.impl.NumberSetting;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuiHackManager extends GuiScreen {

    public List<me.ritomg.raptor.client.ui.gui.Panel> panels = new ArrayList<>();

    public GuiHackManager() {
        int offset = 0;
        for (Category c : Category.values()) {
            panels.add(new Panel(RaptorClient.modManager.getModsByCategory(c), c, 20 + offset, 20, 96, 14));
            offset += 120;
        }
        guiColor = new Color((int) ((NumberSetting) RaptorClient.modManager.getMod("ClickGUIOld").getSetting("R")).getValue(),
                (int) ((NumberSetting) RaptorClient.modManager.getMod("ClickGUIOld").getSetting("G")).getValue(),
                (int) ((NumberSetting) RaptorClient.modManager.getMod("ClickGUIOld").getSetting("B")).getValue(),
                255);
    }

    public Color guiColor;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        guiColor = new Color((int) ((NumberSetting) RaptorClient.modManager.getMod("ClickGUIOld").getSetting("R")).getValue(),
                (int) ((NumberSetting) RaptorClient.modManager.getMod("ClickGUIOld").getSetting("G")).getValue(),
                (int) ((NumberSetting) RaptorClient.modManager.getMod("ClickGUIOld").getSetting("B")).getValue(),
                255);
        panels.forEach(panel -> {
            panel.updatePosition(mouseX, mouseY);
            panel.draw(mouseX, mouseY);
        });
    }

    @Override
    public boolean doesGuiPauseGame() { return false; }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            mc.displayGuiScreen(null);
            RaptorClient.modManager.getMod("ClickGUI").disable();
        }

        panels.forEach(panel -> panel.keyTyped(typedChar, keyCode));
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
            panels.forEach(panel -> {
                if (mouseButton == 0) {
                    if (panel.isMouseWithinHeader(mouseX, mouseY)
                            /*&& !panel.panelButton.isMouseWithin(mouseX, mouseY)*/) {
                        panel.dragging = true;
                        panel.dragX = mouseX - panel.x;
                        panel.dragY = mouseY - panel.y;


                    }
                }

                if (mouseButton == 1) {
                    if (panel.isMouseWithinHeader(mouseX, mouseY)) {
                        panel.open = !panel.open;
                    }
                }

                if (panel.open)
                    panel.buttons.forEach(modButton -> modButton.mouseClicked(mouseX, mouseY, mouseButton));

                //panel.mouseClicked(mouseX, mouseY, mouseButton);
            });
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        panels.forEach(panel -> {
            panel.dragging = false;
            panel.mouseReleased(mouseX, mouseY, mouseButton);
        });
    }
}