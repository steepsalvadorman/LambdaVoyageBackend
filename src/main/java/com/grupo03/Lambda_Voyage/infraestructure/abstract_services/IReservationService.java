package com.grupo03.Lambda_Voyage.infraestructure.abstract_services;

import com.grupo03.Lambda_Voyage.api.models.request.ReservationRequest;
import com.grupo03.Lambda_Voyage.api.models.responses.ReservationResponse;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public interface IReservationService extends CrudService<ReservationRequest, ReservationResponse, UUID>{


    BigDecimal findPrice(Long hotelId, Currency currency);


}
