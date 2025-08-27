package io.github.vanillasky.digestplatform.app.config;

import io.github.vanillasky.digestplatform.adapters.sources.hn.HackerNewsAdapter;
import io.github.vanillasky.digestplatform.adapters.sources.hn.HackerNewsClient;
import io.github.vanillasky.digestplatform.domain.ports.in.AggregateFeedUseCase;
import io.github.vanillasky.digestplatform.domain.ports.out.ContentSourcePort;
import io.github.vanillasky.digestplatform.domain.service.AggregateFeedService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BeansConfig {

    @Bean
    @Qualifier("HackerNews")
    ContentSourcePort hnSource(HackerNewsClient client) {
        return new HackerNewsAdapter(client);
    }

    @Bean
    AggregateFeedUseCase aggregateFeedUseCase(List<ContentSourcePort> sources) {
        return new AggregateFeedService(sources);
    }
}
