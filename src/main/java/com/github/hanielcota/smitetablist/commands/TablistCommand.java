package com.github.hanielcota.smitetablist.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import com.github.hanielcota.smitetablist.manager.TablistManager;
import com.github.hanielcota.smitetablist.utils.ColorUtils;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

@RequiredArgsConstructor
@CommandAlias("tablist")
public class TablistCommand extends BaseCommand {

    private final TablistManager tablistManager;
    private final Plugin plugin;

    @Subcommand("reload")
    @CommandPermission("tablist.reload")
    public void reload(CommandSender sender) {

        tablistManager.reloadTablistForAllPlayers();
        sender.sendMessage(ColorUtils.colorize("&aTablist recarregada para todos os jogadores!"));
    }
}
