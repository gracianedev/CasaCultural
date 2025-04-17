/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casacultural.filme.controller;

import com.casacultural.filme.model.Filme;
import com.casacultural.filme.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author GFS_Mac
 */
@Controller
public class FilmeController {
    @Autowired
    private FilmeService filmeService;
    
    
    @GetMapping ("/index")
    public String inicio () {
        return "index";
    }
    
    @GetMapping ("/cadastro")
    public String cadastro (Model model){
        model.addAttribute("filme", new Filme());
        return "cadastro-filme";
    }
    
    @PostMapping ("/salvar")
    public String cadastrarFilme (@ModelAttribute Filme filme){
        filmeService.salvar(filme);
        return "redirect:/lista";
    }
    
    @GetMapping ("/lista")
    public String lista () {
        return "lista-filmes";
    }
    
}
