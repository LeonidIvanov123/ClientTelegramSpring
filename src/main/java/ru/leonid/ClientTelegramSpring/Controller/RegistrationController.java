package ru.leonid.ClientTelegramSpring.Controller;

import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String registrationForm(Model model) {
       // model.addAttribute("registerForm", new RegistrationForm());
        if(!model.containsAttribute("registerForm")){
            model.addAttribute("registerForm", new RegistrationForm());
        }
        return "register";
    }
    @PostMapping
    public String processRegistration(@Valid @ModelAttribute("registerForm") RegistrationForm registerForm,
                                      BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "register";
        }
        userAppRepository.save(registerForm.toUserApp(passwordEncoder));
        return "redirect:/login";
    }


}
