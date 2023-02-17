package ru.leonid.ClientTelegramSpring.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import ru.leonid.ClientTelegramSpring.Model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class BotService implements BotServiceInterface{
    @Value("${telegram.token}")
    public String botAddress;
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    private TelegramUpdateRepository telegramUpdateRepository;

    private Long maxidOffset ;
    static Logger logger = org.slf4j.LoggerFactory.getLogger(BotService.class); ;

    @Override
    public List<TelegramUpdate> getUpdates(long offset) {
        //запросить из базы последний update и отправить в ТГ запрос с новым update+1
        TelegramUpdate tgupd = getLastFromDB();
        if(tgupd!=null) maxidOffset = tgupd.getUpdate_id();

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
    @Override
    public TelegramUpdate getLastFromDB() {
        //System.out.println( sessionFactory.getCurrentSession());
       List<TelegramUpdate> telegramUpdate = telegramUpdateRepository.findAll();
       logger.debug("Найдено апдейтов: "+ telegramUpdate.size());
       TelegramUpdate lastupdate = telegramUpdate.stream().sorted(Comparator.comparing(TelegramUpdate::getUpdate_id)).findFirst().orElse(null);
       logger.debug("Последний апдейт: "+ lastupdate.toString());
        return lastupdate; //не хорошо возвращать null если в базе нет апдейтов!
    }

    @Override
    public String getBotAddress() {
        return botAddress;
    }

    @Override
    public boolean sendMessage(String message, Long chat_id) {
        try {
            URL url = new URL(botAddress + "sendMessage?" + "chat_id=" + chat_id + "&text=" + message);
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                logger.debug(inputLine);
            in.close();
        }catch (IOException e){
            logger.error("Не удалось отправить сообщение в чат: "+ chat_id);
            return false;
        }
        return true;
    }
}
