package com.github.hanielcota.smitetablist.provider;

import com.github.hanielcota.smitetablist.utils.ConfigUtil;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class ConfigPrefixProvider implements PermissionPrefixProvider {

    private final ConfigUtil configUtil;

    @Override
    public String getPrefix(Player player) {
        if (configUtil.getConfiguration() == null) {
            configUtil.getPlugin().getLogger().warning("Configuração não carregada. Usando prefixo padrão.");
            return "&7[Player] ";
        }

        var section = configUtil.getConfiguration().getConfigurationSection("tablist.prefixes");

        if (section == null) {
            configUtil.getPlugin().getLogger().warning("Seção 'tablist.prefixes' não encontrada no config.yml.");
            return "&7[Player] ";
        }

        Map<String, Object> prefixes = section.getValues(false);
        for (Map.Entry<String, Object> entry : prefixes.entrySet()) {

            String permission = entry.getKey();
            Object value = entry.getValue();

            if (!(value instanceof String prefix)) {
                configUtil
                        .getPlugin()
                        .getLogger()
                        .warning("A chave '" + permission + "' contém um valor inválido. Ignorando...");

                continue;
            }

            if (player.hasPermission(permission)) {
                return prefix;
            }
        }

        return (String) prefixes.getOrDefault("default", "&7[Player] ");
    }
}
