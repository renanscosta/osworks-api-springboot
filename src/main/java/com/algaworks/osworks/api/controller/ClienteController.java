package com.algaworks.osworks.api.controller;

import com.algaworks.osworks.domain.model.*;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import com.sun.el.stream.Optional;

import jdk.jfr.BooleanFlag;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	/*@PersistenceContext
	private EntityManager manager;//equivalente ao dbcontext do entity framework
	*/
	@Autowired //define que receberá uma Injeção de dependencia. O spring que "se vira" para criar
	private ClienteRepository repository;
	
	@GetMapping
	public List<Cliente> listar() {
		return repository.findAll();
		
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		java.util.Optional<Cliente> cliente = repository.findById(clienteId);
		
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cliente adicionar(@RequestBody Cliente cliente) {
				
		return repository.save(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente) {
				
		if(!repository.existsById(clienteId))
			return ResponseEntity.notFound().build();
		
		cliente.setId(clienteId);
		cliente =repository.save(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		
		if(!repository.existsById(clienteId))
			return ResponseEntity.notFound().build();
		
		repository.deleteById(clienteId);
		return ResponseEntity.noContent().build();
	}
	

}
