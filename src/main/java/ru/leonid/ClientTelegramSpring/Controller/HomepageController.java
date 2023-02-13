package ru.leonid.ClientTelegramSpring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.leonid.ClientTelegramSpring.Model.TelegramUpdate;
import ru.leonid.ClientTelegramSpring.Service.BotService;
import ru.leonid.ClientTelegramSpring.Service.BotServiceInterface;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomepageController {

    final BotServiceInterface botService;
    public HomepageController(BotService botService) {
        this.botService = botService;
    }

    @GetMapping("/")
    public String homepage(Model model){
        botService.getLastFromDB();
        model.addAttribute("botAddress", botService.getBotAddress());
        return "homepage";
    }

    @GetMapping("/update")
    public String getUpdatesfrombot(Model model){

        model.addAttribute("updates", botService.getUpdates(0));
        System.out.println();
        return "listofdata";
    }
}
