/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.casacultural.filme.repository;

import com.casacultural.filme.model.Analise;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author GFS_Mac
 */
@Repository
public interface AnaliseRepository extends JpaRepository<Analise, Integer> {

    List<Analise> findByFilmeId(int filmeId);
}
