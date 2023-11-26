package com.grupo03.Lambda_Voyage.infraestructure.abstract_services;

import com.grupo03.Lambda_Voyage.api.models.responses.HotelResponse;

import java.util.Set;

public interface IHotelService extends CatalogService<HotelResponse>{

    Set<HotelResponse> readByRating(Integer rating);
}
