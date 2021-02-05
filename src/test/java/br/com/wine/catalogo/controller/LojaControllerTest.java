package br.com.wine.catalogo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LojaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void findByCep() throws Exception {
        Long cep = 90000001L;
        mvc.perform(MockMvcRequestBuilders.get("/"+cep).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"nome\":\"Geraldo Henrique\",\"endereco\":\"Rua Um\",\"numero\":\"123\",\"bairro\":\"Centro\",\"cidade\":\"Santos\",\"cep\":\"00000-000\",\"contato\":\"Henrique\",\"email\":\"teste@teste.com\",\"telefone\":\"(47) 0000-0000\",\"observacao\":\"Teste\"}")));
    }

    @Test
    public void save() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content("{\"CODIGO_LOJA\": \"LOJA_PINHEIROS\", \"FAIXA_INICIO\": 90000001, \"FAIXA_FIM\": 100000000}"))
                .andExpect(status().isCreated());
    }
}