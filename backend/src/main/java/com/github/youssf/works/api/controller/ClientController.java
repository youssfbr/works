package com.github.youssf.works.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.youssf.works.domain.model.Client;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@GetMapping
	public ResponseEntity<List<Client>> findAll() {
		var client1 = new Client(1L, "Teste1", "teste1@email.com", "1234567");
		var client2 = new Client(2L, "Teste2", "teste2@email.com", "1234567");
		
		return ResponseEntity.ok(Arrays.asList(client1, client2));
	}
	

}
