package io.github.vanillasky.digestplatform.adapters.out.sources.guardian;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "gd",
        url = "${source.guardian.base-url}",
        configuration = GuardianFeignConfig.class

)
public interface GuardianClient {

    //main content endpoint (supports q, tags, date filters, paging)
    @GetMapping("/search")
    GuardianSearchItem search(
            @RequestParam("q") String q,
            @RequestParam(value = "tag", required = false) String tag,                  // e.g. "environment/recycling"
            @RequestParam(value = "from-date", required = false) String from,           // "YYYY-MM-DD"
            @RequestParam(value = "to-date", required = false) String to,               // optional
            @RequestParam(value = "order-by", defaultValue = "newest") String orderBy,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "page-size", defaultValue = "20") int pageSize,
            @RequestParam(value = "show-fields", required = false) String showFields,   // "body,trailText,thumbnail"
            @RequestParam(value = "show-blocks", required = false) String showBlocks    // e.g. "all"
    );

//    // /tags — discover tag ids you can use in `tag=...`
//    @GetMapping("/tags")
//    GuardianTagsResponse tags(
//            @RequestParam("q") String q,
//            @RequestParam(value = "page", defaultValue = "1") int page,
//            @RequestParam(value = "page-size", defaultValue = "20") int pageSize
//    );
//
//    // /sections — discover sections
//    @GetMapping("/sections")
//    GuardianSectionsResponse sections(
//            @RequestParam(value = "page", defaultValue = "1") int page,
//            @RequestParam(value = "page-size", defaultValue = "20") int pageSize
//    );
//
//    // Single item — catch-all path (id contains slashes). Spring Boot 3 PathPattern supports {**id}.
//    @GetMapping("/{**id}")
//    GuardianItemResponse item(
//            @PathVariable("id") String id,
//            @RequestParam(value = "show-fields", required = false) String showFields,
//            @RequestParam(value = "show-blocks", required = false) String showBlocks
//    );
}
