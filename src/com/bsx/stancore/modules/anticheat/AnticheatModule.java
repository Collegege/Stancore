package com.bsx.stancore.modules.anticheat;

import com.bsx.stancore.objects.Module;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Bradley on 11/24/16.
 */
public class AnticheatModule extends Module {
    private boolean enabled;

    private HashMap<Player, Boolean> debugMap;

    private boolean initialized;

    @Override
    public HashMap<Player, Boolean> getDebugMap() {
        return debugMap;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void initModule() {

    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public void setDebugMode(Player p, boolean debug) {
        if (debugMap.containsKey(p)) {
            debugMap.remove(p);
        }
        debugMap.put(p, debug);
    }

    @Override
    public boolean hasDebugModeEnabled(Player p) {
        return (debugMap.containsKey(p) || debugMap.get(p));
    }

    @Override
    public String getModuleName() {
        return "anticheat";
    }
}