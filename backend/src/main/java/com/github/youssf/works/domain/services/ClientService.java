package com.github.youssf.works.domain.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.youssf.works.domain.models.Client;
import com.github.youssf.works.domain.repositories.ClientRepository;
import com.github.youssf.works.domain.services.exceptions.DatabaseException;
import com.github.youssf.works.domain.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	private final ClientRepository repository;

	@Autowired
	public ClientService(ClientRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public List<Client> findByNameContaining(String name) {
		return repository.findByNameContaining(name);
	}

	@Transactional(readOnly = true)
	public Client findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id " + id + " not found!"));
	}

	@Transactional
	public Client insert(Client client) {
		return repository.save(client);
	}

	@Transactional
	public Client update(Long id, Client client) {
		try {
			Client entity = repository.getOne(id);
			entity.setName(client.getName());
			entity.setEmail(client.getEmail());
			entity.setTelephone(client.getTelephone());
			return repository.save(entity);
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id " + id + " not found!");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} 
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id " + id + " not found!");
		} 
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity Violation!");
		}
	}
}
