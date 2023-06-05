package ru.leonid.ClientTelegramSpring.Model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TelegramUpdateRepositoryTest {

    @Autowired
    TelegramUpdateRepository telegramUpdateRepository;

    private TelegramUpdate telegramUpdate;

    @BeforeEach
    void initdata(){
        telegramUpdate = new TelegramUpdate();
        telegramUpdate.setUpdate_id(15);
        TelegramMessage telegramMessage = new TelegramMessage();
        telegramUpdate.setMessage(telegramMessage);
        telegramUpdateRepository.save(telegramUpdate);
        telegramUpdate.setUpdate_id(1);
        telegramUpdateRepository.save(telegramUpdate);
        telegramUpdate.setUpdate_id(30);
        telegramUpdateRepository.save(telegramUpdate);
    }

    @Test
    void updateIdMax() {
        assertEquals(3, telegramUpdateRepository.findAll().size());
        assertTrue(telegramUpdateRepository.UpdateIdMax().get() == 30l);
        //assertEquals();
    }
}