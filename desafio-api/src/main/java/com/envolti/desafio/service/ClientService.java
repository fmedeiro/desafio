package com.envolti.desafio.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.envolti.desafio.model.Client;
import com.envolti.desafio.repository.Clients;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientService {
	
	@Autowired
	private Clients clients;
	
	public ResponseEntity<Client> create(Client client) {
		try {
			setDates(client, LocalDateTime.now());
			
			ResponseEntity<Client> clientAlreadyExits = find(client.getId());
			
			if (clientAlreadyExits.getBody() != null) {
				setRepeatedCpfViolation(client);
			} else {
				return ResponseEntity.status(201).body(clients.save(client));
			}
        } catch (DataIntegrityViolationException ex) {
        	setRepeatedCpfViolation(client);
        	log.error(ex.getMessage(), ex);
        } catch (Exception ex) {
        	log.error(ex.getMessage(), ex);
        }
		resetDates(client);
		
		return ResponseEntity.unprocessableEntity().body(client);
	}
	
	public ResponseEntity<Client> delete(Long id) {
		try {
			Optional<Client> client = clients.findById(id);
			
			if (client.isEmpty()) {
				return ResponseEntity.notFound().build();
			} else {
				clients.delete(client.get());
				
				return ResponseEntity.status(204).build();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
        }
		return ResponseEntity.unprocessableEntity().build();
	}
	
	public ResponseEntity<Client> deleteAll() {
		try {
			if (clients.findAll().isEmpty()) {
				return ResponseEntity.notFound().build();
			} else {
				clients.deleteAll();
				
				return ResponseEntity.status(204).build();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
        }
		return ResponseEntity.unprocessableEntity().build();
	}
		
	public ResponseEntity<Client> find(Long id) {
		try {
			Optional<Client> client = clients.findById(id);
			
			if (client.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(client.get());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
        }
		return ResponseEntity.unprocessableEntity().build();
	}
	
	public ResponseEntity<List<Client>> list() {
		try {
			List<Client> allClients = clients.findAll(Sort.by(Sort.Direction.ASC, "nome"));
			
			if (allClients.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(allClients);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
        }
		return ResponseEntity.unprocessableEntity().build();
	}
	
	private void resetDates(Client client) {
		client.setDataCadastro(null);
		client.setDataUltimaAlteracao(null);
	}
	
	private void setDates(Client client, LocalDateTime date) {
		client.setDataCadastro(date);
		client.setDataUltimaAlteracao(LocalDateTime.now());
	}
	
	private void setRepeatedCpfViolation(Client client) {
		client.setCpf(client.getCpf().concat(" -> Violação: CPF já cadastrado!"));
	}
	
	public ResponseEntity<Client> update(Client client) {
		try {
			Optional<Client> clientDb = clients.findById(client.getId());
			
			if (clientDb.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			
			setDates(client, clientDb.get().getDataCadastro());
			
			return ResponseEntity.status(204).body(clients.save(client));
        } catch (DataIntegrityViolationException ex) {
        	setRepeatedCpfViolation(client);
        	log.error(ex.getMessage(), ex);
        } catch (Exception ex) {
        	log.error(ex.getMessage(), ex);
        }
		resetDates(client);
		
		return ResponseEntity.unprocessableEntity().body(client);
	}
}
