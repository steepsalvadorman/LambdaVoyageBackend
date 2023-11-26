package com.grupo03.Lambda_Voyage.domain.repositories.jpa;

import com.grupo03.Lambda_Voyage.domain.entities.jpa.ReservationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;


public interface ReservationRepository extends CrudRepository<ReservationEntity, UUID> {


}
