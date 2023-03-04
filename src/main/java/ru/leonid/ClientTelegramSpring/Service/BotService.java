package ru.leonid.ClientTelegramSpring.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.leonid.ClientTelegramSpring.Model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@Service
public class BotService implements BotServiceInterface{
    @Value("${telegram.token}")
    public String botAddress;
    @Autowired
    private TelegramUpdateRepository telegramUpdateRepository;

    private Long maxidOffset = 0L;

    @Override
    public List<TelegramUpdate> getUpdates(long offset) {
        //запросить из базы последний update и отправить в ТГ запрос с новым update+1
        if(maxidOffset == 0L) maxidOffset = getLastIdFromDB() - offset;

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
    public Long getLastIdFromDB() {
        //System.out.println( sessionFactory.getCurrentSession());
       List<TelegramUpdate> telegramUpdate = telegramUpdateRepository.findAll();
       Long lastupdateID = telegramUpdate.stream().map(TelegramUpdate::getUpdate_id).max(Long::compareTo).orElse(0L);
        return lastupdateID;
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
                System.out.println(inputLine);
            in.close();
        }catch (IOException e){
            return false;
        }
        return true;
    }
}
