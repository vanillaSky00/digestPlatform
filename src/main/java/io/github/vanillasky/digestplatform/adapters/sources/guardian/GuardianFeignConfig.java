package io.github.vanillasky.digestplatform.adapters.sources.guardian;

import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

// NOTE: no @Configuration here on purpose
public class GuardianFeignConfig {

    @Value("${guardian.api-key}")
    private String apiKey;

    @Bean
    public RequestInterceptor apiKeyQueryParam() {
        return template -> template.query("api-key", apiKey);
    }

    @Bean
    public Logger.Level feginLoggerLevel() {
        return Logger.Level.BASIC;
    }

    @Bean
    public Feign.Builder feignBuilder() {
        return Feign.builder()
                .options(
                        new Request.Options(
                                Duration.ofSeconds(2),
                                Duration.ofSeconds(5),
                                true  // follow redirects
                        )
                );
    }
}



//If you put a RequestInterceptor bean in a globally scanned config, it applies to all Feign clients
// (and you’ll accidentally add api-key to HackerNews too).
// Keeping a per-client config class alongside the client and not scanned ensures clean scoping.

