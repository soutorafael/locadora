package br.com.locadora.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locadora.entity.Cliente;
import br.com.locadora.exception.ExceptionCliente;
import br.com.locadora.exception.ExceptionCpfInvalido;
import br.com.locadora.repository.RepositoryCliente;

@Service
public class ServiceCliente {
	
	@Autowired
	private RepositoryCliente repositoryCliente;
	
	public Cliente salvarCliente(Cliente cliente) {
		validarCliente(cliente);
		validarIdade(cliente);
		return repositoryCliente.save(cliente);
	}

	private void validarIdade(Cliente cliente) {
		LocalDate dataAgora = LocalDate.now();
		LocalDate dataNascimento = cliente.getDataNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Period period = Period.between(dataAgora, dataNascimento);
	    int diff = Math.abs(period.getYears());
	    if (diff < 18) {
	    	throw new ExceptionCliente("Usuário menor de idade.");
	    }
		
	}

	private void validarCliente(Cliente cliente) {
		if (cliente.getCpf() == null)
			throw new ExceptionCpfInvalido("CPF em branco");
		if (cliente.getCpf().length() < 11)
			throw new ExceptionCpfInvalido("CPF inválido");
	}

	public List<Cliente> listarClientes() {
		return repositoryCliente.findAll();
	}
	
	public Cliente pesquisarCLiente(Long id) {
		Optional<Cliente> cliente = repositoryCliente.findById(id);
		if (!cliente.isPresent())
			throw new ExceptionCliente("Usuário nao encontrado.");
		return cliente.get();
	}
	
	public void deletarCLiente(Long id) {
		Cliente cliente = pesquisarCLiente(id);
		if (cliente != null)
			repositoryCliente.deleteById(cliente.getId());
	}
	
	public Cliente alterarCliente(Cliente cliente) {
		return repositoryCliente.save(cliente);
	}

}
