package com.github.hanielcota.smitetablist.manager;

import com.github.hanielcota.smitetablist.utils.NameUtils;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

@RequiredArgsConstructor
public class PlayerNickDisplayManager {

    private static final String TEAM_PREFIX = "team_";

    public void updatePlayerDisplay(Player player, String prefix) {
        if (player == null || prefix == null) {
            return;
        }

        updatePlayerTeam(player, prefix);
        updatePlayerTabName(player, prefix);
    }

    private void updatePlayerTeam(Player player, String prefix) {
        String teamName = TEAM_PREFIX + player.getUniqueId();
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.getTeam(teamName);

        if (team == null) {
            team = scoreboard.registerNewTeam(teamName);
        }

        team.prefix(Component.text(prefix));
        team.addEntry(player.getName());
    }

    private void updatePlayerTabName(Player player, String prefix) {
        String tabName = NameUtils.createTabName(prefix, player.getName());
        player.playerListName(Component.text(tabName));
    }
}
