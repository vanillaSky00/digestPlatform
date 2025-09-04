package io.github.vanillasky.digestplatform.adapters.out.persistence.entity;

import io.github.vanillasky.digestplatform.domain.model.SourceType;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "articles",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_articles_source_external", columnNames = {"source", "external_id"}),
                @UniqueConstraint(name = "uk_articles_url_hash_hex", columnNames = {"url_hash_hex"})
        },
        indexes = {
                @Index(name = "idx_articles_published_at", columnList = "published_at")
        }
)
public class ArticleEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private SourceType source;

    @Column(name = "external_id", length = 128)
    private String externalId;

    @Column(nullable = false, columnDefinition = "text")
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String url;

    //find digest content for given url
    @Column(name = "url_hash_hex", length = 64, nullable = false)
    private String urlHashHex;

    @Column(name = "author")
    private String author;

    @Column(name = "published_at", columnDefinition = "timestamptz")
    private Instant publishedAt;

    @Column(name = "first_seen_at", nullable = false, columnDefinition = "timestamptz")
    private Instant firstSeenAt = Instant.now();

    @PrePersist
    void prePersist() {
        if (firstSeenAt == null) firstSeenAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public SourceType getSource() {
        return source;
    }

    public void setSource(SourceType source) {
        this.source = source;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlHashHex() {
        return urlHashHex;
    }

    public void setUrlHashHex(String urlHash) {
        this.urlHashHex = urlHashHex;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Instant getFirstSeenAt() {
        return firstSeenAt;
    }

    public void setFirstSeenAt(Instant firstSeenAt) {
        this.firstSeenAt = firstSeenAt;
    }
}