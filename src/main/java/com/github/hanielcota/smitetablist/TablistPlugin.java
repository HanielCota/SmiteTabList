package com.github.hanielcota.smitetablist;

import co.aikar.commands.PaperCommandManager;
import com.github.hanielcota.smitetablist.commands.TablistCommand;
import com.github.hanielcota.smitetablist.listener.PlayerJoinListener;
import com.github.hanielcota.smitetablist.manager.TablistManager;
import com.github.hanielcota.smitetablist.provider.ConfigPrefixProvider;
import com.github.hanielcota.smitetablist.utils.ConfigUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class TablistPlugin extends JavaPlugin {

	private TablistManager tablistManager;
	private ConfigUtil configUtil;

	@Override
	public void onEnable() {
		initializeConfig();
		initializeManagers();
		registerListeners();
		registerCommands();
	}

	private void initializeConfig() {
		this.configUtil = new ConfigUtil(this, "config.yml");
		configUtil.initialize();
	}

	private void initializeManagers() {
		ConfigPrefixProvider prefixProvider = new ConfigPrefixProvider(configUtil);
		this.tablistManager = new TablistManager(prefixProvider, configUtil);
	}

	private void registerListeners() {
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(tablistManager), this);
	}

	private void registerCommands() {
		PaperCommandManager commandManager = new PaperCommandManager(this);
		commandManager.registerCommand(new TablistCommand(tablistManager, this));
	}
}
