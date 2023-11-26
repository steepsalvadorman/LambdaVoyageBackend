package com.grupo03.Lambda_Voyage.api.controllers;

import com.grupo03.Lambda_Voyage.api.models.request.ReservationRequest;
import com.grupo03.Lambda_Voyage.api.models.responses.ErrorsResponse;
import com.grupo03.Lambda_Voyage.api.models.responses.ReservationResponse;
import com.grupo03.Lambda_Voyage.infraestructure.abstract_services.IReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping(path = "reservation")
@AllArgsConstructor
@Tag(name = "Reservation")
public class ReservationController {

    private final IReservationService reservationService;
    @ApiResponse(responseCode = "400",
            description = "When the request have a field invalid we response this",
            content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorsResponse.class))
            }
    )
    @Operation(summary = "Save in system a reservation")
    @PostMapping
    public ResponseEntity<ReservationResponse> post(@Valid @RequestBody ReservationRequest request){
        return ResponseEntity.ok(reservationService.create(request));
    }

    @Operation(summary = "Return a reservation with id passed")
    @GetMapping(path = "{id}")
    public ResponseEntity<ReservationResponse> get(@PathVariable UUID id){
        return ResponseEntity.ok(reservationService.read(id));
    }

    @Operation(summary = "Update reservation with id passed")
    @PutMapping(path = "{id}")
    public ResponseEntity<ReservationResponse> put(@Valid @PathVariable UUID id, @RequestBody ReservationRequest request) {
        return ResponseEntity.ok(this.reservationService.update(request,id));
    }

    @Operation(summary = "Delete a reservation with id passed")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        this.reservationService.delete(id);
        return ResponseEntity.noContent().build() ;
    }


    @Operation(summary = "Return a reservation price given a hotel ID.")
    @GetMapping
    public ResponseEntity<Map<String, BigDecimal>> getReservationPrice
            (@RequestParam Long hotelId,
             @RequestHeader(required = false) Currency currency){
        if (Objects.isNull(currency)) currency = Currency.getInstance("USD");
        return ResponseEntity.ok(Collections.singletonMap("ticketPrice", this.reservationService.findPrice(hotelId, currency)));

    }
}
