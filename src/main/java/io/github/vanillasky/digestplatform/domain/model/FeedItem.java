package io.github.vanillasky.digestplatform.domain.model;

/**
 * normalized representation of a content
 * hacker news -> map HnItem into FeedItem
 * youtube -> map YtItem into FeedItem
 * ... and so on
 */
public record FeedItem(
        String id,
        SourceType source,
        String title,
        String url,
        String author,
        Integer score,
        java.time.Instant publishedAt
) {}


