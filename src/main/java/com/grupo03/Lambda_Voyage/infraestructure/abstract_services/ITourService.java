package com.grupo03.Lambda_Voyage.infraestructure.abstract_services;

import com.grupo03.Lambda_Voyage.api.models.request.TourRequest;
import com.grupo03.Lambda_Voyage.api.models.responses.TourResponse;

import java.util.UUID;

public interface ITourService extends SimpleCrudService<TourRequest, TourResponse, Long>{
    void removeTicket(Long tourId, UUID tickerId);
    UUID addTicket(Long flyId, Long tourId);

    void removeReservation(Long tourId, UUID reservationId);
    UUID addReservation(Long reservationId, Long tourId, Integer totalDays);
}
