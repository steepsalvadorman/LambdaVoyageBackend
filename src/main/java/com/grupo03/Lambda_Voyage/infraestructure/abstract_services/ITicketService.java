package com.grupo03.Lambda_Voyage.infraestructure.abstract_services;

import com.grupo03.Lambda_Voyage.api.models.request.TicketRequest;
import com.grupo03.Lambda_Voyage.api.models.responses.TicketResponse;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public interface ITicketService extends CrudService<TicketRequest, TicketResponse, UUID>{

    BigDecimal findPrice(Long flyId, Currency currency);
}
