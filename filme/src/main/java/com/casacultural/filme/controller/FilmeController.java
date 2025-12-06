/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casacultural.filme.controller;

import com.casacultural.filme.model.Analise;
import com.casacultural.filme.model.Filme;
import com.casacultural.filme.service.AnaliseService;
import com.casacultural.filme.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author GFS_Mac
 */
@Controller
@RequestMapping("/filme")

public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private AnaliseService analiseService;

    @GetMapping("/cadastro")
    public String cadastro(@CookieValue(name = "pref-estilo", defaultValue = "style_claro") String tema, Model model) {
        model.addAttribute("filme", new Filme());
        model.addAttribute("css", tema);
        return "cadastro-filme";
    }

    @PostMapping("/salvar")
    public String cadastrarFilme(@ModelAttribute Filme filme) {
        filmeService.salvar(filme);
        return "redirect:/filme/lista";
    }

    @GetMapping("/lista")
    public String lista (@CookieValue(name = "pref-estilo", defaultValue = "style_claro") String tema, Model model) {
        model.addAttribute("filmes", filmeService.listarTodos());
        model.addAttribute("css", tema);
        return "lista-filmes";
    }

    @GetMapping("/analise/{id}")
    public String avaliar(@CookieValue(name = "pref-estilo", defaultValue = "style_claro") String tema, @PathVariable int id, Model model) {
        model.addAttribute("filme", filmeService.buscarPorId(id));
        model.addAttribute("analise", new Analise());
        model.addAttribute("css", tema);
        return "analise";
    }

    @GetMapping("/detalhe/{id}")
    public String detalhe(@CookieValue(name = "pref-estilo", defaultValue = "style_claro") String tema, @PathVariable int id, Model model) {
        model.addAttribute("filme", filmeService.buscarPorId(id));
        model.addAttribute("analise", analiseService.burcarPorId(id));
        model.addAttribute("css", tema);
        return "detalhe";
    }
    
     @PutMapping("/{id}")
    public Filme atualizarFilme (@PathVariable int id, @RequestBody Filme filmeAtualizado) {
        return filmeService.salvar(filmeAtualizado);
    }

}
