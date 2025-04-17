/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.casacultural.filme.repository;

import com.casacultural.filme.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author GFS_Mac
 */
@Repository
public interface FilmeRepository extends JpaRepository<Filme, Integer>{
    
}
