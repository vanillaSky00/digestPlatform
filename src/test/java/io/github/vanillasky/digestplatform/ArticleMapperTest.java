package io.github.vanillasky.digestplatform;

import io.github.vanillasky.digestplatform.adapters.out.persistence.ArticleMapper;
import io.github.vanillasky.digestplatform.adapters.out.persistence.entity.ArticleEntity;
import io.github.vanillasky.digestplatform.adapters.out.persistence.utils.Hashes;
import io.github.vanillasky.digestplatform.adapters.out.persistence.utils.Urls;
import io.github.vanillasky.digestplatform.domain.model.FeedItem;
import io.github.vanillasky.digestplatform.domain.model.SourceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import static org.assertj.core.api.Assertions.assertThat;


public class ArticleMapperTest {


    @Test
    @DisplayName("Test if the articleMapper work")
    void testIfTheArticleWork(){

        FeedItem f = new FeedItem(
                "testing",             // id
                SourceType.HN,            // source
                "testingTitle",           // title
                "http://test.com",        // url
                "vanillasky",             // author
                23,                       // score
                Instant.parse("2025-01-01T00:00:00Z") // stable time for tests
        );

        ArticleEntity a = ArticleMapper.toNewEntity(f);
        assertThat(a.getTitle()).isEqualTo("testingTitle");
        assertThat(a.getSource()).isEqualTo(f.source());
        assertThat(a.getExternalId()).isEqualTo(f.id());
        assertThat(a.getTitle()).isEqualTo(f.title());
        assertThat(a.getUrl()).isEqualTo(f.url());
        assertThat(a.getAuthor()).isEqualTo(f.author());
        assertThat(a.getPublishedAt()).isEqualTo(f.publishedAt());

        // derived field
        assertThat(a.getUrlHashHex())
                .isEqualTo(Hashes.sha256Hex(Urls.normalize(f.url())));
    }
}
