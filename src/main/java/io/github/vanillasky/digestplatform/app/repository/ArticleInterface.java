package io.github.vanillasky.digestplatform.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ArticleInterface extends JpaRepository<Article, UUID> {

    @Query("SELECT * FROM article WHERE sourceId = :sourceId, externalId =:externalId")
    Optional<Article> findBySourceIdAndExternalId(UUID sourceId, UUID externalId);

    @Query("SELECT * FROM article WHERE urlHash = :urlHash")
    Optional<Article> findByUrlHash(byte[] urlHash);
}
