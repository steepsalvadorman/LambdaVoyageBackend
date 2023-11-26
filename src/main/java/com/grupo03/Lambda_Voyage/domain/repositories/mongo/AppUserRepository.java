package com.grupo03.Lambda_Voyage.domain.repositories.mongo;

import com.grupo03.Lambda_Voyage.domain.entities.documents.AppUserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AppUserRepository extends MongoRepository<AppUserDocument, String> {

    Optional<AppUserDocument> findByUsername(String username);

}
