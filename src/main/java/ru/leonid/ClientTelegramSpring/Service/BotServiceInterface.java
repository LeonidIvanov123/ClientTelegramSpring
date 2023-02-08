package ru.leonid.ClientTelegramSpring.Service;

import ru.leonid.ClientTelegramSpring.Model.TelegramMessage;

import java.util.List;

public interface BotServiceInterface {

    List<TelegramMessage> getUpdates(long offset);
    void sendToChat(long chatId, String text);

}
