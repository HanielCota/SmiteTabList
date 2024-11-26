package com.github.hanielcota.smitetablist.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {

    private static final LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.legacyAmpersand();
    private final ItemStack itemStack;

    public ItemBuilder(Material material) {
        if (material == null) {
            throw new IllegalArgumentException("Material cannot be null");
        }
        this.itemStack = new ItemStack(material);
    }

    public ItemBuilder(ItemStack itemStack) {
        if (itemStack == null) {
            throw new IllegalArgumentException("ItemStack cannot be null");
        }
        this.itemStack = itemStack.clone();
    }

    public ItemBuilder setAmount(int amount) {
        if (amount < 1 || amount > 64) {
            throw new IllegalArgumentException("Amount must be between 1 and 64");
        }
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return this;
        }
        updateItemMeta(meta -> meta.displayName(parseComponent(name)));
        return this;
    }

    public ItemBuilder addLore(String... lines) {
        if (lines == null || lines.length == 0) {
            return this;
        }

        updateItemMeta(meta -> {
            List<Component> lore = meta.lore();
            if (lore == null) {
                lore = new ArrayList<>();
            }

            for (String line : lines) {
                if (line == null || line.trim().isEmpty()) {
                    continue;
                }
                lore.add(parseComponent(line));
            }

            meta.lore(lore);
        });

        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        if (lore == null || lore.isEmpty()) {
            return this;
        }

        updateItemMeta(meta -> {
            List<Component> formattedLore = new ArrayList<>();
            for (String line : lore) {
                formattedLore.add(parseComponent(line));
            }
            meta.lore(formattedLore);
        });
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        updateItemMeta(meta -> meta.setUnbreakable(unbreakable));
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        if (enchantment == null) {
            return this;
        }
        updateItemMeta(meta -> meta.addEnchant(enchantment, level, true));
        return this;
    }

    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
        if (enchantments == null || enchantments.isEmpty()) {
            return this;
        }
        updateItemMeta(meta -> enchantments.forEach((enchantment, level) -> meta.addEnchant(enchantment, level, true)));
        return this;
    }

    public ItemBuilder addItemFlags(ItemFlag... flags) {
        if (flags == null || flags.length == 0) {
            return this;
        }
        updateItemMeta(meta -> meta.addItemFlags(flags));
        return this;
    }

    public ItemBuilder setCustomModelData(Integer data) {
        if (data != null && data < 0) {
            throw new IllegalArgumentException("CustomModelData must be null or positive");
        }
        updateItemMeta(meta -> meta.setCustomModelData(data));
        return this;
    }

    public ItemStack build() {
        return itemStack.clone();
    }

    private void updateItemMeta(Consumer<ItemMeta> updater) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) {
            return;
        }

        updater.accept(meta);
        itemStack.setItemMeta(meta);
    }

    private Component parseComponent(String text) {
        return LEGACY_SERIALIZER.deserialize(text).decoration(TextDecoration.ITALIC, false);
    }
}
