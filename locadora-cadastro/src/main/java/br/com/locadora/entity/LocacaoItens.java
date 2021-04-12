package br.com.locadora.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "locacaoitens")
public class LocacaoItens implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long idLocacaoItens;
	
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name="idFilme")
	private Filme listFilmes;
	
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name="id_locacao")
	private Locacao idLocacao;

	public Long getIdLocacaoItens() {
		return idLocacaoItens;
	}

	public void setIdLocacaoItens(Long idLocacaoItens) {
		this.idLocacaoItens = idLocacaoItens;
	}


	public Locacao getIdLocacao() {
		return idLocacao;
	}

	public void setIdLocacao(Locacao idLocacao) {
		this.idLocacao = idLocacao;
	}

	public Filme getListFilmes() {
		return listFilmes;
	}

	public void setListFilmes(Filme listFilmes) {
		this.listFilmes = listFilmes;
	}
	
}
