package br.com.wine.catalogo.repository;

import br.com.wine.catalogo.entity.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long> {

    @Query("FROM Loja WHERE :cep >= faixaInicio and :cep <= faixaFim")
    public Optional<Loja> findLojaByFaixaCep(long cep);

}
