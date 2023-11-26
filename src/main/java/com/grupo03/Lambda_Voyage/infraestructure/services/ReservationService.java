package com.grupo03.Lambda_Voyage.infraestructure.services;

import com.grupo03.Lambda_Voyage.api.models.request.ReservationRequest;
import com.grupo03.Lambda_Voyage.api.models.responses.HotelResponse;
import com.grupo03.Lambda_Voyage.api.models.responses.ReservationResponse;
import com.grupo03.Lambda_Voyage.domain.entities.jpa.ReservationEntity;
import com.grupo03.Lambda_Voyage.domain.repositories.jpa.CustomerRepository;
import com.grupo03.Lambda_Voyage.domain.repositories.jpa.HotelRepository;
import com.grupo03.Lambda_Voyage.domain.repositories.jpa.ReservationRepository;
import com.grupo03.Lambda_Voyage.infraestructure.abstract_services.IReservationService;
import com.grupo03.Lambda_Voyage.infraestructure.helpers.ApiCurrencyConnectorHelper;
import com.grupo03.Lambda_Voyage.infraestructure.helpers.BlackListHelper;
import com.grupo03.Lambda_Voyage.infraestructure.helpers.CustomerHelper;
import com.grupo03.Lambda_Voyage.infraestructure.helpers.EmailHelper;
import com.grupo03.Lambda_Voyage.util.enums.Tables;
import com.grupo03.Lambda_Voyage.util.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Objects;
import java.util.UUID;


@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ReservationService implements IReservationService {


    private final HotelRepository hotelRepository;
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;
    private final CustomerHelper customerHelper;
    private final BlackListHelper blackListHelper;
    private final ApiCurrencyConnectorHelper currencyConnectorHelper;
    private final EmailHelper emailHelper;
    @Override
    public ReservationResponse create(ReservationRequest request) {
        blackListHelper.isInBlackListCustomer(request.getIdClient());
        var hotel = this.hotelRepository.findById(request.getIdHotel()).orElseThrow(()-> new IdNotFoundException("hotel"));
        var customer = this.customerRepository.findById(request.getIdClient()).orElseThrow(()-> new IdNotFoundException("customer"));

        var reservationToPersist = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotel(hotel)
                .customer(customer)
                .totalDays(request.getTotalDays())
                .dateTimeReservation(LocalDateTime.now())
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
                .price(hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage)))
                .build();

        var reservationPersisted = reservationRepository.save(reservationToPersist);
        this.customerHelper.increase(customer.getDni(),ReservationService.class);
        if (Objects.nonNull(request.getEmail())) this.emailHelper.sendMail(request.getEmail(),customer.getFullName(), Tables.reservation.name());
        return this.entityToResponse(reservationPersisted);
    }

    @Override
    public ReservationResponse read(UUID id) {
        var reservationFromDB = this.reservationRepository.findById(id).orElseThrow(()-> new IdNotFoundException("reservation"));
        return this.entityToResponse(reservationFromDB);
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID id) {
        var hotel = this.hotelRepository.findById(request.getIdHotel()).orElseThrow(()-> new IdNotFoundException("hotel"));
        var reservationToUpdate = this.reservationRepository.findById(id).orElseThrow(()-> new IdNotFoundException("reservation"));

        reservationToUpdate.setHotel(hotel);
        reservationToUpdate.setTotalDays(request.getTotalDays());
        reservationToUpdate.setDateTimeReservation(LocalDateTime.now());
        reservationToUpdate.setDateStart(LocalDate.now());
        reservationToUpdate.setDateEnd(LocalDate.now().plusDays(request.getTotalDays()));
        reservationToUpdate.setPrice(hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage)));
        var reservationUpdated = this.reservationRepository.save(reservationToUpdate);
        log.info("Reservation updated with id {}", reservationUpdated.getId());
        return this.entityToResponse(reservationUpdated);
    }

    @Override
    public void delete(UUID id) {
        var reservationToDelete = reservationRepository.findById(id).orElseThrow(()-> new IdNotFoundException(Tables.reservation.name()));
        this.reservationRepository.delete(reservationToDelete);
    }

    @Override
    public BigDecimal findPrice(Long flyId, Currency currency) {
        var hotel = hotelRepository.findById(flyId).orElseThrow(()-> new IdNotFoundException("hotel"));
        var priceInDollars = hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage));
        if (currency.equals(Currency.getInstance("USD"))) return priceInDollars;
        var currencyDTO = this.currencyConnectorHelper.getCurrency(currency);
        log.info("API Currency in {}, response: {}",currencyDTO.getExchangeDate().toString(), currencyDTO.getRates());
        return priceInDollars.multiply(currencyDTO.getRates().get(currency));
    }
    private ReservationResponse entityToResponse(ReservationEntity entity){
        var response = new ReservationResponse();
        BeanUtils.copyProperties(entity,response);
        var hotelResponse = new HotelResponse();
        BeanUtils.copyProperties(entity.getHotel(), hotelResponse);
        response.setHotel(hotelResponse);
        return response;
    }

    public static final BigDecimal charges_price_percentage = BigDecimal.valueOf(0.20);

}
