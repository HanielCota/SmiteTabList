package com.github.hanielcota.smitetablist.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class ColorUtils {

    // Serializador para converter códigos de cor (& ou §) para componentes Adventure
    private static final LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.builder()
            .character('&') // Define o uso de '&' como prefixo para cores
            .hexColors() // Suporte para cores hexadecimais
            .useUnusualXRepeatedCharacterHexFormat() // Suporte para formato &#rrggbb
            .build();

    /**
     * Converte texto com cores (& ou §) para um componente Adventure.
     *
     * @param text O texto com códigos de cor (& ou §).
     * @return O componente formatado.
     */
    public static Component colorize(String text) {
        if (text == null || text.isEmpty()) {
            return Component.empty();
        }

        // Substitui § por & para garantir compatibilidade
        text = text.replace('§', '&');

        // Converte o texto para um componente Adventure
        return LEGACY_SERIALIZER.deserialize(text);
    }

    /**
     * Remove códigos de cor de um texto.
     *
     * @param text O texto com códigos de cor.
     * @return O texto sem formatação.
     */
    public static String stripColors(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        // Remove todos os códigos de cor usando regex
        return text.replaceAll("(?i)([&§])[0-9a-fk-orx]", "");
    }
}
