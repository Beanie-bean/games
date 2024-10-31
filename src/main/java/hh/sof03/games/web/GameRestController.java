package hh.sof03.games.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.sof03.games.domain.Game;
import hh.sof03.games.domain.GameRepository;

@CrossOrigin
@Controller
public class GameRestController {

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping(value="/games", method = RequestMethod.GET)
    public @ResponseBody List<Game> gameListRest() {
        return (List<Game>) gameRepository.findAll();
    }

    @RequestMapping(value="/games/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Game> findGameRest(@PathVariable("id") Long id) {	
    	return gameRepository.findById(id);
    }  

    @RequestMapping(value="/games", method = RequestMethod.POST)
    public @ResponseBody Game saveGamesRest(@RequestBody Game game) {	
    	return gameRepository.save(game);
    }
}
