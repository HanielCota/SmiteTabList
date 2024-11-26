package com.github.hanielcota.smitetablist;

import com.github.hanielcota.smitetablist.configuration.ConfigUtil;
import com.github.hanielcota.smitetablist.initializer.TablistInitializer;
import com.github.hanielcota.smitetablist.manager.TablistManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class TablistPlugin extends JavaPlugin {

    private TablistManager tablistManager;
    private ConfigUtil configUtil;

    @Override
    public void onEnable() {
        TablistInitializer initializer = new TablistInitializer(this);
        configUtil = initializer.initializeConfig();
        tablistManager = initializer.initializeManagers(configUtil);

        initializer.registerListeners(tablistManager);
        initializer.registerCommands(tablistManager);
    }
}
