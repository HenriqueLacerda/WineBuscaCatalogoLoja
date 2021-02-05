package br.com.wine.catalogo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "lojas")
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private long id;
    @Column(name = "codigo_loja", nullable = false)
    private String codigoLoja;
    @Column(name = "faixa_inicio", nullable = false)
    private long faixaInicio;
    @Column(name = "faixaFim", nullable = false)
    private long faixaFim;

}
