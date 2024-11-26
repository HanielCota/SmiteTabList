package com.github.hanielcota.smitetablist.utils;

import java.util.Map;

public class PlaceholderUtils {

    public static String replacePlaceholders(String template, Map<String, String> placeholders) {
        if (template == null || placeholders == null) {
            throw new IllegalArgumentException("Template e placeholders não podem ser nulos.");
        }

        for (Map.Entry<String, String> entry : placeholders.entrySet()) {

            if (entry.getKey() != null && entry.getValue() != null) {
                template = template.replace(entry.getKey(), entry.getValue());
            }
        }

        return template;
    }
}
