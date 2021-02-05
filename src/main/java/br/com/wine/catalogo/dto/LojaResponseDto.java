package br.com.wine.catalogo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LojaResponseDto {

    @JsonProperty("ID")
    private Long id;
    @JsonProperty("CODIGO_LOJA")
    private String codigoLoja;
    @JsonProperty("FAIXA_INICIO")
    private Long faixaInicio;
    @JsonProperty("FAIXA_FIM")
    private Long faixaFim;

}
