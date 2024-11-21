package com.github.hanielcota.smitetablist.listener;

import com.github.hanielcota.smitetablist.manager.TablistManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@RequiredArgsConstructor
public class PlayerJoinListener implements Listener {

    private final TablistManager tablistManager;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        tablistManager.updatePlayerTablist(event.getPlayer());
    }
}
