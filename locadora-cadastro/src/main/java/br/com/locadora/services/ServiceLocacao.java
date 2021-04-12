package br.com.locadora.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.entity.Cliente;
import br.com.locadora.entity.Filme;
import br.com.locadora.entity.Locacao;
import br.com.locadora.entity.LocacaoItens;
import br.com.locadora.repository.RepositoryLocacao;

@Service
public class ServiceLocacao {
	
	@Autowired
	private RepositoryLocacao repositoryLocacao;
	
	@Autowired
	private ServiceLocacaoItens serviceLocacaoItens;
	
	@Autowired
	private ServiceCliente serviceCliente;
	
	@Autowired 
	private ServiceFilme serviceFilmes;
	
	
	public Locacao salvarLocacao(Locacao locacao) {
		Cliente cliente = serviceCliente.pesquisarCLiente(locacao.getIdCliente().getId());
		locacao.setIdCliente(cliente);
		locacao.setPendente(Boolean.TRUE);
		Locacao locacaoSalva = repositoryLocacao.save(locacao);
		serviceLocacaoItens.salvarLocacalItem(locacaoSalva);
		return locacaoSalva;
	}
	
	public List<Locacao> listLocacoesPendentes(Long idCliente){
		return repositoryLocacao.listLocacoesPendentes(idCliente);
	}


	public List<Locacao> listLocacoes() {
		return repositoryLocacao.findAll();
	}

	public Locacao devolverFilmes(Locacao locacao) {
		Optional<Locacao> objetoLocacaoRecuperado = repositoryLocacao.findById(locacao.getIdLocacao());
		if (objetoLocacaoRecuperado.isPresent()) {
			locacao = objetoLocacaoRecuperado.get();
		}
		locacao.setPendente(Boolean.FALSE);
		List<LocacaoItens> listLocacaoItens = serviceLocacaoItens.listarLocacaoItens(locacao);
		List<Filme> idsFilmes = recuperaIdsFIlmes(listLocacaoItens);
		serviceFilmes.devolverFilmes(idsFilmes);
		return repositoryLocacao.save(locacao);
	}

	private List<Filme> recuperaIdsFIlmes(List<LocacaoItens> listLocacaoItens) {
		List<Long> idsFilmes = new ArrayList<>();
		listLocacaoItens.forEach(locaItens -> {
			idsFilmes.add(locaItens.getListFilmes().getIdFilme());
		});
		return serviceFilmes.listaFilmes(idsFilmes);
	}

}
