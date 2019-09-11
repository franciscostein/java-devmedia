package br.com.devmedia.controller;

import br.com.devmedia.domain.Playlist;
import br.com.devmedia.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("playlists", playlistService.recuperar());
        return new ModelAndView("playlist/list", model);
    }

    @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("playlist")Playlist playlist) {
        return "/playlist/add";
    }

    @PostMapping("/salvar")     //Post para criar
    public String salvar(@Valid @ModelAttribute("playlist") Playlist playlist, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "/playlist/add";
        }

        playlistService.salvar(playlist);
        attributes.addFlashAttribute("mensagem", "Playlist criada com sucesso.");
        return "redirect:/playlists/listar";
    }

    @GetMapping("/{id}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("id") Long id, ModelMap model) {
        Playlist playlist = playlistService.recuperarPorId(id);
        model.addAttribute("playlist", playlist);
        return new ModelAndView("/playlist/add", model);
    }

    @PutMapping("/salvar")      //Put para atualizar
    public String atualizar(@Valid @ModelAttribute("playlist") Playlist playlist, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "/playlist/add";
        }

        playlistService.atualizar(playlist);
        attributes.addFlashAttribute("mensagem", "Playlist atualizada com sucesso.");
        return "redirect:/playlists/listar";
    }

    @GetMapping("/{id}/remover")
    public String remover(@PathVariable("id") long id, RedirectAttributes attributes) {
        playlistService.excluir(id);
        attributes.addFlashAttribute("mensagem", "Playlist excluída com sucesso.");
        return "redirect:/playlists/listar";    //Vai pra outro método, por isso não precisa de um ModelMap
    }
}
