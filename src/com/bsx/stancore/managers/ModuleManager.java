package com.bsx.stancore.managers;

import com.bsx.stancore.Stancore;
import com.bsx.stancore.modules.anticheat.AnticheatModule;
import com.bsx.stancore.modules.cfactions.CFactionsModule;
import com.bsx.stancore.modules.hcfactions.HCFactionsModule;
import com.bsx.stancore.modules.practice.PracticeModule;
import com.bsx.stancore.objects.Module;
import com.bsx.stancore.utilities.Logger;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bradley on 11/23/16.
 */
public class ModuleManager {
    private String state = "";
    private List<Module> am;
    public void init() {
        this.initActiveModules();
        this.state = "complete";
        return;
    }

    public void setActiveModules(List<Module> am) {
        this.am = am;
    }

    public List<Module> getActiveModules() {
        return this.am;
    }

    public void initActiveModules() {
        List<Module> modules = new ArrayList<Module>();
        if (Stancore.getStancore().getConfig().getStringList("modules.enabled") != null) {
            for (String s : Stancore.getStancore().getConfig().getStringList("modules.enabled")) {
                if (s.equalsIgnoreCase("anticheat")) {
                    modules.add(new AnticheatModule());
                } else if (s.equalsIgnoreCase("cfactions")) {
                    modules.add(new CFactionsModule());
                } else if (s.equalsIgnoreCase("hcfactions")) {
                    modules.add(new HCFactionsModule());
                } else if (s.equalsIgnoreCase("practice")) {
                    modules.add(new PracticeModule());
                } else {
                    Logger.log("[Stancore] Error parsing through modules.enabled in config.yml. Server crashing... [Error 0x00f1, ModuleManager.class ; initActiveModules()]");
                    Stancore.getStancore().getServer().dispatchCommand(Bukkit.getConsoleSender(), "stop");
                    return;
                }
            }
            if (modules.isEmpty()) {
                Logger.log("[Stancore] No modules enabled. Server crashing... [Error 0x00f2, ModuleManager.class ; initActiveModules()]");
                Stancore.getStancore().getServer().dispatchCommand(Bukkit.getConsoleSender(), "stop");
                return;
            } else {
                for (Module m : modules) {
                    m.initModule();
                }
                setActiveModules(modules);
                return;
            }
        } else {
            Logger.log("[Stancore] Module enabled list not found. Server crashing... [Error 0x00f4, ModuleManager.class ; initActiveModules()]");
            Stancore.getStancore().getServer().dispatchCommand(Bukkit.getConsoleSender(), "stop");
            return;
        }
    }

    public boolean isFullyInitialized() {
        return (this.state.equalsIgnoreCase("complete"));
    }
}
