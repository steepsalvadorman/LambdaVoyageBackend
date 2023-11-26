package com.grupo03.Lambda_Voyage.domain.repositories.jpa;

import com.grupo03.Lambda_Voyage.domain.entities.jpa.TicketEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;


public interface TicketRepository extends CrudRepository<TicketEntity, UUID> {


}
