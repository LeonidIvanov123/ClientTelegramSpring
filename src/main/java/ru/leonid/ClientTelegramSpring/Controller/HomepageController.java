package ru.leonid.ClientTelegramSpring.Controller;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ru.leonid.ClientTelegramSpring.Service.BotService;
import ru.leonid.ClientTelegramSpring.Service.BotServiceInterface;

import java.net.Authenticator;


@Controller
public class HomepageController {

    final BotServiceInterface botService;
    private Long offset = 5L;
    private String sendStatus = "";
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
        model.addAttribute("sendstatus",sendStatus);
        sendStatus = "";
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
    public String sendToRabbit(@RequestParam("mymessage") String message, Model model){
        System.out.println("Отправляем: "+ message);
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForObject("http://127.0.0.1:8080/send?msgtorabbit=" + message, String.class);
        }catch (RestClientException ioe){
            sendStatus = "Ошибка отправки. Сервис недоступен.    [" + ioe.getMessage() + "]";
            return "redirect:/update";
        }
        sendStatus = "Сообщение отправлено: [" + message + "]";
        return "redirect:/update";
    }

}
