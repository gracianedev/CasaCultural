/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casacultural.filme.controller;

import com.casacultural.filme.model.Filme;
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
@RequestMapping("/api/filme")
@CrossOrigin(origins = "*")
public class FilmeAPIController {

    @Autowired
    private FilmeService filmeService;
    
    @GetMapping
    public List<Filme> listarTodos() {
        return filmeService.listarTodos();
    }
    
    @GetMapping("/{id}")
    public Filme buscarPorId(@PathVariable int id) {
        return filmeService.buscarPorId(id);
    }
    
    @PostMapping
    public Filme criarFilme(@RequestBody Filme filme) {
        return filmeService.salvar(filme);
    }
    
    @PutMapping("/{id}")
    public Filme atualizarFilme(@PathVariable int id, @RequestBody Filme filmeAtualizado) {
        return filmeService.salvar(filmeAtualizado);
    }
    
    @DeleteMapping("/{id}")
    public void deletarFilme(@PathVariable int id) {
        filmeService.excluir(id);
    }
    
}
