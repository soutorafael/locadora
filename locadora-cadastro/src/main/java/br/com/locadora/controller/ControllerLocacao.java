package br.com.locadora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.locadora.entity.Locacao;
import br.com.locadora.services.ServiceLocacao;

@RestController
@RequestMapping(value = "/locacao")
public class ControllerLocacao {
	
	@Autowired
	private ServiceLocacao serviceLocacao;
	
	@PostMapping
	public Locacao alugarFilme(@RequestBody Locacao locacao) {
		return serviceLocacao.salvarLocacao(locacao);
	}
	
	@GetMapping
	public List<Locacao> listLocacao(){
		return serviceLocacao.listLocacoes();
	}
	
	@GetMapping("/listLocacaoPendente/{id}")
	public List<Locacao> listLocacaoPendente(@PathVariable("id") Long id){
		return serviceLocacao.listLocacoesPendentes(id);
	}
	
	@PostMapping("/devolucaoFilmes")
	public Locacao devolverFilmes(@RequestBody Locacao locacao){
		return serviceLocacao.devolverFilmes(locacao);
	}

}
