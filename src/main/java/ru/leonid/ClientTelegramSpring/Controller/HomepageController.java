package ru.leonid.ClientTelegramSpring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.leonid.ClientTelegramSpring.Service.BotService;
import ru.leonid.ClientTelegramSpring.Service.BotServiceInterface;

import java.net.Authenticator;


@Controller
public class HomepageController {

    final BotServiceInterface botService;
    private Long offset = 5L;
    public HomepageController(BotService botService) {
        this.botService = botService;
    }

    @GetMapping("/")
    public String homepage(Model model){
        model.addAttribute("botAddress", botService.getBotAddress());
        return "homepage";
    }

    @GetMapping("/update")
    public String getUpdatesfrombot(Model model){
        model.addAttribute("offset", offset);
        model.addAttribute("updates", botService.getUpdates(offset));
        return "listofdata";
    }

    @PostMapping("/update")
    public String sendUpdateToTelegram(@ModelAttribute Long offset, Model model){
        System.out.println("получили оффсет: "+ offset);
        return "listofdata";
    }
//сделать столбец юзернейм в представлении гиперссылкой с переходом на другую страницу для ответа пользователю.

    //тест rest + rabbitmq project
    @PostMapping("/send")
    public String sendToRabbit(@RequestParam("mymessage") String message){
        System.out.println("Отправляем: "+ message);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject("http://127.0.0.1:8080/send?msgtorabbit="+ message, String.class);
        return "redirect:/update";
    }

}
