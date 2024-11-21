package com.github.hanielcota.smitetablist.manager;

import com.github.hanielcota.smitetablist.provider.PermissionPrefixProvider;
import com.github.hanielcota.smitetablist.utils.ColorUtils;
import com.github.hanielcota.smitetablist.utils.ConfigUtil;
import com.github.hanielcota.smitetablist.utils.PlaceholderService;
import com.github.hanielcota.smitetablist.utils.PlaceholderUtils;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class TablistManager {

    private final PermissionPrefixProvider prefixProvider;
    private final ConfigUtil configUtil;
    private final PlayerNickDisplayManager playerNickDisplayManager = new PlayerNickDisplayManager();
    private final PlaceholderService placeholderService = new PlaceholderService();

    public void updatePlayerTablist(Player player) {
        if (player == null) return;

        String prefix = prefixProvider.getPrefix(player);
        if (prefix == null || prefix.isEmpty()) return;

        int onlinePlayers = Bukkit.getOnlinePlayers().size();
        int maxPlayers = Bukkit.getMaxPlayers();

        // Obtém o mapa de placeholders do PlaceholderService
        Map<String, String> placeholders = placeholderService.createPlaceholders(player, prefix, onlinePlayers, maxPlayers);

        // Substitui os placeholders no cabeçalho e rodapé
        String rawHeader = configUtil.get("tablist.header", String.class);
        String rawFooter = configUtil.get("tablist.footer", String.class);

        String header = rawHeader != null ? PlaceholderUtils.replacePlaceholders(rawHeader, placeholders) : "";
        String footer = rawFooter != null ? PlaceholderUtils.replacePlaceholders(rawFooter, placeholders) : "";

        // Envia o cabeçalho e rodapé formatados ao jogador
        Component headerComponent = ColorUtils.colorize(header);
        Component footerComponent = ColorUtils.colorize(footer);
        player.sendPlayerListHeaderAndFooter(headerComponent, footerComponent);

        // Atualiza o display do jogador
        playerNickDisplayManager.updatePlayerDisplay(player, prefix);
    }

    public void reloadTablistForAllPlayers() {
        Bukkit.getOnlinePlayers().forEach(this::updatePlayerTablist);
    }
}
