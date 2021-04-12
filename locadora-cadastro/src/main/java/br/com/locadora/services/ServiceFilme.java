package br.com.locadora.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.entity.Filme;
import br.com.locadora.exception.ExceptionFilme;
import br.com.locadora.repository.RepositoryFilme;

@Service
public class ServiceFilme {
	
	
	@Autowired
	private RepositoryFilme repositoryFilme;
	
	public Filme salvarFilme(Filme filme) {
		filme.setDisponivel(Boolean.TRUE);
		return repositoryFilme.save(filme);
	}
	
	public List<Filme> listFilme() {
		return repositoryFilme.findAll();
	}
	
	public Filme pesquisarFilme(Long id) {
		Optional<Filme> filmet = repositoryFilme.findById(id);
		if (!filmet.isPresent())
			throw new ExceptionFilme("Filme nao existe");
		return filmet.get();
	}
	
	public Filme alterarFilme(Filme filme) {
		return repositoryFilme.save(filme);
	}
	
	public void deletarFilme(Long idFilme) {
		Filme filme = pesquisarFilme(idFilme);
		if (filme != null)
			repositoryFilme.deleteById(filme.getIdFilme());
	}

	public List<Filme> listaFilmesPorID(List<Long> listFilmes) {
		List<Filme> listFilme = repositoryFilme.listFilmes(listFilmes);
		
		if (listFilme.isEmpty())
			throw new ExceptionFilme("Nenhum filme cadastrado.");
		
		if (listFilme.size() > 5 )
			throw new ExceptionFilme(" locacao limitade até cinco filmes.");
		
		listFilme.forEach(filme -> {
			if (!filme.isDisponivel())
				throw new ExceptionFilme(filme.getNome() + " nao está disponível ");
			filme.setDisponivel(Boolean.FALSE);
			repositoryFilme.save(filme);
		});
		return listFilme;
	}

	public void devolverFilmes(List<Long> idsFilmes) {
		repositoryFilme.devolverFilmes(idsFilmes);
	}

}
