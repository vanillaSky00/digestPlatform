package io.github.vanillasky.digestplatform.app;

import io.github.vanillasky.digestplatform.adapters.sources.guardian.GuardianClient;
import io.github.vanillasky.digestplatform.adapters.sources.guardian.GuardianSearchItem;
import io.github.vanillasky.digestplatform.adapters.sources.hackernews.HackerNewsClient;
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
        useCase.topAcrossSources(3).forEach(i ->
                log.info("[USECASE] {} | {} | {}", i.source(), i.title(), i.url())
        );

//        var hn = ctx.getBean(HackerNewsClient.class);
//        long[] ids = hn.topStories();
//        log.info("[FEIGN] topstories count={}", ids.length);



    }

}
