package com.generation.lojadegames.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.generation.lojadegames.model.Produto;
import com.generation.lojadegames.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin (origins = "*", allowedHeaders = "*")

public class ProdutoController {
	
	@Autowired 
	private ProdutoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> pegarProduto(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> trazerId(@PathVariable Long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
		
	}
	//teste
	@GetMapping("/nomeDoJogo/{nome}")
		public ResponseEntity<List<Produto>> trazerNome(@PathVariable String nomeDoJogo){
			return ResponseEntity.ok(repository.findAllByNomeDoJogoContainingIgnoreCase(nomeDoJogo));
		}
	@PostMapping
	public ResponseEntity<Produto> postar(@Valid @RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(produto));
	}
	
	@PutMapping
	public ResponseEntity<Produto> atualizar (@Valid @RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
	}
	@DeleteMapping ("/{id}")
	public void deletar(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
