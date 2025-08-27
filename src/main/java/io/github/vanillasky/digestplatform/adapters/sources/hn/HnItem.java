package io.github.vanillasky.digestplatform.adapters.sources.hn;

import java.util.List;

public record HnItem(
        Long id,
        String type,
        String by,
        Long time,
        String title,
        String url,
        Integer score,
        List<Long> kids,
        Long parent
)
{}
