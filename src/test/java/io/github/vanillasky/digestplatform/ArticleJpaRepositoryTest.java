package io.github.vanillasky.digestplatform;

import io.github.vanillasky.digestplatform.adapters.out.persistence.ArticleMapper;
import io.github.vanillasky.digestplatform.adapters.out.persistence.entity.ArticleEntity;
import io.github.vanillasky.digestplatform.adapters.out.persistence.repository.ArticleJpaRepository;
import io.github.vanillasky.digestplatform.adapters.out.persistence.utils.Hashes;
import io.github.vanillasky.digestplatform.adapters.out.persistence.utils.Urls;
import io.github.vanillasky.digestplatform.application.service.ArticleIngestionService;
import io.github.vanillasky.digestplatform.domain.model.FeedItem;
import io.github.vanillasky.digestplatform.domain.model.SourceType;
import io.github.vanillasky.digestplatform.domain.ports.in.AggregateFeedUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
class ArticleJpaRepositoryTest {

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16")
                    .withDatabaseName("digest")
                    .withUsername("digest")
                    .withPassword("digest");

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry r) {
        r.add("spring.datasource.url", postgres::getJdbcUrl);
        r.add("spring.datasource.username", postgres::getUsername);
        r.add("spring.datasource.password", postgres::getPassword);
        r.add("spring.flyway.enabled", () -> "true");
        r.add("spring.jpa.hibernate.ddl-auto", () -> "none");
    }

    @Autowired
    private ArticleJpaRepository articleJpaRepository; // no constructor

    @Test
    void saveAndFindByUrlHashHex() {
        var a = new ArticleEntity();
        a.setSource(SourceType.HN);
        a.setExternalId("123");
        a.setTitle("Hello");
        a.setUrl("https://example.com/x?utm=z");
        a.setUrlHashHex("0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef");
        a.setAuthor("vanillasky");
        a.setPublishedAt(Instant.now());
        var saved = articleJpaRepository.save(a);

        assertThat(saved.getId()).isNotNull();
        assertThat(
                articleJpaRepository.findByUrlHashHex(a.getUrlHashHex())
        ).isPresent();
    }
}
