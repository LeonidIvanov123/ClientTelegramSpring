package ru.leonid.ClientTelegramSpring.Service;

import ru.leonid.ClientTelegramSpring.Model.TelegramUpdate;

import java.util.List;

public interface BotServiceInterface {

    List<TelegramUpdate> getUpdates(long offset);
    void sendToChat(long chatId, String text);
    TelegramUpdate getLastFromDB();
    String getBotAddress();


}
