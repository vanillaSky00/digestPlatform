package io.github.vanillasky.digestplatform.adapters.out.persistence.repository;

import feign.Param;
import io.github.vanillasky.digestplatform.adapters.out.persistence.entity.ArticleEntity;
import io.github.vanillasky.digestplatform.domain.model.SourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Query(
            value = """
      WITH ranked AS (
        SELECT a.*,
               ROW_NUMBER() OVER (
                 PARTITION BY a.source
                 ORDER BY a.published_at DESC, a.id DESC
               ) AS rn
        FROM articles a
        WHERE a.source IN (:sources)
      )
      SELECT *
      FROM ranked
      WHERE rn <= :n
      ORDER BY source, published_at DESC, id DESC
      """,
            nativeQuery = true
    )
    List<ArticleEntity> findLatestOfEach(
            @Param("sources") List<String> sources,
            @Param("n") int n
    );
}