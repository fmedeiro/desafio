package com.envolti.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.envolti.desafio.model.Client;

public interface Clients extends JpaRepository<Client, Long> {

}
