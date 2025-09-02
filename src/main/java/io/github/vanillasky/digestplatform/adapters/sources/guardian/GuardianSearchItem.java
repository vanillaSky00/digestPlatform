package io.github.vanillasky.digestplatform.adapters.sources.guardian;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GuardianSearchItem {

    //this line matches the top-level response in the JSON
    @JsonProperty("response")
    public InnerResponse innerResponse;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class InnerResponse {
        public String status;         // "ok"
        public String userTier;       // "developer"
        public int total;             // total results
        public int startIndex;        // 1-based
        public int pageSize;          // page size (max 200)
        public int currentPage;       // current page number
        public int pages;             // total pages
        public String orderBy;        // "newest" | "relevance" | ...
        public List<Result> results;  // list of content items
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {
        public String id;                 // e.g. "world/2025/sep/01/..."
        public String type;               // "article", "liveblog", ...
        public String sectionId;          // e.g. "world"
        public String sectionName;        // e.g. "World news"
        public String webPublicationDate; // ISO8601
        public String webTitle;           // headline text
        public String webUrl;             // public URL
        public String apiUrl;             // API URL
        public boolean isHosted;
        public String pillarId;           // e.g. "pillar/news"
        public String pillarName;         // e.g. "News"
        public Fields fields;             // present if you request show-fields
        public List<Tag> tags;            // present if you request show-tags
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Fields {
        public String headline;   // HTML
        public String trailText;  // HTML dek/standfirst
        public String body;       // full HTML body (if requested)
        public String bodyText;   // plain text body (if requested)
        public String byline;
        public String thumbnail;  // image URL (if available/requested)
        public String shortUrl;   // https://gu.com/p/...
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Tag {
        public String id;         // e.g. "world/europe"
        public String type;       // "keyword", "contributor", ...
        public String webTitle;
        public String webUrl;
        public String apiUrl;
    }
}

//With that, Jackson (the default decoder behind Spring Cloud OpenFeign) can deserialize to your class:
// it sees "response" → creates InnerResponse, then fills results with Result objects (and fields if present).
