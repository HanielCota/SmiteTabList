package com.github.hanielcota.smitetablist.utils;

import java.io.File;
import java.io.IOException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

@Getter
@RequiredArgsConstructor
public class ConfigUtil {

    private final Plugin plugin;
    private final String fileName;
    private File configFile;
    private FileConfiguration configuration;

    /**
     * Inicializa o arquivo de configuração.
     */
    public void initialize() {
        if (plugin == null || fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("Plugin ou fileName não podem ser nulos ou vazios!");
        }

        configFile = new File(plugin.getDataFolder(), fileName.endsWith(".yml") ? fileName : fileName + ".yml");

        if (!configFile.exists()) {
            if (!configFile.getParentFile().exists()
                    && !configFile.getParentFile().mkdirs()) {
                plugin.getLogger()
                        .severe("Falha ao criar a estrutura de diretórios para: " + configFile.getAbsolutePath());
                return;
            }

            plugin.saveResource(fileName, false);
        }

        loadConfiguration();
    }

    /**
     * Carrega o arquivo de configuração em memória.
     */
    public void loadConfiguration() {
        if (configFile == null) {
            plugin.getLogger().warning("Arquivo de configuração não foi inicializado.");
            return;
        }

        configuration = YamlConfiguration.loadConfiguration(configFile);
    }

    /**
     * Salva a configuração no arquivo.
     */
    public void saveConfiguration() {
        if (configuration == null) {
            plugin.getLogger().warning("Configuração não foi carregada, não é possível salvar.");
            return;
        }

        try {
            configuration.save(configFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Erro ao salvar a configuração: " + fileName + ". Detalhes: " + e.getMessage());
        }
    }

    /**
     * Recarrega o arquivo de configuração.
     */
    public void reloadConfiguration() {
        loadConfiguration();
    }

    /**
     * Retorna um valor da configuração.
     *
     * @param path Caminho da chave.
     * @param <T>  Tipo esperado do valor.
     * @return Valor da chave ou null se não existir.
     */
    public <T> T get(String path, Class<T> type) {
        if (configuration == null || path == null || path.isEmpty()) {
            plugin.getLogger().warning("Configuração ou caminho inválido: " + path);
            return null;
        }

        Object value = configuration.get(path);
        return type.isInstance(value) ? type.cast(value) : null;
    }

    /**
     * Define um valor na configuração e salva.
     *
     * @param path  Caminho da chave.
     * @param value Valor a ser definido.
     */
    public void set(String path, Object value) {
        if (configuration == null || path == null || path.isEmpty()) {
            plugin.getLogger().warning("Configuração ou caminho inválido: " + path);
            return;
        }

        configuration.set(path, value);
        saveConfiguration();
    }

    /**
     * Verifica se uma chave existe na configuração.
     *
     * @param path Caminho da chave.
     * @return true se existir, false caso contrário.
     */
    public boolean contains(String path) {
        return configuration != null && path != null && configuration.contains(path);
    }
}
