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

import br.com.locadora.entity.Cliente;
import br.com.locadora.services.ServiceCliente;

@RestController
@RequestMapping(value = "/cliente")
public class ControllerCliente {
	
	@Autowired
	private ServiceCliente serviceCliente;
	
	@PostMapping
	public Cliente salvarCliente(@RequestBody Cliente cliente) {
		return serviceCliente.salvarCliente(cliente);
	}
	
	@GetMapping
	public List<Cliente> listarClientes() {
		return serviceCliente.listarClientes();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> pesquisarCliente(@PathVariable("id") Long id){
		return ResponseEntity.ok(serviceCliente.pesquisarCLiente(id));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cliente> deletarCliente(@PathVariable("id") Long id){
		serviceCliente.deletarCLiente(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping
	public ResponseEntity<Cliente> alterarCliente(@RequestBody Cliente cliente) {
		return ResponseEntity.ok(serviceCliente.alterarCliente(cliente));
	}

}
