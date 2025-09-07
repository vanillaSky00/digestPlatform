package io.github.vanillasky.digestplatform.adapters.out.persistence;

import io.github.vanillasky.digestplatform.adapters.out.persistence.entity.ArticleEntity;
import io.github.vanillasky.digestplatform.adapters.out.persistence.utils.Hashes;
import io.github.vanillasky.digestplatform.adapters.out.persistence.utils.Urls;
import io.github.vanillasky.digestplatform.domain.model.FeedItem;

import java.time.Instant;
import java.util.List;

//map FeedItem to ArticleEntity
public class ArticleMapper {
    static public ArticleEntity toNewEntity(FeedItem f) {
        var a = new ArticleEntity();
        a.setSource(f.source());
        a.setExternalId(f.id());
        a.setTitle(f.title());
        a.setUrl(f.url());
        a.setUrlHashHex(Hashes.sha256Hex(Urls.normalize(f.url())));
        a.setAuthor(f.author());
        a.setPublishedAt(f.publishedAt());
        a.setFirstSeenAt(Instant.now());
        return a;
    }

    static public List<ArticleEntity> toNewEntityList(List<FeedItem> fs) {
        return fs.stream()
                .map(ArticleMapper::toNewEntity)
                .toList();
    }

    private ArticleMapper() {}
}
