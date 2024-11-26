package com.github.hanielcota.smitetablist.services;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class TablistPlaceholderService {

    private final PlaceholderService placeholderService = new PlaceholderService();

    public Map<String, String> getPlaceholders(Player player, String prefix, int onlinePlayers, int maxPlayers) {
        return placeholderService.createPlaceholders(player, prefix, onlinePlayers, maxPlayers);
    }
}
