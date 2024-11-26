package com.github.hanielcota.smitetablist.services;

import java.util.Map;
import org.bukkit.entity.Player;

public class PlaceholderService {

    public Map<String, String> createPlaceholders(Player player, String prefix, int onlinePlayers, int maxPlayers) {
        if (player == null || prefix == null) {
            throw new IllegalArgumentException("Player e prefixo não podem ser nulos.");
        }

        if (onlinePlayers < 0 || maxPlayers < 0) {
            throw new IllegalArgumentException("Valores de jogadores online ou máximos não podem ser negativos.");
        }

        return Map.of(
                "%prefix%", prefix,
                "%player_name%", player.getName(),
                "%online%", String.valueOf(onlinePlayers),
                "%max%", String.valueOf(maxPlayers));
    }
}
