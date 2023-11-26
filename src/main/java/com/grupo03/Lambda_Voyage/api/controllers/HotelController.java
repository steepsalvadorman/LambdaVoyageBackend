package com.grupo03.Lambda_Voyage.api.controllers;

import com.grupo03.Lambda_Voyage.api.models.responses.HotelResponse;
import com.grupo03.Lambda_Voyage.infraestructure.abstract_services.IHotelService;
import com.grupo03.Lambda_Voyage.util.annotations.Notify;
import com.grupo03.Lambda_Voyage.util.enums.SortType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(path = "hotel")
@AllArgsConstructor
@Tag(name = "Hotel")
public class HotelController {

    private final IHotelService hotelService;

    @Operation(summary = "Retrieve a paginated list of hotels that can be sorted.")
    @GetMapping
    @Notify(value = "GET hotel")
    public ResponseEntity<Page<HotelResponse>> getAll(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestHeader(required = false) SortType sortType){
        if(Objects.isNull(sortType)) sortType = SortType.NONE;
        var response = this.hotelService.readAll(page, size, sortType);
        return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);

    }


    @Operation(summary = "Retrieve a list of hotels with prices less than the specified value.")
    @GetMapping(path = "less_price")
    public ResponseEntity<Set<HotelResponse>> getLessPrice(
            @RequestParam BigDecimal price){
        var response = this.hotelService.readLessPrice(price);
        return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);

    }

    @Operation(summary = "Retrieve a list of hotels with prices within the specified range.")
    @GetMapping(path = "between_price")
    public ResponseEntity<Set<HotelResponse>> getBetweenPrice(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max){
        var response = this.hotelService.readBetweenPrice(min, max);
        return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);

    }

    @Operation(summary = "Retrieve a list of hotels with the specified rating (1 to 4).")
    @GetMapping(path = "rating")
    public ResponseEntity<Set<HotelResponse>> getByRating(
            @RequestParam Integer rating){
        if(rating>4) rating=4;
        if (rating<1) rating=1;
        var response = this.hotelService.readByRating(rating);
        return response.isEmpty()?ResponseEntity.noContent().build():ResponseEntity.ok(response);

    }
}
