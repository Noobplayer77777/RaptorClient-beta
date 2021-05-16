package me.ritomg.raptor.client.module;

import me.ritomg.raptor.client.misc.util.FontUtil;
import me.ritomg.raptor.client.module.mods.chat.ChatSuffix;
import me.ritomg.raptor.client.module.mods.chat.ChatTweaks;
import me.ritomg.raptor.client.module.mods.chat.Greentext;
import me.ritomg.raptor.client.module.mods.chat.Shrug;
import me.ritomg.raptor.client.module.mods.combat.*;
import me.ritomg.raptor.client.module.mods.misc.*;
import me.ritomg.raptor.client.module.mods.movement.*;
import me.ritomg.raptor.client.module.mods.render.*;
import me.ritomg.raptor.client.module.mods.world.*;
import me.ritomg.raptor.client.module.mods.combat.*;
import me.ritomg.raptor.client.module.mods.misc.*;
import me.ritomg.raptor.client.module.mods.movement.*;
import me.ritomg.raptor.client.module.mods.render.*;
import me.ritomg.raptor.client.module.mods.world.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {

    public ModuleManager() {
        init();
    }

    ArrayList<Module> modules = new ArrayList<>();

    public ArrayList<Module> sortedMods = new ArrayList<>();

    private void init() {
        // combat
        registerMod(new AutoCrystal());
        registerMod(new AutoTotem());
        registerMod(new Criticals());
        registerMod(new FastUtil());
        registerMod(new KillAura());
        registerMod(new Offhand());

        // render
        registerMod(new BlockOutline());
        registerMod(new ESP());
        registerMod(new Freecam());
        registerMod(new Fullbright());
        registerMod(new NameTags());
        registerMod(new NoCaveCulling());
        registerMod(new NoRender());
        registerMod(new NoWeather());
        registerMod(new ViewModel());
        registerMod(new Wallhack());
        registerMod(new ImposterESP());
        registerMod(new XRay());

        // movement
        registerMod(new AirJump());
        registerMod(new AutoWalk());
        registerMod(new NoFall());
        registerMod(new NoSlow());
        registerMod(new SafeWalk());
        registerMod(new Sprint());
        registerMod(new Step());
        registerMod(new Velocity());
        registerMod(new Strafe());

        // world
        registerMod(new AntiSound());
        registerMod(new BuildHeight());
        registerMod(new FastPlace());
        registerMod(new NoRotate());
        registerMod(new SoundLogger());
        registerMod(new Timer());
        registerMod(new XCarry());

        // misc
        registerMod(new AntiCrash());
        registerMod(new Australia());
        registerMod(new DiscordRPC());
        registerMod(new ClickGUI());
        registerMod(new ClickGUIOld());
        registerMod(new CustomFont());
        registerMod(new FakePlayer());
        registerMod(new HudEditor());

        // chat
        registerMod(new Greentext());
        registerMod(new ChatSuffix());
        registerMod(new Shrug());
        registerMod(new ChatTweaks());
            
        modules.sort(this::compareTo);

        sortedMods.addAll(modules);
        sortedMods.sort(this::compareLength);
    }

     //alphabetical sort
    private int compareTo(Module mod1, Module mod2) {
        return mod1.getName().compareTo(mod2.getName());
    }

    private int compareLength(Module mod1, Module mod2) {
        return Integer.compare(FontUtil.getStringWidth(mod1.getName()), FontUtil.getStringWidth(mod2.getName()));
    }

    private void registerMod(Module m) {
        modules.add(m);
    }

    public Module getMod(String name) {
        return modules.stream().filter(mod -> mod.name.equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public List<Module> getModsByCategory(Category category) {
        return modules.stream().filter(mod -> mod.category == category).collect(Collectors.toList());
    }

    public ArrayList<Module> getMods() {
        return modules;
    }
}
