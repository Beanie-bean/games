package hh.sof03.games.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import hh.sof03.games.domain.Game;
import hh.sof03.games.domain.GameRepository;
import hh.sof03.games.domain.GenreRepository;
import hh.sof03.games.domain.PlatformRepository;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class GameController {

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping(value = { "*", "/gamelist" })
    public String getGames(Model model, HttpServletRequest request, ServletContext servletContext) {
        model.addAttribute("games", gameRepository.findAll());
        model.addAttribute("servletPath", request.getServletPath());
        model.addAttribute("servletContext", request.getContextPath());

        return "gamelist";
    }

    @RequestMapping(value = "/addgame")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addGame(Model model) {
        model.addAttribute("game", new Game());
        model.addAttribute("platforms", platformRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        return "addgame";
    }

    @RequestMapping(value = "/savegame", method = RequestMethod.POST)
    public String saveGame(@Valid @ModelAttribute("game") Game game, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("platforms", platformRepository.findAll());
            model.addAttribute("genres", genreRepository.findAll());
            return "addgame";
        } else {
            gameRepository.save(game);
            return "redirect:gamelist";
        }
    }

    @RequestMapping(value = "/deletegame/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteGame(@PathVariable("id") Long id, Model model) {
        gameRepository.deleteById(id);
        return "redirect:/gamelist";
    }

    @RequestMapping(value = "/editgame/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editGame(@PathVariable("id") Long id, Model model) {
        model.addAttribute("game", gameRepository.findById(id));
        model.addAttribute("platforms", platformRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        return "editgame";
    }

    @RequestMapping(value = "/editgamesave", method = RequestMethod.POST)
    public String editGameSave(@Valid @ModelAttribute("game") Game game, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("platforms", platformRepository.findAll());
            model.addAttribute("genres", genreRepository.findAll());
            return "editgame";
        } else {
            gameRepository.save(game);
            return "redirect:gamelist";
        }
    }
}
