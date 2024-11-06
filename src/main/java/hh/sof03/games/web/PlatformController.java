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

import hh.sof03.games.domain.Platform;
import hh.sof03.games.domain.PlatformRepository;

@Controller
public class PlatformController {
    @Autowired
    private PlatformRepository platformRepository;

    @RequestMapping(value="/platformlist")
    public String platformList(Model model, HttpServletRequest request) {
        model.addAttribute("platforms", platformRepository.findAll());
        model.addAttribute("servletPath", request.getServletPath());
        return "platformlist";
    }

    @RequestMapping(value="/addplatform")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addPlatform(Model model) {
        model.addAttribute("platform", new Platform());
        return "addplatform";
    }

    @RequestMapping(value="/saveplatform", method = RequestMethod.POST)
    public String savePlatform(@Valid Platform platform, BindingResult result) {
        if (result.hasErrors()) {
            return "addplatform";
        }
        platformRepository.save(platform);
        return "redirect:/platformlist";
    }

    @RequestMapping(value="/deleteplatform/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deletePlatform(@PathVariable("id") Long id, Model model) {
        platformRepository.deleteById(id);
        return "redirect:/platformlist";
    }

    @RequestMapping(value="/editplatform/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editPlatform(@PathVariable("id") Long id, Model model) {
        model.addAttribute("platform", platformRepository.findById(id));
        return "editplatform";
    }
}
