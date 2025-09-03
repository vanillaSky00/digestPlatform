package io.github.vanillasky.digestplatform.app.controller;



import com.linecorp.bot.messaging.client.MessagingApiClient;
import com.linecorp.bot.messaging.model.ReplyMessageRequest;
import com.linecorp.bot.messaging.model.TextMessage;
import com.linecorp.bot.spring.boot.handler.annotation.EventMapping;
import com.linecorp.bot.spring.boot.handler.annotation.LineMessageHandler;
import com.linecorp.bot.webhook.model.Event;
import com.linecorp.bot.webhook.model.MessageEvent;
import com.linecorp.bot.webhook.model.TextMessageContent;

import java.util.List;


@LineMessageHandler
public class LineBotController {
    private final MessagingApiClient messagingApiClient;//a small HTTP client that talks to LINE’s Messaging API.

    public LineBotController(MessagingApiClient messagingApiClient) {
        this.messagingApiClient = messagingApiClient;
    }

    //When a user messages your bot, LINE sends a POST to your webhook URL.
    @EventMapping
    public void handleTextMessageEvent(MessageEvent event) {
        System.out.println("event: " + event);
        final String originalMessageText = ((TextMessageContent) event.message()).text();
        messagingApiClient.replyMessage(
                new ReplyMessageRequest.Builder(
                        event.replyToken(),
                        List.of(new TextMessage(originalMessageText))
                ).build()
        );
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
