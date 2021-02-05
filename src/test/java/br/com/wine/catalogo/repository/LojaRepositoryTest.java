package br.com.wine.catalogo.repository;

import br.com.wine.catalogo.entity.Loja;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;

@DataJpaTest
@DisplayName("Testes para o candidato repository")
class LojaRepositoryTest {

    @Autowired
    private LojaRepository lojaRepository;

    @Test
    @DisplayName("Salvar loja com sucesso")
    public void test_SalvarLojaComSucesso() {
        Loja loja = criarLoja();
        Loja lojaSalva = lojaRepository.save(loja);
        Assertions.assertThat(lojaSalva).isNotNull();
        Assertions.assertThat(lojaSalva.getId()).isNotNull();
        Assertions.assertThat(lojaSalva.getCodigoLoja()).isEqualTo(loja.getCodigoLoja());
    }

    @Test
    @DisplayName("Buscar loja com sucesso")
    public void test_BuscarLojaComSucesso() {

        Loja loja = criarLoja();
        Loja lojaSalva = lojaRepository.save(loja);

        Assertions.assertThat(lojaSalva).isNotNull();
        Assertions.assertThat(lojaSalva.getId()).isNotNull();
        Assertions.assertThat(lojaSalva.getCodigoLoja()).isEqualTo(loja.getCodigoLoja());

        Optional<Loja> lojaBusca = lojaRepository.findLojaByFaixaCep(10000003L);

        Assertions.assertThat(lojaBusca.get()).isNotNull();
        Assertions.assertThat(lojaBusca.get().getId()).isNotNull();
        Assertions.assertThat(lojaBusca.get().getCodigoLoja()).isEqualTo(loja.getCodigoLoja());

    }

    private Loja criarLoja() {
        return new Loja().builder()
                .codigoLoja("LOJA_PINHEIROS")
                .faixaInicio(10000000)
                .faixaFim(20000000)
                .build();
    }

}