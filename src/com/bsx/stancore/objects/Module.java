package com.bsx.stancore.objects;

import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Bradley on 11/24/16.
 */
public abstract class Module {
    private boolean enabled;
    private boolean initialized;
    private HashMap<Player, Boolean> debugMap;

    public abstract HashMap<Player, Boolean> getDebugMap();

    public abstract boolean isEnabled();

    public abstract void initModule();

    public abstract boolean isInitialized();

    public abstract void setDebugMode(Player p, boolean debug);

    public abstract boolean hasDebugModeEnabled(Player p);

    public abstract String getModuleName();
}
