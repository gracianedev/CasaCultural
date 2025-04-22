package com.casacultural.filme.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Component;

/**
 *
 * @author GFS_Mac
 */
@Data
@Entity
@Table(name = "Filme")
@Component
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String sinopse;
    private String genero;
    private int ano;

    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Analise> analise = new ArrayList<>();

    public void adicionarAnalise(Analise novaAnalise) {
        novaAnalise.setFilme(this);
        this.analise.add(novaAnalise);
    }

}
