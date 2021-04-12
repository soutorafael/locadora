package br.com.locadora.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.entity.Filme;
import br.com.locadora.entity.Locacao;
import br.com.locadora.entity.LocacaoItens;
import br.com.locadora.repository.RepositoryLocacaoItem;

@Service
public class ServiceLocacaoItens {
	
	@Autowired
	private ServiceFilme serviceFilme;
	
	@Autowired
	private RepositoryLocacaoItem repositoryLocacaoItem;
	
	public void salvarLocacalItem(Locacao locacao) {
		LocacaoItens locacaoItens = new LocacaoItens();
		locacaoItens.setIdLocacao(locacao);
		
		List<Filme> listFilmes = serviceFilme.listaFilmesPorID(locacao.getListFilmes());
		listFilmes.forEach(iFIlme -> {
			locacaoItens.setListFilmes(iFIlme);
			repositoryLocacaoItem.save(locacaoItens);
		});
	}

	public List<LocacaoItens> listarLocacaoItens(Locacao locacao) {
		return repositoryLocacaoItem.listarLocacaoItens(locacao.getIdLocacao());
	}
}
