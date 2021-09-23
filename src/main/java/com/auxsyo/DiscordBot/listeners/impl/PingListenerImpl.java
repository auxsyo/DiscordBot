package com.auxsyo.DiscordBot.listeners.impl;

import com.auxsyo.DiscordBot.listeners.PingListener;
import com.auxsyo.DiscordBot.service.MessagingService;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PingListenerImpl implements PingListener {

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        if(messageCreateEvent.getMessageContent().equals("-ping")) {
            messageCreateEvent.getChannel().sendMessage("Pong nigga!");
        }
    }
}
