package ru.leonid.ClientTelegramSpring.ControllerTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.leonid.ClientTelegramSpring.Controller.HomepageController;
import org.junit.jupiter.api.Test;
import ru.leonid.ClientTelegramSpring.Service.BotService;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(HomepageController.class)
public class HomepageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BotService botService;

    @Test
    public void testHomepage() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/")
                .accept(MediaType.TEXT_HTML)).andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.view().name("homepage"));
    }
}
