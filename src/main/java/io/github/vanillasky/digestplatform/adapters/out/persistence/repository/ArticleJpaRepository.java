package io.github.vanillasky.digestplatform.adapters.out.persistence.repository;

import feign.Param;
import io.github.vanillasky.digestplatform.adapters.out.persistence.entity.ArticleEntity;
import io.github.vanillasky.digestplatform.domain.model.SourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArticleJpaRepository extends JpaRepository<ArticleEntity, UUID> {

    @Query("""
        SELECT a FROM ArticleEntity a
            WHERE a.source = :source
                AND a.externalId = :externalId
    """)
    Optional<ArticleEntity> findBySourceIdAndExternalId(
            @Param("source") SourceType source,
            @Param("externalId") String externalId);

    @Query("""
        SELECT a FROM ArticleEntity a
            WHERE a.urlHashHex = :urlHashHex
    """)
    Optional<ArticleEntity> findByUrlHashHex(
            @Param("urlHashHex") String urlHashHex);
}
