package ru.leonid.ClientTelegramSpring.Controller;

import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
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
    public String registrationForm() {
        return "register";
    }

    @ModelAttribute(name = "registerForm")
    public RegistrationForm registerForm(){
        return new RegistrationForm();
    }
    @PostMapping
    public String processRegistration(@Valid RegistrationForm registerForm, Errors errors) {
        if(errors.hasErrors()){

            return "register";
        }
        userAppRepository.save(registerForm.toUserApp(passwordEncoder));
        return "redirect:/login";
    }


}
