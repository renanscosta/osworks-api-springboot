package com.algaworks.osworks.api.controller;

import com.algaworks.osworks.domain.model.*;

import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
	
	@GetMapping("/clientes")
	public List<Cliente> lista() {
		
		var lista = new ArrayList<Cliente>();
		Long id = 0L;
		
		for(long i=0; i<5; i++)
		{
			id = i+1;
			var cliente = new Cliente();
			cliente.setEmail(String.format("renan%x.costa.ti@gmail.com", id));
			cliente.setId(id);
			cliente.setNome(String.format("Renan 0%x", id));
			cliente.setTelefone(String.valueOf(id));
			lista.add(cliente);
		}
		
		return lista;
	}

}
