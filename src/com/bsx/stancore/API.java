package com.bsx.stancore;

import com.bsx.stancore.managers.ModuleManager;
import com.bsx.stancore.utilities.Logger;

/**
 * Created by Bradley on 11/24/16.
 */
public class API {
    private ModuleManager moduleManager;
    private boolean pluginEnabled = false;
    public API(boolean pluginEnabled) {
        this.pluginEnabled = pluginEnabled;
    }

    public void init() {
        this.moduleManager = new ModuleManager();
        Logger.log("Initialization of the module manager starting...");
        this.moduleManager.init();
        if (moduleManager.isFullyInitialized()) {
            Logger.log("Initialization of the module manager complete.");
        }
    }

    public ModuleManager getModuleManager() {
        return this.moduleManager != null ? this.moduleManager : null;
    }
}
