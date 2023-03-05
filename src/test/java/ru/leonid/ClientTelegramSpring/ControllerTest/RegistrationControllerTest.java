package ru.leonid.ClientTelegramSpring.ControllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.leonid.ClientTelegramSpring.Controller.RegistrationController;
import ru.leonid.ClientTelegramSpring.Model.UserAppRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(RegistrationController.class)
@AutoConfigureMockMvc(webClientEnabled = false)
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserAppRepository userAppRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @WithMockUser(value = "spring")
    @Test
    public void processRegistrationTest() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/register").secure(false)
                .accept(MediaType.TEXT_HTML)).andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.view().name("register"));
    }


}
