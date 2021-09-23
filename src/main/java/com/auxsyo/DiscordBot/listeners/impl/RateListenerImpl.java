package com.auxsyo.DiscordBot.listeners.impl;

import com.auxsyo.DiscordBot.listeners.RateListener;
import com.auxsyo.DiscordBot.service.MessagingService;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RateListenerImpl implements RateListener {
    private final static Pattern pattern = Pattern.compile("-rate (\\w+)");

    @Autowired
    private MessagingService messagingService;
    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        if(messageCreateEvent.getMessageContent().startsWith("-rate")) {
            Matcher matcher = pattern.matcher(messageCreateEvent.getMessageContent());
            if(matcher.matches()) {
                //Do the rating
                int rating = (int) Math.floor(Math.random() * 100) + 1;

                messagingService.sendMessage(messageCreateEvent.getMessageAuthor(),
                        "Rate calculator",
                        messageCreateEvent.getMessageAuthor().getDisplayName() + " is " + rating + "% " + matcher.group(1),
                        "Rate again?",
                        "https://previews.123rf.com/images/realcg/realcg1603/realcg160300024/53926927-lucky-spin-represent-the-wheel-of-fortune-concept-this-graphic-is-create-by-three-dimensional-welcom.jpg",
                        messageCreateEvent.getChannel());
            } else {
                // Send helpful syntax message

                messagingService.sendMessage(messageCreateEvent.getMessageAuthor(),
                        "Rate calculator",
                        "Are you trying to use the `-rate` command? Please use the syntax `-rate [word]`. Thanks!",
                        "Rate again?",
                null,
                messageCreateEvent.getChannel(), true);
            }
        }
    }
}
