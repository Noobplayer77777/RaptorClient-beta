package me.ritomg.raptor.client.setting.impl;

import me.ritomg.raptor.client.module.Module;
import me.ritomg.raptor.client.setting.Setting;

public class KeyBindSetting extends Setting {
    public int code;

    public KeyBindSetting(int code, Module parent) {
        this.name = "KeyBind";
        this.code = code;
        this.parent = parent;
    }

    public int getKeyCode() {
        return this.code;
    }

    public void setKeyCode(int code) {
        this.code = code;
    }
}