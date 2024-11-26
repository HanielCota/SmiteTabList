package com.github.hanielcota.smitetablist.provider;

import com.github.hanielcota.smitetablist.cache.PrefixCache;
import com.github.hanielcota.smitetablist.configuration.ConfigUtil;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class ConfigPrefixProvider implements PermissionPrefixProvider {

    private static final String DEFAULT_PREFIX = "&7[Player] ";

    private final ConfigUtil configUtil;
    private final PrefixCache prefixCache;

    @Override
    public String getPrefix(Player player) {
        String cachedPrefix = prefixCache.get(player.getName());
        if (cachedPrefix != null) {
            return cachedPrefix;
        }

        String computedPrefix = computePrefix(player);
        prefixCache.put(player.getName(), computedPrefix);
        return computedPrefix;
    }

    private String computePrefix(Player player) {
        if (configUtil.getConfiguration() == null) {
            logWarning("Configuração não carregada. Usando prefixo padrão.");
            return DEFAULT_PREFIX;
        }

        var section = configUtil.getConfiguration().getConfigurationSection("tablist.prefixes");
        if (section == null) {
            logWarning("Seção 'tablist.prefixes' não encontrada no config.yml.");
            return DEFAULT_PREFIX;
        }

        Map<String, Object> prefixes = section.getValues(false);
        for (Map.Entry<String, Object> entry : prefixes.entrySet()) {
            if (isValidPrefix(entry) && player.hasPermission(entry.getKey())) {
                return (String) entry.getValue();
            }
        }
        return (String) prefixes.getOrDefault("default", DEFAULT_PREFIX);
    }

    private void logWarning(String message) {
        configUtil.getPlugin().getLogger().warning(message);
    }

    private boolean isValidPrefix(Map.Entry<String, Object> entry) {
        if (!(entry.getValue() instanceof String)) {
            logWarning("A chave '" + entry.getKey() + "' contém um valor inválido. Ignorando...");
            return false;
        }
        return true;
    }
}
