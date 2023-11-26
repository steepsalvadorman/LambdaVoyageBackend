package com.grupo03.Lambda_Voyage.domain.repositories.jpa;

import com.grupo03.Lambda_Voyage.domain.entities.jpa.CustomerEntity;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {


}
