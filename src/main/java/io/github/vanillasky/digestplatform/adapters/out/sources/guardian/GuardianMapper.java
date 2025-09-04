package io.github.vanillasky.digestplatform.adapters.out.sources.guardian;

import io.github.vanillasky.digestplatform.domain.model.FeedItem;
import io.github.vanillasky.digestplatform.domain.model.SourceType;

import java.time.Instant;

public class GuardianMapper {
    private static Instant parseIso(String s) {
        return (s == null) ? null : java.time.OffsetDateTime.parse(s).toInstant();
    }

    static FeedItem toDomain(GuardianSearchItem.Result r) {
        return new FeedItem(
                r.id,
                SourceType.GUARDIAN,
                r.webTitle,
                r.webUrl,
                null,
                null,
                parseIso(r.webPublicationDate)
        );
    }

    private GuardianMapper() {}
}
