package ru.leonid.ClientTelegramSpring.Controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.leonid.ClientTelegramSpring.Model.RegistrationForm;
import ru.leonid.ClientTelegramSpring.Model.UserAppRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserAppRepository userAppRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(
            UserAppRepository userAppRepository, PasswordEncoder passwordEncoder) {
        this.userAppRepository = userAppRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "register";
    }
    @PostMapping
    public String processRegistration(RegistrationForm registerForm) {
        userAppRepository.save(registerForm.toUserApp(passwordEncoder));
        return "redirect:/login";
    }


}
