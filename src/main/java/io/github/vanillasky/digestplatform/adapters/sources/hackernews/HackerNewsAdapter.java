package io.github.vanillasky.digestplatform.adapters.sources.hackernews;

import io.github.vanillasky.digestplatform.domain.model.FeedItem;
import io.github.vanillasky.digestplatform.domain.ports.out.ContentSourcePort;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class HackerNewsAdapter implements ContentSourcePort {

    private final HackerNewsClient client;

    public HackerNewsAdapter(HackerNewsClient client) {
        this.client = client;
    }

    @Override
    public String sourceName() {
        return "hackernews";
    }

    @Override
    public List<FeedItem> top(int limit) {
        long[] ids = client.topStories();
        return Arrays.stream(ids).limit(limit)
                .mapToObj(client::item)
                .map(HnMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<FeedItem> byId(String id) {
        try {
            HnItem i = client.item(Long.parseLong(id));
            return Optional.ofNullable(i).map(HnMapper::toDomain);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    // Further api
}
