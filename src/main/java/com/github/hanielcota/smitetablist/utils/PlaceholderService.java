package com.github.hanielcota.smitetablist.utils;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;

public class PlaceholderService {

    public Map<String, String> createPlaceholders(Player player, String prefix, int onlinePlayers, int maxPlayers) {
        Map<String, String> placeholders = new HashMap<>();

        placeholders.put("%prefix%", prefix);
        placeholders.put("%player_name%", player.getName());
        placeholders.put("%online%", String.valueOf(onlinePlayers));
        placeholders.put("%max%", String.valueOf(maxPlayers));
        return placeholders;
    }
}
