/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casacultural.filme.service;

import com.casacultural.filme.model.Analise;
import com.casacultural.filme.model.Filme;
import com.casacultural.filme.repository.FilmeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author GFS_Mac
 */
@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    public Filme salvar(Filme filme) {
        return filmeRepository.save(filme);
    }

    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }

    public Filme buscarPorId(int id) {
        return filmeRepository.findById(id).orElse(null);
    }

    public void excluir(int id) {
        filmeRepository.deleteById(id);
    }

    public Filme adicionarAnalise(int filmeId, Analise novaAnalise) {
        Filme filme = filmeRepository.findById(filmeId)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));
        filme.adicionarAnalise(novaAnalise);
        return filmeRepository.save(filme);
    }

}
