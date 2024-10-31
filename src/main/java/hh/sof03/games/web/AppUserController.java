package hh.sof03.games.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppUserController {

    @Autowired
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }
}
