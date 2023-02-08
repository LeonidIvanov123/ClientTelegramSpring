package ru.leonid.ClientTelegramSpring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.leonid.ClientTelegramSpring.Service.BotService;

@Controller
public class HomepageController {

    final BotService botService;

    public HomepageController(BotService botService) {
        this.botService = botService;
    }

    @GetMapping("/")
    public String homepage(Model model){
        model.addAttribute("botAddress", botService.botAddress);
        return "homepage";
    }
}
