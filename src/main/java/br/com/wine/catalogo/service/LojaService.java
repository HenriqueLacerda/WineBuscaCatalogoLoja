package br.com.wine.catalogo.service;

import br.com.wine.catalogo.dto.LojaRequestDto;
import br.com.wine.catalogo.dto.LojaResponseDto;
import br.com.wine.catalogo.entity.Loja;
import br.com.wine.catalogo.repository.LojaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LojaService {

    private final LojaRepository lojaRepository;

    public List<LojaResponseDto> listar() {
        ModelMapper modelMapper = new ModelMapper();
        List<Loja> lojas = lojaRepository.findAll();

        List<LojaResponseDto> lojasDtos = lojas
                .stream()
                .map(user -> modelMapper.map(user, LojaResponseDto.class))
                .collect(Collectors.toList());
        return lojasDtos;
    }

    public Loja buscarPorId(long id) {
        return lojaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Loja não encontrada"));
    }

    public LojaResponseDto buscarPorCep(long cep) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<Loja> loja = lojaRepository.findLojaByFaixaCep(cep);
        if (loja.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        LojaResponseDto lojaDTO = modelMapper.map(loja.get(), LojaResponseDto.class);
        return lojaDTO;
    }

    public LojaResponseDto salvar(LojaRequestDto lojaDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Loja loja = modelMapper.map(lojaDTO, Loja.class);
        faixaCepValida(loja);
        loja = lojaRepository.save(loja);
        LojaResponseDto lojaResponseDto = modelMapper.map(loja, LojaResponseDto.class);
        return  lojaResponseDto;
    }

    public void alterar(LojaResponseDto lojaDTO) {
        Loja loja = this.buscarPorId(lojaDTO.getId());
        loja.setCodigoLoja(lojaDTO.getCodigoLoja());
        loja.setFaixaInicio(lojaDTO.getFaixaInicio());
        loja.setFaixaFim(lojaDTO.getFaixaFim());
        faixaCepValida(loja);

        lojaRepository.save(loja);
    }

    public void deletar(Long id) {
        lojaRepository.delete(this.buscarPorId(id));
    }

    private void faixaCepValida(Loja loja) {
        Optional<Loja> lojaExisteFaixaInicio = lojaRepository.findLojaByFaixaCep(loja.getFaixaInicio());
        Optional<Loja> lojaExisteFaixaFim= lojaRepository.findLojaByFaixaCep(loja.getFaixaFim());
        //
        if (loja.getFaixaInicio() > loja.getFaixaFim()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Faixa de CEP inválida");
        }
        if (!lojaExisteFaixaInicio.isEmpty() || !lojaExisteFaixaFim.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Faixa de CEP já Cadastrada");
        }
        //
    }
}
