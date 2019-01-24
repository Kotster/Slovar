package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    public static final int DEFAULT_SPITTLES_PER_PAGE = 25;
    @Autowired
    @Qualifier("dbControl")
    private ISlovService spitterService;

    @RequestMapping({"/","/home"})     // Обрабатывать запросы на получение главной страницы
    public String showHomePage(List<String> list) {
        list.add(spitterService.all());                //Добавить сообщения в модель
        return "home";              // Вернуть имя представления
    }
}
