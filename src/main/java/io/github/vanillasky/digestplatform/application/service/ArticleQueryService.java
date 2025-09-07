package io.github.vanillasky.digestplatform.application.service;

import io.github.vanillasky.digestplatform.adapters.out.persistence.ArticleMapper;
import io.github.vanillasky.digestplatform.adapters.out.persistence.entity.ArticleEntity;
import io.github.vanillasky.digestplatform.adapters.out.persistence.repository.ArticleJpaRepository;
import io.github.vanillasky.digestplatform.domain.model.FeedItem;
import io.github.vanillasky.digestplatform.domain.model.SourceType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleQueryService {

    private final ArticleJpaRepository articleJpaRepository;

    public ArticleQueryService(
            ArticleJpaRepository articleJpaRepository
    ) {
        this.articleJpaRepository = articleJpaRepository;
    }

    public void saveAllFeedItem(List<FeedItem> feedItems) {
        articleJpaRepository.saveAll(ArticleMapper.toNewEntityList(feedItems));
    }

    public List<ArticleEntity> getAllArticles() {
        return articleJpaRepository.findAll();
    }

    public List<ArticleEntity> latestOfEach(List<String> sourceTypes, int n) {
        return articleJpaRepository.findLatestOfEach(sourceTypes, n);
    }

}
