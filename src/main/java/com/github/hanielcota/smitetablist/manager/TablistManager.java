package com.github.hanielcota.smitetablist.manager;

import com.github.hanielcota.smitetablist.provider.PermissionPrefixProvider;
import com.github.hanielcota.smitetablist.utils.ColorUtils;
import com.github.hanielcota.smitetablist.utils.ConfigUtil;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

@RequiredArgsConstructor
public class TablistManager {

    private final PermissionPrefixProvider prefixProvider;
    private final ConfigUtil configUtil;

    private static final String TEAM_PREFIX = "team_";

    public void updatePlayerTablist(Player player) {
        if (player == null) {
            return;
        }

        String prefix = prefixProvider.getPrefix(player);
        if (prefix == null || prefix.isEmpty()) {
            return;
        }

        int onlinePlayers = Bukkit.getOnlinePlayers().size();
        int maxPlayers = Bukkit.getMaxPlayers();

        String rawHeader = configUtil.get("tablist.header", String.class);
        String rawFooter = configUtil.get("tablist.footer", String.class);

        String header = rawHeader != null ? replacePlaceholders(rawHeader, player, prefix, onlinePlayers, maxPlayers) : "";
        String footer = rawFooter != null ? replacePlaceholders(rawFooter, player, prefix, onlinePlayers, maxPlayers) : "";

        Component headerComponent = ColorUtils.colorize(header);
        Component footerComponent = ColorUtils.colorize(footer);

        player.sendPlayerListHeaderAndFooter(headerComponent, footerComponent);

        updatePlayerNickAndTab(player, prefix);
    }

    public void reloadTablistForAllPlayers() {
        Bukkit.getOnlinePlayers().forEach(this::updatePlayerTablist);
    }

    /**
     * Atualiza o prefixo no tab e no nick.
     *
     * @param player Jogador a ser atualizado.
     * @param prefix Prefixo a ser aplicado.
     */
    private void updatePlayerNickAndTab(Player player, String prefix) {
        String teamName = TEAM_PREFIX + player.getUniqueId();
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.getTeam(teamName);

        if (team == null) {
            team = scoreboard.registerNewTeam(teamName);
        }

        team.prefix(Component.text(prefix));
        team.addEntry(player.getName());

        Component tabName = createTabName(prefix, player.getName());
        player.playerListName(tabName);
    }

    private String replacePlaceholders(String template, Player player, String prefix, int onlinePlayers, int maxPlayers) {
        return template.replace("%prefix%", prefix)
                .replace("%player_name%", player.getName())
                .replace("%online%", String.valueOf(onlinePlayers))
                .replace("%max%", String.valueOf(maxPlayers));
    }

    private Component createTabName(String prefix, String playerName) {
        String tabName = prefix + playerName;
        return Component.text(tabName.length() > 16 ? tabName.substring(0, 16) : tabName);
    }
}
