package io.github.vanillasky.digestplatform.adapters.sources.hn;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "hn",
        url = "${integrations.hn.base-url}"
)
public interface HackerNewsClient {
    @GetMapping("/topstories.json")
    long[] topStories();

    @GetMapping("/item/{id}.json")
    HnItem item(@PathVariable("id") long id);
}
