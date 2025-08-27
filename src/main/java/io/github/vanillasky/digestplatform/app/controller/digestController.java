package io.github.vanillasky.digestplatform.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class digestController {

    @GetMapping("/preview")
    public String previewDigest() {
        return "preview Digest";
    }

    @PostMapping("/send")
    public String sendDigest(
            @RequestParam String lineUserId
    ) {
        return "Sending digest to" + lineUserId;
    }
}
