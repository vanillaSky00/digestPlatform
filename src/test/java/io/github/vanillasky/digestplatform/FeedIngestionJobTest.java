package io.github.vanillasky.digestplatform;

import io.github.vanillasky.digestplatform.application.scheduler.FeedIngestionJob;
import io.github.vanillasky.digestplatform.application.service.ArticleService;
import io.github.vanillasky.digestplatform.domain.model.FeedItem;
import io.github.vanillasky.digestplatform.domain.model.SourceType;
import io.github.vanillasky.digestplatform.domain.ports.in.AggregateFeedUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class FeedIngestionJobTest {
    @Mock
    AggregateFeedUseCase aggregateFeedUseCase;
    @Mock
    ArticleService articleService;

    //@InjectMocks
    FeedIngestionJob feedIngestionJob;

    @Test
    @DisplayName("Test cron job : calling external api to get data and save to db")
    public void externalApiCallAndSaveHappyFlow() {

        var mockItems = List.of(
                new FeedItem("id1", SourceType.HN, "Title 1", "http://t1", "author", 100, Instant.now()),
                new FeedItem("id2", SourceType.GUARDIAN, "Title 2", "http://t2", "author2", 50, Instant.now())
        );

        given(aggregateFeedUseCase.topAcrossSources(2))
                .willReturn(mockItems);

        // act
        FeedIngestionJob feedIngestionJob = new FeedIngestionJob(aggregateFeedUseCase, articleService);
        feedIngestionJob.run();

        //assert
        verify(aggregateFeedUseCase).topAcrossSources(2);
        verify(articleService).saveAllFeedItem(mockItems);
        verifyNoMoreInteractions(articleService, aggregateFeedUseCase);
    }
}
