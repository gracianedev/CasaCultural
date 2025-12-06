/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casacultural.filme.controller;

import com.casacultural.filme.model.Analise;
import com.casacultural.filme.model.Filme;
import com.casacultural.filme.service.AnaliseService;
import com.casacultural.filme.service.FilmeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author GFS_Mac
 */

@RestController
@RequestMapping("/api/analise")
@CrossOrigin(origins = "*")
public class AnaliseAPIController {
    
    @Autowired
    private AnaliseService analiseService;
    
    @Autowired
    private FilmeService filmeService;
    
    @GetMapping("/{filmeId}")
    public List<Analise> listarTodos(@PathVariable int filmeId) {
        return analiseService.listarPorFilme(filmeId);
    }
    
    @PostMapping("/filme/{filmeId}")
    public Filme adicionarAnalise(@PathVariable int filmeId, @RequestBody Analise analise) {
    return filmeService.adicionarAnalise(filmeId, analise);
}

    
    @PutMapping("/{id}")
    public Analise atualizarAnalise(@PathVariable int id, @RequestBody Analise analiseAtualizada) {
        return analiseService.salvar(analiseAtualizada);
    }
    
    @DeleteMapping("/{id}")
    public void deletarAnalise(@PathVariable int id) {
        analiseService.excluir(id);
    }
    
}
