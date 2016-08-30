package nju.citicup.WebController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by admin on 2016/8/30.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "redirect:/home.html";
    }

}
