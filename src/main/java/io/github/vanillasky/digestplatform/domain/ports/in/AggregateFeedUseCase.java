package io.github.vanillasky.digestplatform.domain.ports.in;

import io.github.vanillasky.digestplatform.domain.model.FeedItem;

import java.util.List;

// Define what the app layer can ask the domain to do
public interface AggregateFeedUseCase {
    List<FeedItem> topAcrossSources(int limitPerSource);
}
