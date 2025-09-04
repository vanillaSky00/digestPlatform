package io.github.vanillasky.digestplatform.app.repository;

import io.github.vanillasky.digestplatform.domain.model.SourceType;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "articles",
        uniqueConstraints = {

        }
)
public class Article {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private SourceType source;

    @Column(name = "external_id", length = 128)
    private String externalId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String url;

    @Column(name = "url_hash", nullable = false)
    @Lob
    private byte[] urlHash;

    @Column(name = "author")
    private String author;

    @Column(name = "published_at")
    private String publishedAt;

    @Column(name = "first_seen_at", nullable = false)
    private Instant firstSeenAt = Instant.now();

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

    public byte[] getUrlHash() {
        return urlHash;
    }

    public void setUrlHash(byte[] urlHash) {
        this.urlHash = urlHash;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Instant getFirstSeenAt() {
        return firstSeenAt;
    }

    public void setFirstSeenAt(Instant firstSeenAt) {
        this.firstSeenAt = firstSeenAt;
    }
}