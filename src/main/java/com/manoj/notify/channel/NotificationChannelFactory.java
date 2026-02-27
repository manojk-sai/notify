package com.manoj.notify.channel;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class NotificationChannelFactory {

    private final Map<ChannelType, NotificationChannel> channelMap;

    public NotificationChannelFactory(List<NotificationChannel> channels) {
        this.channelMap = channels.stream()
                .collect(Collectors.toMap(
                        NotificationChannel::getType,
                        Function.identity()
                ));
    }

    public NotificationChannel getChannel(ChannelType type) {
        NotificationChannel channel = channelMap.get(type);

        if (channel == null) {
            throw new IllegalArgumentException("Unsupported channel: " + type);
        }

        return channel;
    }
}