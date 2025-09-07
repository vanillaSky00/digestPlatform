package io.github.vanillasky.digestplatform.application.service;

import io.github.vanillasky.digestplatform.adapters.out.persistence.ArticleMapper;
import io.github.vanillasky.digestplatform.adapters.out.persistence.repository.ArticleJpaRepository;
import io.github.vanillasky.digestplatform.domain.model.FeedItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleIngestionService {

    private final ArticleJpaRepository articleJpaRepository;

    public ArticleIngestionService(
            ArticleJpaRepository articleJpaRepository
    ) {
        this.articleJpaRepository = articleJpaRepository;
    }

    public void saveAllFeedItem(List<FeedItem> feedItems) {
        articleJpaRepository.saveAll(ArticleMapper.toNewEntityList(feedItems));
    }

    public List<?> getAllArticles() {
        return articleJpaRepository.findAll();
    }

}
