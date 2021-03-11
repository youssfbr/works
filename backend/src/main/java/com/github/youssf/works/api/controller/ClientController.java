package com.github.youssf.works.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.youssf.works.domain.models.Client;
import com.github.youssf.works.domain.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
		
	private final ClientService service;	
	
	@Autowired
	public ClientController(ClientService service) {
		this.service = service;
	}	
	
	@GetMapping
	public ResponseEntity<List<Client>> findAll() {		
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Client>> findByNameContaining(@PathVariable String name) {		
		return ResponseEntity.ok(service.findByNameContaining(name));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id) {		
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Client> insert(@Valid @RequestBody Client client) {
		client = service.insert(client);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(client.getId()).toUri();
		
		return ResponseEntity.created(uri).body(client);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Client> update(@PathVariable Long id, @Valid @RequestBody Client client) {		
		return ResponseEntity.ok(service.update(id, client));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
