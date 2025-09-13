package io.github.vanillasky.digestplatform.config;

import io.github.vanillasky.digestplatform.adapters.out.sources.guardian.GuardianClient;
import io.github.vanillasky.digestplatform.adapters.out.sources.hackernews.HackerNewsClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableFeignClients(
        basePackageClasses = {
                HackerNewsClient.class,
                GuardianClient.class
        }
)
@EnableScheduling
public class ProjectConfig {
}
