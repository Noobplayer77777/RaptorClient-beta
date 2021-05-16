package me.ritomg.raptor.client.misc.util;

import me.ritomg.raptor.RaptorClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.awt.*;

public class FontUtil {

    public static boolean doCustomFont = false;

    public static void drawString(String text, float x, float y, int color) {
        if (doCustomFont) {
            RaptorClient.cfont.drawString(text, x, y, new Color(color));
        } else {
            Minecraft.getMinecraft().fontRenderer.drawString(text, x, y, color, false);
        }
    }

    public static void drawStringWithShadow(String text, float x, float y, int color) {
        if (doCustomFont) {
            RaptorClient.cfont.drawStringWithShadow(text, x, y, new Color(color));
        } else {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(text, x, y, color);
        }
    }

    public static int getStringWidth(String text) {
        if (doCustomFont) {
            return RaptorClient.cfont.getStringWidth(text);
        } else {
            return Minecraft.getMinecraft().fontRenderer.getStringWidth(text);
        }
    }

    public static int getHeight() {
        if (doCustomFont) {
            return RaptorClient.cfont.getHeight();
        } else {
            return Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
        }
    }
}
