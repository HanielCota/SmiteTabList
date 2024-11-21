package com.github.hanielcota.smitetablist.utils;

import java.util.Map;

public class PlaceholderUtils {

    public static String replacePlaceholders(String template, Map<String, String> placeholders) {
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            template = template.replace(entry.getKey(), entry.getValue());
        }
        return template;
    }
}
