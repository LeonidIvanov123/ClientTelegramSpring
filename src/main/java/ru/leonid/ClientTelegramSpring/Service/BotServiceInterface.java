package ru.leonid.ClientTelegramSpring.Service;

import ru.leonid.ClientTelegramSpring.Model.TelegramUpdate;

import java.util.List;

public interface BotServiceInterface {

    List<TelegramUpdate> getUpdates(long offset);
    boolean sendMessage(String message, Long chat_id);
    Long getLastIdFromDB();
    String getBotAddress();
}
