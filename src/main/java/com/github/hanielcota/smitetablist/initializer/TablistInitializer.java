package com.github.hanielcota.smitetablist.initializer;

import co.aikar.commands.PaperCommandManager;
import com.github.hanielcota.smitetablist.TablistPlugin;
import com.github.hanielcota.smitetablist.commands.TablistCommand;
import com.github.hanielcota.smitetablist.configuration.ConfigUtil;
import com.github.hanielcota.smitetablist.listener.PlayerJoinListener;
import com.github.hanielcota.smitetablist.manager.TablistManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;

@RequiredArgsConstructor
public class TablistInitializer {

    private final TablistPlugin plugin;

    public ConfigUtil initializeConfig() {
        ConfigUtil configUtil = new ConfigUtil(plugin, "config.yml");
        configUtil.initialize();
        return configUtil;
    }

    public TablistManager initializeManagers(ConfigUtil configUtil) {
        return new TablistManager(configUtil);
    }

    public void registerListeners(TablistManager manager) {
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(manager), plugin);
    }

    public void registerCommands(TablistManager manager) {
        PaperCommandManager commandManager = new PaperCommandManager(plugin);
        commandManager.registerCommand(new TablistCommand(manager, plugin));
    }
}
