package ru.leonid.ClientTelegramSpring.Service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.leonid.ClientTelegramSpring.Model.*;

import java.util.List;

@Service
public class BotService implements BotServiceInterface{
    @Value("${telegram.token}")
    public String botAddress;
    @Autowired
    private TelegramUpdateRepository telegramUpdateRepository;
    private Long maxidOffset;
    static Logger logger = org.slf4j.LoggerFactory.getLogger(BotService.class); ;

    @Override
    public List<TelegramUpdate> getUpdates(long offset) {
        //запросить из базы последний update и отправить в ТГ запрос с новым update+1
        maxidOffset = getLastIdFromDB() - offset;

        String query = botAddress+ "getUpdates";
        if(maxidOffset != 0L) query = query + "?offset="+maxidOffset;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseFromTG> responseEntity = restTemplate.getForEntity(query, ResponseFromTG.class);

        ResponseFromTG responseFromTG = responseEntity.getBody();
        if (responseFromTG.getTelegramUpdates().size() > 0) {
            maxidOffset = responseFromTG.getTelegramUpdates().get(responseFromTG.getTelegramUpdates().size() - 1).getUpdate_id() - offset;
            for(TelegramUpdate telegramUpdate: responseFromTG.getTelegramUpdates())
                telegramUpdateRepository.save(telegramUpdate);
        }
            System.out.println("offset = " + maxidOffset);
        return responseFromTG.getTelegramUpdates();
    }
    @Override
    public void sendToChat(long chatId, String text) {
    }
    /***
     * Получаем последний id апдейта, чтоб запросить новые не прочитанные сообщения
     * ***/
    @Override
    public Long getLastIdFromDB() {
        //System.out.println( sessionFactory.getCurrentSession());
       List<TelegramUpdate> telegramUpdate = telegramUpdateRepository.findAll();
       logger.debug("Найдено апдейтов: "+ telegramUpdate.size());
       Long lastupdateID = telegramUpdate.stream().map(TelegramUpdate::getUpdate_id).max(Long::compareTo).orElse(0L);
       logger.debug("Последний апдейт: (либо ноль)"+ lastupdateID);
        return lastupdateID;
    }
    @Override
    public String getBotAddress() {
        return botAddress;
    }
}
