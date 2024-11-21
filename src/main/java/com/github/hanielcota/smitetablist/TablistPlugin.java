package com.github.hanielcota.smitetablist;

import co.aikar.commands.PaperCommandManager;
import com.github.hanielcota.smitetablist.commands.TablistCommand;
import com.github.hanielcota.smitetablist.listener.PlayerJoinListener;
import com.github.hanielcota.smitetablist.manager.TablistManager;
import com.github.hanielcota.smitetablist.provider.ConfigPrefixProvider;
import com.github.hanielcota.smitetablist.utils.ConfigUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
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

        getLogger().info("SmiteTabList foi ativado com sucesso!");
    }

    @Override
    public void onDisable() {
        getLogger().info("SmiteTabList foi desativado!");
    }

    /**
     * Inicializa o sistema de configuração do plugin.
     */
    private void initializeConfig() {
        this.configUtil = new ConfigUtil(this, "config.yml");
        configUtil.initialize();
        getLogger().info("Configuração carregada com sucesso.");
    }

    /**
     * Inicializa os gerenciadores necessários para o plugin.
     */
    private void initializeManagers() {
        ConfigPrefixProvider prefixProvider = new ConfigPrefixProvider(configUtil);
        this.tablistManager = new TablistManager(prefixProvider, configUtil);
        getLogger().info("TablistManager inicializado.");
    }

    /**
     * Registra todos os listeners do plugin.
     */
    private void registerListeners() {
        registerEvent(new PlayerJoinListener(tablistManager));
        getLogger().info("Listeners registrados.");
    }

    /**
     * Método auxiliar para registrar um listener.
     *
     * @param listener Listener a ser registrado.
     */
    private void registerEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    /**
     * Registra todos os comandos do plugin.
     */
    private void registerCommands() {
        PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new TablistCommand(tablistManager, this));
        getLogger().info("Comandos registrados.");
    }
}
