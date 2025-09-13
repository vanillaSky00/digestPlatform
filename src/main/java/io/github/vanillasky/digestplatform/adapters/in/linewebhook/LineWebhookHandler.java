package io.github.vanillasky.digestplatform.adapters.in.linewebhook;



import com.linecorp.bot.messaging.client.MessagingApiClient;
import com.linecorp.bot.messaging.model.ReplyMessageRequest;
import com.linecorp.bot.spring.boot.handler.annotation.EventMapping;
import com.linecorp.bot.spring.boot.handler.annotation.LineMessageHandler;
import com.linecorp.bot.webhook.model.Event;
import com.linecorp.bot.webhook.model.MessageEvent;
import io.github.vanillasky.digestplatform.application.service.ArticleService;

import java.util.List;


@LineMessageHandler
public class LineWebhookHandler {
    private final MessagingApiClient messagingApiClient;
    private final ArticleService articleService;

    public LineWebhookHandler(
            MessagingApiClient messagingApiClient,
            ArticleService articleService) {
        this.messagingApiClient = messagingApiClient;
        this.articleService = articleService;
    }

//    //When a user messages your bot, LINE sends a POST to your webhook URL.
//    @EventMapping
//    public void handleTextMessageEvent(MessageEvent event) {
//        System.out.println("event: " + event);
//        final String originalMessageText = ((TextMessageContent) event.message()).text();
//        messagingApiClient.replyMessage(
//                new ReplyMessageRequest.Builder(
//                        event.replyToken(),
//                        List.of(new TextMessage(originalMessageText))
//                ).build()
//        );
//    }

    //When a user messages your bot, LINE sends a POST to your webhook URL.
    @EventMapping
    public void handleTextMessageEvent(MessageEvent event) {
        var token = event.replyToken();

        var articles = articleService.latestOfEach(
                List.of("HN", "GUARDIAN"),
                5);

        var messages = DigestRenderer.toTextMessages(articles);

        messagingApiClient.replyMessage(
                new ReplyMessageRequest.Builder(
                        token, messages
                ).build()
        );
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
