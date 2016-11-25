package com.bsx.stancore.modules.cfactions;

import com.bsx.stancore.objects.Module;

/**
 * Created by Bradley on 11/24/16.
 */
public class CFactionsModule extends Module {
    private boolean enabled;

    private boolean initialized;

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
}
