package io.github.vanillasky.digestplatform.app.config;

import io.github.vanillasky.digestplatform.adapters.sources.guardian.GuardianClient;
import io.github.vanillasky.digestplatform.adapters.sources.hackernews.HackerNewsClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(
        basePackageClasses = {
                HackerNewsClient.class,
                GuardianClient.class
        }
)
public class ProjectConfig {
}
