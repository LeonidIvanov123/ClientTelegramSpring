package ru.leonid.ClientTelegramSpring.Service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.leonid.ClientTelegramSpring.Model.*;

import java.util.List;

@Service
public class BotService implements BotServiceInterface{
    @Value("${telegram.token}")
    public String botAddress;
    @Autowired
    SessionFactory sessionFactory;


    @Override
    public List<TelegramUpdate> getUpdates(long offset) {
        RestTemplate restTemplate = new RestTemplate();
        TelegramUpdate tMessage = restTemplate.getForObject(botAddress + "getUpdates", TelegramUpdate.class);
        System.out.println(tMessage.toString());
        return null;
    }

    @Override
    public void sendToChat(long chatId, String text) {

    }

    @Override
    public TelegramUpdate getLastFromDB() {

        //System.out.println( sessionFactory.getCurrentSession());

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        TelegramChat telegramChat = new TelegramChat();
        telegramChat.setFirst_name("testname");
        telegramChat.setLast_name("test2name");
        telegramChat.setId(1L);
        telegramChat.setType("trololo");
        session.persist(telegramChat);
        transaction.commit();


        //long i = msgRepository.count();
        //System.out.println(i);
        //if(i>0)
         //   System.out.println(msgRepository.findAll().toString());

        return null;
    }

    @Override
    public String getBotAddress() {
        return botAddress;
    }
}
