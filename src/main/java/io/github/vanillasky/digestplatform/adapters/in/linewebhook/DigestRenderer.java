package io.github.vanillasky.digestplatform.adapters.in.linewebhook;

import com.linecorp.bot.messaging.model.Message;
import com.linecorp.bot.messaging.model.TextMessage;
import io.github.vanillasky.digestplatform.adapters.out.persistence.entity.ArticleEntity;

import java.util.ArrayList;
import java.util.List;

public class DigestRenderer {
    private static final int MAX_TEXT = 5000;    // LINE limit guidance
    private static final int SAFE_CHUNK = 4500;  // leave headroom; UTF-16 counts

    public static List<Message> toTextMessages(List<ArticleEntity> items) {
        if (items.isEmpty()) {
            return List.of(
                    (Message) new TextMessage("Meow ate all the articles. There was no left")
            );
        }

        var sb = new StringBuilder("📚 New Articles：\n");
        int idx = 1;
        for (var a : items) {
            sb.append(idx++)
                .append(". ")
                .append(a.getTitle())
                .append("\n")
                .append(a.getUrl()).append("\n\n");
        }

        var chunks = splitUtf16Safely(sb.toString());

        // LINE allows up to 5 message objects in one reply
        return chunks.stream()
                .map(String::strip)             // trim both ends
                .filter(s -> !s.isEmpty())      // <— important
                .limit(5)                       // LINE allows up to 5 messages per reply
                .map(s -> (Message) new TextMessage(s))
                .toList();
    }

    private static List<String> splitUtf16Safely(String text) {
        List<String> parts = new ArrayList<>();
        int n = text.length(), start = 0;

        while (start < n) {
            int end = Math.min(n, start + SAFE_CHUNK);

            // Prefer breaking at a newline
            int cut = text.lastIndexOf('\n', end);
            if (cut < start) cut = end; // no newline in window → hard cut

            String part = text.substring(start, cut).stripTrailing();
            if (!part.isBlank()) parts.add(part);

            start = cut;
            // Skip a single newline so we don't create an empty next chunk
            if (start < n && text.charAt(start) == '\n') start++;
        }
        return parts;
    }
}

// Message is interface they cannot be newed, but we can new TextMessage and uppercast to it