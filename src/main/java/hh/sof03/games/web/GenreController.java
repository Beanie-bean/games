package hh.sof03.games.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import hh.sof03.games.domain.Genre;
import hh.sof03.games.domain.GenreRepository;

@Controller
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;

    @RequestMapping(value="/genrelist")
    public String genreList(Model model, HttpServletRequest request) {
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("servletPath", request.getServletPath());
        return "genrelist";
    }

    @RequestMapping(value="/addgenre")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addGenre(Model model) {
        model.addAttribute("genre", new Genre());
        return "addgenre";
    }

    @RequestMapping(value="/savegenre", method = RequestMethod.POST)
    public String saveGenre(@Valid Genre genre, BindingResult result) {
        if (result.hasErrors()) {
            return "addgenre";
        }
        genreRepository.save(genre);
        return "redirect:/genrelist";
    }

    @RequestMapping(value="/deletegenre/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteGenre(@PathVariable("id") Long id, Model model) {
        genreRepository.deleteById(id);
        return "redirect:/genrelist";
    }

    @RequestMapping(value="/editgenre/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editGenre(@PathVariable("id") Long id, Model model) {
        model.addAttribute("genre", genreRepository.findById(id));
        return "editgenre";
    }
}
