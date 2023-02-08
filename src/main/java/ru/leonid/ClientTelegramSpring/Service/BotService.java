package ru.leonid.ClientTelegramSpring.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.leonid.ClientTelegramSpring.Model.TelegramMessage;

import java.util.List;

@Service
public class BotService implements BotServiceInterface{
    @Value("${telegram.token}")
    public String botAddress;

    @Override
    public List<TelegramMessage> getUpdates(long offset) {

        return null;
    }

    @Override
    public void sendToChat(long chatId, String text) {

    }
}
