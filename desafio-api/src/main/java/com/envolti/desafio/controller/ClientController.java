package com.envolti.desafio.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.envolti.desafio.model.Client;
import com.envolti.desafio.service.ClientService;

@RestController
@RequestMapping("/desafio")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping
	@RequestMapping("/cadastrar")
	public ResponseEntity<Client> create(@RequestBody @Valid Client client) {	
		return clientService.create(client);
	}
	
	@GetMapping("/excluir/{id}")
	public ResponseEntity<Client> delete(@PathVariable Long id) {
		return clientService.delete(id);
	}
	
	@GetMapping("/excluir")
	public ResponseEntity<Client> deleteAll() {
		return clientService.deleteAll();
	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<Client> find(@PathVariable Long id) {
		return clientService.find(id);
	}
	
	@GetMapping
	@RequestMapping("/listar")
	public ResponseEntity<List<Client>> list() {
		return clientService.list();
	}
	
	@PutMapping
	@RequestMapping("/alterar")
	public ResponseEntity<Client> update(@RequestBody @Valid Client client) {	
		return clientService.update(client);
	}
}
