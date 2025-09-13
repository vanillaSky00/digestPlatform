package io.github.vanillasky.digestplatform.domain.service;

import io.github.vanillasky.digestplatform.domain.model.FeedItem;
import io.github.vanillasky.digestplatform.domain.ports.in.AggregateFeedUseCase;
import io.github.vanillasky.digestplatform.domain.ports.out.ContentSourcePort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

public class AggregateFeedService implements AggregateFeedUseCase {
    private final List<ContentSourcePort> sources;

    public AggregateFeedService(List<ContentSourcePort> sources) {
        this.sources = sources;
    }


    @Override
    public List<FeedItem> topAcrossSources(int limitPerSource) {
        return sources.stream()
                .flatMap(s -> s.top(limitPerSource).stream())
                .sorted(Comparator.comparing(FeedItem::publishedAt).reversed())
                .toList();
    }
}


