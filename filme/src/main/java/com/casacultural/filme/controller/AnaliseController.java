/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casacultural.filme.controller;

import com.casacultural.filme.model.Analise;
import com.casacultural.filme.model.Filme;
import com.casacultural.filme.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author GFS_Mac
 */
@Controller
@RequestMapping("/analise")
public class AnaliseController {

    @Autowired
    private FilmeService filmeService;

    @PostMapping("/salvar/{filmeId}")
    public String adicionarAnalise(@PathVariable int filmeId, @ModelAttribute Analise novaAnalise) {
        Filme filme = filmeService.buscarPorId(filmeId);
        if (filme != null) {
            filme.adicionarAnalise(novaAnalise);
            filmeService.salvar(filme);
        }
        return "redirect:/filme/detalhe/" + filmeId;
    }
}
