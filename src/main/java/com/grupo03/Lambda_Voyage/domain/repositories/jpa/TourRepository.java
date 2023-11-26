package com.grupo03.Lambda_Voyage.domain.repositories.jpa;

import com.grupo03.Lambda_Voyage.domain.entities.jpa.TourEntity;
import org.springframework.data.repository.CrudRepository;


public interface TourRepository extends CrudRepository<TourEntity, Long> {


}
