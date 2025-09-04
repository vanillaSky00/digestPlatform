package io.github.vanillasky.digestplatform.adapters.out.persistence.utils;

import java.net.URI;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public final class Urls {
    private Urls() {}
    public static String normalize(String raw) {
        try {
            var u = new URI(raw.trim());
            var scheme = (u.getScheme() == null ? "http" : u.getScheme()).toLowerCase(Locale.ROOT);
            var host = (u.getHost() == null ? "" : u.getHost()).toLowerCase(Locale.ROOT);
            var path = u.getPath() == null ? "" : u.getPath();
            var query = u.getQuery();
            if (query != null) {
                query = Arrays.stream(query.split("&"))
                        .filter(p -> {
                            var k = p.split("=", 2)[0].toLowerCase(Locale.ROOT);
                            return !k.startsWith("utm_") && !k.equals("fbclid");
                        })
                        .sorted()
                        .collect(Collectors.joining("&"));
            }
            var sb = new StringBuilder().append(scheme).append("://").append(host).append(path);
            if (query != null && !query.isEmpty()) sb.append('?').append(query);
            return sb.toString();
        } catch (Exception e) {
            return raw.trim();
        }
    }
}
