package io.github.vanillasky.digestplatform.domain.ports.out;

import io.github.vanillasky.digestplatform.domain.model.FeedItem;

import java.util.List;
import java.util.Optional;

// Define what the domain need from the outside world
// Adapters (HN, YT, IG) will implement this interface.

public interface ContentSourcePort {
    String sourceName();
    List<FeedItem> top(int limit);
    Optional<FeedItem> byId(String id);
}
