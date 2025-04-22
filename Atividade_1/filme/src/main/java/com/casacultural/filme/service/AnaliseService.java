/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.casacultural.filme.service;

import com.casacultural.filme.model.Analise;
import com.casacultural.filme.repository.AnaliseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author GFS_Mac
 */
@Service
public class AnaliseService {

    @Autowired
    private AnaliseRepository analiseRepository;

    public List<Analise> listarPorFilme(int filmeId) {
        return analiseRepository.findByFilmeId(filmeId);
    }

    public Analise salvar(Analise analise) {
        return analiseRepository.save(analise);
    }

    public Analise burcarPorId(int id) {
        return analiseRepository.findById(id).orElse(null);
    }
}
