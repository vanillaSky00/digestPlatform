package io.github.vanillasky.digestplatform;

import io.github.vanillasky.digestplatform.application.service.ArticleIngestionService;
import io.github.vanillasky.digestplatform.domain.ports.in.AggregateFeedUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DigestApplication {

    private static final Logger log = LoggerFactory.getLogger(DigestApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
            SpringApplication.run(DigestApplication.class, args);

        var useCase = ctx.getBean(AggregateFeedUseCase.class);

        ArticleIngestionService articleIngestionService = ctx.getBean(ArticleIngestionService.class);
        articleIngestionService.saveAllFeedItem(useCase.topAcrossSources(10));
//        useCase.topAcrossSources(10).forEach(i ->
//                log.info("[USECASE] {} | {} | {} | {} | {} | {} | {}", i.id(), i.source(), i.title(), i.url(), i.author(), i.score(), i.publishedAt())
//        );


//        var hn = ctx.getBean(HackerNewsClient.class);
//        long[] ids = hn.topStories();
//        log.info("[FEIGN] topstories count={}", ids.length);



    }

}

//make a profile to let yml be not ambiguous
// docker compose -f docker-compose.yml up -d db
