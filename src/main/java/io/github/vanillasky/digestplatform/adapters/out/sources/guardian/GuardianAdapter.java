package io.github.vanillasky.digestplatform.adapters.out.sources.guardian;

import io.github.vanillasky.digestplatform.domain.model.FeedItem;
import io.github.vanillasky.digestplatform.domain.ports.out.ContentSourcePort;

import java.util.List;
import java.util.Optional;

public class GuardianAdapter implements ContentSourcePort {

    private final GuardianClient client;
    private static final String DEFAULT_FIELDS =
            "bodyText,trailText,thumbnail,byline";

    public GuardianAdapter(GuardianClient client) {
        this.client = client;
    }

    @Override
    public String sourceName() {
        return "guardian";
    }

    @Override
    public List<FeedItem> top(int limit) {
        int pageSize = Math.min(Math.max(limit, 1), 200);
        var dto = client.search(
                "",
                "politics/politics",
                "2024-01-01",
                null,
                null,
                1,
                pageSize,
                DEFAULT_FIELDS,
                null
        );
        if (dto == null || dto.innerResponse == null || dto.innerResponse.results == null) return List.of();
        return dto.innerResponse.results.stream()
                .limit(limit)
                .map(GuardianMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<FeedItem> byId(String id) {
        return Optional.empty();
    }
}
