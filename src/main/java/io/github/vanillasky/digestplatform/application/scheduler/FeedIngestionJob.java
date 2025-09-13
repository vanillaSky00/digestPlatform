package io.github.vanillasky.digestplatform.application.scheduler;

import io.github.vanillasky.digestplatform.application.service.ArticleService;
import io.github.vanillasky.digestplatform.domain.ports.in.AggregateFeedUseCase;
import io.github.vanillasky.digestplatform.domain.service.AggregateFeedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component

public class FeedIngestionJob {
    private static final Logger log = LoggerFactory.getLogger(FeedIngestionJob.class);
    private final AggregateFeedUseCase useCase;
    private final ArticleService articleService;

    public FeedIngestionJob(
            AggregateFeedUseCase useCase,
            ArticleService articleService) {
        this.articleService = articleService;
        this.useCase = useCase;
    }

    //@Value("${source.scheduled.batch-size}")
    private int batchSize = 2;

    @Scheduled(cron="${source.scheduled.cron}", zone="${source.scheduled.zone}")
    public void run(){
        log.info("Starting FeedIngestionJob");
        var sources = useCase.topAcrossSources(batchSize);//call domain -> inside domain would call feign
        log.info("Ending FeedIngestionJob");
        log.info("Saving FeedIngestionJob");
        articleService.saveAllFeedItem(sources);
        log.info("Finishing FeedIngestionJob");
    }
}
