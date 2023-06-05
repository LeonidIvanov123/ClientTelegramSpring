package ru.leonid.ClientTelegramSpring.Service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.leonid.ClientTelegramSpring.Model.TelegramUpdateRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class BotServiceTest {
    @Autowired
    private BotService botService;
    @MockBean
    private TelegramUpdateRepository telegramUpdateRepository;
    @Test
    void getLastIdFromDB() {
        //мок возвращает 10 когда вызываем его метод
        Mockito.doReturn(Optional.of(10l)).when(telegramUpdateRepository).UpdateIdMax();
        Long l = botService.getLastIdFromDB();
        Assert.assertTrue(l == 10);
        //Проверка на то, что метод UpdateIdMax из мока telegramUpdateRepository был вызван 1 раз
        Mockito.verify(telegramUpdateRepository, Mockito.times(1)).UpdateIdMax();
    }
}