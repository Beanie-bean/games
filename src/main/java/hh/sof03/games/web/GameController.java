package hh.sof03.games.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import hh.sof03.games.domain.Game;
import hh.sof03.games.domain.GameRepository;
import hh.sof03.games.domain.GenreRepository;
import hh.sof03.games.domain.PlatformRepository;

@Controller
public class GameController {

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping(value = { "*", "/gamelist" })
    public String getGames(Model model) {
        model.addAttribute("games", gameRepository.findAll());
        return "gamelist";
    }

    @RequestMapping(value = "/addgame")
    public String addGame(Model model) {
        model.addAttribute("game", new Game());
        model.addAttribute("platforms", platformRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        return "addgame";
    }

    @RequestMapping(value = "/savegame", method = RequestMethod.POST)
    public String saveGame(Game game) {
        gameRepository.save(game);
        return "redirect:gamelist";
    }

    @RequestMapping(value = "/deletegame/{id}", method = RequestMethod.GET)
    public String deleteGame(@PathVariable("id") Long id, Model model) {
        gameRepository.deleteById(id);
        return "redirect:/gamelist";
    }

    @RequestMapping(value = "/editgame/{id}")
    public String editGame(@PathVariable("id") Long id, Model model) {
        model.addAttribute("game", gameRepository.findById(id));
        model.addAttribute("platforms", platformRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        return "editgame";
    }
}
