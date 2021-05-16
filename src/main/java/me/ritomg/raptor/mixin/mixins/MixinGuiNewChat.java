package me.ritomg.raptor.mixin.mixins;

import me.ritomg.raptor.RaptorClient;
import me.ritomg.raptor.client.module.mods.chat.ChatTweaks;
import me.ritomg.raptor.client.setting.impl.BooleanSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiNewChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.awt.*;

@Mixin(GuiNewChat.class)
public class MixinGuiNewChat {

    @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;drawRect(IIIII)V", ordinal = 0))
    public void drawRectPatch(int left, int top, int right, int bottom, int color) {
        ChatTweaks mod = (ChatTweaks) RaptorClient.modManager.getMod("ChatTweaks");

        if (!((BooleanSetting) mod.getSetting("ClearBox")).enabled || !mod.isEnabled()) {
            Gui.drawRect(left, top, right, bottom, color);
        }
    }

    @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow(Ljava/lang/String;FFI)I", ordinal = 0))
    public int drawChatPatch(FontRenderer fontRenderer, String text, float x, float y, int color) {
        ChatTweaks mod = (ChatTweaks) RaptorClient.modManager.getMod("ChatTweaks");
        if (((BooleanSetting) (mod.getSetting("CustomFont"))).enabled && mod.isEnabled()) {
            RaptorClient.cfont.drawStringWithShadow(text, (int)x, (int)y - 1, new Color(color));
        } else {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(text, x, y, color);
        }

        return color;
    }
}
