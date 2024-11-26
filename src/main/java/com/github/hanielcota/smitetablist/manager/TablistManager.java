package com.github.hanielcota.smitetablist.manager;

import com.github.hanielcota.smitetablist.cache.PrefixCache;
import com.github.hanielcota.smitetablist.configuration.ConfigUtil;
import com.github.hanielcota.smitetablist.provider.ConfigPrefixProvider;
import com.github.hanielcota.smitetablist.services.TablistPlaceholderService;
import com.github.hanielcota.smitetablist.utils.ColorUtils;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Getter
public class TablistManager {

    private final ConfigUtil configUtil;
    private final ConfigPrefixProvider prefixProvider;
    private final TablistPlaceholderService placeholderService = new TablistPlaceholderService();
    private final PlayerNickDisplayManager playerNickDisplayManager = new PlayerNickDisplayManager();

    public TablistManager(ConfigUtil configUtil) {
        if (configUtil == null) {
            throw new IllegalArgumentException("ConfigUtil não pode ser nulo!");
        }
        this.configUtil = configUtil;

        PrefixCache prefixCache = new PrefixCache();
        this.prefixProvider = new ConfigPrefixProvider(configUtil, prefixCache);
    }

    public void updatePlayerTablist(Player player) {
        if (player == null) return;

        String prefix = prefixProvider.getPrefix(player);
        if (prefix == null || prefix.isEmpty()) return;

        int onlinePlayers = Bukkit.getOnlinePlayers().size();
        int maxPlayers = Bukkit.getMaxPlayers();

        Map<String, String> placeholders =
                placeholderService.getPlaceholders(player, prefix, onlinePlayers, maxPlayers);

        String header = ConfigUtil.formatWithPlaceholders(configUtil.get("tablist.header", String.class), placeholders);
        String footer = ConfigUtil.formatWithPlaceholders(configUtil.get("tablist.footer", String.class), placeholders);

        player.sendPlayerListHeaderAndFooter(ColorUtils.colorize(header), ColorUtils.colorize(footer));

        playerNickDisplayManager.updatePlayerDisplay(player, prefix);
    }

    public void reloadTablistForAllPlayers() {
        Bukkit.getOnlinePlayers().forEach(player -> CompletableFuture.runAsync(() -> updatePlayerTablist(player)));
    }
}
