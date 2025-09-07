package io.github.vanillasky.digestplatform.application.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebService {

    final private ArticleIngestionService articleIngestionService;

    public WebService(ArticleIngestionService articleIngestionService) {
        this.articleIngestionService = articleIngestionService;
    }

    @GetMapping("/articles")
    public ResponseEntity<?> getAllArticles() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(articleIngestionService.getAllArticles());
    }
}
