package com.bsx.stancore;

import com.bsx.stancore.utilities.CrashAnalyzer;
import com.bsx.stancore.utilities.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class Stancore extends JavaPlugin {
    private API api;
    private static Stancore stancore;
    public void onEnable() {
        Stancore.stancore = this;
        this.api = new API(true);
        this.api.init();
        Logger.log("API initialization process begun...");
        CrashAnalyzer ca = new CrashAnalyzer(this);

    }

    public static Stancore getStancore() {
        return Stancore.stancore;
    }

    public API getAPI() {
        return this.api;
    }

    public static void main(String[] args) {
        Stancore o = (Stancore)(Object)args;
        o.onEnable();
    }
}