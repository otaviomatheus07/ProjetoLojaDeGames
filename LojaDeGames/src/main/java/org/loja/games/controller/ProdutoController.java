package org.loja.games.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.loja.games.model.Produto;
import org.loja.games.repository.CategoriaRepository;
import org.loja.games.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController // para definir que é uma classe controller
@RequestMapping("/jogos")
@CrossOrigin(origins = " * ", allowedHeaders = " * ") // define que ira aceitar requisicoes de qualquer origem
public class ProdutoController {
	@Autowired // define que o proprio spring instancie os objetos
	private ProdutoRepository repository;

	@Autowired // define que o proprio spring instancie os objetos
	private CategoriaRepository categoriaRepository;

	@GetMapping("/titulo") // busca tudo que estiver dentro de postagem
	public ResponseEntity<List<Produto>> buscartudo() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}") // busca tudo pelo ID
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return repository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/titulo/{titulo}") // Busca tudo pelo Titulo
	public ResponseEntity<List<Produto>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}

	@PostMapping // Insere novos Dados
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
		if (categoriaRepository.existsById(produto.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@PutMapping // Atualiza os dados que contem no banco de dados
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {
		if (repository.existsById(produto.getId())) {
			if (categoriaRepository.existsById(produto.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {

		Optional<Produto> produto = repository.findById(id);

		if (produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		repository.deleteById(id);
	}

}
