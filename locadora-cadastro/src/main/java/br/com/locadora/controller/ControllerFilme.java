package br.com.locadora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.locadora.entity.Filme;
import br.com.locadora.services.ServiceFilme;

@RestController
@RequestMapping(value = "/filmes")
public class ControllerFilme {
	
	@Autowired
	private ServiceFilme serviceFilme;
	
	@PostMapping
	public ResponseEntity<Filme> salvarFilme(@RequestBody Filme filme) {
		return ResponseEntity.ok(serviceFilme.salvarFilme(filme));
	}
	
	@GetMapping
	public List<Filme> listFilmes(){
		return serviceFilme.listFilme();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Filme> pesquisarFilme(@PathVariable("id") Long idFilme) {
		return ResponseEntity.ok(serviceFilme.pesquisarFilme(idFilme));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Filme> deletarFilme(@PathVariable("id") Long idFilme) {
		serviceFilme.deletarFilme(idFilme);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping
	public ResponseEntity<Filme> alterarFilme(@RequestBody Filme filme){
		return ResponseEntity.ok(serviceFilme.alterarFilme(filme));
	}
}
