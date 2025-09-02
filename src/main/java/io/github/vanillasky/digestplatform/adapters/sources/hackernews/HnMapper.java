package io.github.vanillasky.digestplatform.adapters.sources.hackernews;

import io.github.vanillasky.digestplatform.domain.model.FeedItem;
import io.github.vanillasky.digestplatform.domain.model.SourceType;

import java.time.Instant;

public class HnMapper {
    static FeedItem toDomain(HnItem i) {
        return new FeedItem(
                String.valueOf((i.id())),
                SourceType.HN,
                i.title(),
                i.url(),
                i.by(),
                i.score(),
                i.time() == null ? null : Instant.ofEpochSecond(i.time())
        );
    }

    private HnMapper() {}
}
