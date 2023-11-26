package com.grupo03.Lambda_Voyage.infraestructure.abstract_services;

import com.grupo03.Lambda_Voyage.api.models.responses.FlyResponse;

import java.util.Set;

public interface IFlyService extends CatalogService<FlyResponse> {

    Set<FlyResponse> readByOriginDestiny(String origin, String destiny);
}
