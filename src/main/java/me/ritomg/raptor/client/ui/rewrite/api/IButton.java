package me.ritomg.raptor.client.ui.rewrite.api;

public interface IButton {

    void draw(int mouseX, int mouseY);

    void mouseClicked(int mouseX, int mouseY, int mouseButton);

    void handleClick(int mouseButton);

    boolean isMouseWithin(int mouseX, int mouseY);
}
