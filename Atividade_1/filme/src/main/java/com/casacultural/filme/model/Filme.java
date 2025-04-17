
package com.casacultural.filme.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

/**
 *
 * @author GFS_Mac
 */


@Data
@Entity
@Table(name="Filme")
@Component
public class Filme {
    
    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String sinopse;
    private String genero;
    private int ano;
    
    
    
    
}
