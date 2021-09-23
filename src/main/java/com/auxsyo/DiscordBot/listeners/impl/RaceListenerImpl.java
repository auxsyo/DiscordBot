package com.auxsyo.DiscordBot.listeners.impl;

import com.auxsyo.DiscordBot.listeners.RaceListener;
import com.auxsyo.DiscordBot.service.MessagingService;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RaceListenerImpl implements RaceListener {
    private static boolean active = false;

    @Autowired
    private MessagingService messagingService;

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        if(messageCreateEvent.getMessageContent().equals("-race")) {
            if(!active) {
                active = true;
                messagingService.sendMessage(messageCreateEvent.getMessageAuthor(),
                        "The race begins!",
                        "Be the first to **react** to this message to win!",
                        null,
                        "https://media.istockphoto.com/photos/international-race-track-picture-id1156933946?k=20&m=1156933946&s=612x612&w=0&h=bVhv4K9fW3C7cIWc1ASScP44KAS0c9-hNc_wMYsEu5s=",
                        messageCreateEvent.getChannel())
                .thenAccept(message -> {
                    message.addReactionAddListener(listener -> {
                        if(active) {
                            message.edit(new EmbedBuilder()
                                    .setTitle("The race ends!")
                                    .setDescription("Congratulations! **" + listener.getUser().get().getName() + "** was first!\nThe race is now over!")
                                    .setFooter("Race again?"));
                                    active = false;
                        }
                    });
                });
            }
        }
    }
}
