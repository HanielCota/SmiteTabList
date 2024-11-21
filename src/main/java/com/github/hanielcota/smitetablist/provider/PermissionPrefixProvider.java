package com.github.hanielcota.smitetablist.provider;

import org.bukkit.entity.Player;

public interface PermissionPrefixProvider {

    String getPrefix(Player player);
}
