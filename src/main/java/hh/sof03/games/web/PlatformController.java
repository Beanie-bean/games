package hh.sof03.games.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hh.sof03.games.domain.PlatformRepository;

@Controller
public class PlatformController {
    @Autowired
    private PlatformRepository platformRepository;

    

}
