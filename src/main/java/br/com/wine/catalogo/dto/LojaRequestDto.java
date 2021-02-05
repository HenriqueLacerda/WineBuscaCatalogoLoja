package br.com.wine.catalogo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LojaRequestDto {

    @NotNull(message = "O Código da Loja não pode ser null")
    @NotEmpty(message = "O Código da Loja não pode ser vazio")
    @JsonProperty("CODIGO_LOJA")
    private String codigoLoja;
    @NotNull(message = "O inicio da faixa de cep não pode ser null")
    @JsonProperty("FAIXA_INICIO")
    private Long faixaInicio;
    @NotNull(message = "O fim da faixa de cep não pode ser null")
    @JsonProperty("FAIXA_FIM")
    private Long faixaFim;

}
