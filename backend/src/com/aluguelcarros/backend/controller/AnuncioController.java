package com.aluguelcarros.backend.controller; // <-- PACOTE CORRIGIDO COM BASE NA SUA ESTRUTURA REAL

// IMPORTS DO SPRING FRAMEWORK
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

// IMPORTS DOS SEUS MODELOS E REPOSITÓRIOS (AGORA COM OS CAMINHOS CORRETOS E AJUSTADOS)
import com.aluguelcarros.backend.model.Anuncio;
import com.aluguelcarros.backend.model.Carro;
import com.aluguelcarros.backend.repository.AnuncioRepository;
import com.aluguelcarros.backend.repository.CarroRepository;
// etc.
// IMPORTS DE CLASSES UTILITY DO JAVA
import java.util.List;
import java.util.Optional;

// A partir daqui, o restante do seu código AnuncioController
// public class AnuncioController {
// ...
// A partir daqui, o restante do seu código AnuncioController
// public class AnuncioController {
// ...
@RestController
@RequestMapping("/anuncios")
public class AnuncioController {

    private final AnuncioRepository anuncioRepository;
    private final CarroRepository carroRepository;

    public AnuncioController(AnuncioRepository anuncioRepository, CarroRepository carroRepository) {
        this.anuncioRepository = anuncioRepository;
        this.carroRepository = carroRepository;
    }

    @GetMapping
    public List<Anuncio> listarAnuncios() {
        return anuncioRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Anuncio> salvarAnuncio(@RequestBody Anuncio anuncio) {
        // Valida se o carro existe
        Carro carro = carroRepository.findById(anuncio.getCarro().getId())
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));

        Anuncio novoAnuncio = anuncioRepository.save(anuncio);
        return new ResponseEntity<>(novoAnuncio, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anuncio> buscarAnuncioPorId(@PathVariable Long id) {
        Optional<Anuncio> anuncio = anuncioRepository.findById(id);
        return anuncio.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Anuncio> atualizarAnuncio(@PathVariable Long id, @RequestBody Anuncio anuncioDetalhes) {
        return anuncioRepository.findById(id)
                .map(anuncioExistente -> {
                    anuncioExistente.setTitulo(anuncioDetalhes.getTitulo()); // Exemplo: atualize os campos necessários
                    anuncioExistente.setDescricao(anuncioDetalhes.getDescricao());
                    // ... outros campos ...
                    // Se o carro puder ser atualizado, também faça a validação
                    if (anuncioDetalhes.getCarro() != null && anuncioDetalhes.getCarro().getId() != null) {
                        Carro carro = carroRepository.findById(anuncioDetalhes.getCarro().getId())
                                .orElseThrow(() -> new RuntimeException("Carro não encontrado para atualização"));
                        anuncioExistente.setCarro(carro);
                    }

                    Anuncio anuncioAtualizado = anuncioRepository.save(anuncioExistente);
                    return ResponseEntity.ok(anuncioAtualizado);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarAnuncio(@PathVariable Long id) {
        return anuncioRepository.findById(id)
                .map(anuncio -> {
                    anuncioRepository.delete(anuncio);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
