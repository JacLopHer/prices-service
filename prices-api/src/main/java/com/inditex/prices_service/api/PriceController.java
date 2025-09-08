package com.inditex.prices_service.api;

import com.inditex.prices_service.application.PriceService;
import com.inditex.prices_service.shared.PriceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for price queries by product, brand, and date.
 */
@RestController
@SuppressWarnings("unused")
public class PriceController {
    private final PriceService priceService;

    /**
     * Constructs a PriceController with the given PriceService.
     * @param priceService the service to retrieve price information
     */
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/prices")
    @Operation(
        summary = "Get product price by date, product and brand",
        description = "Returns the price for a given product and brand at a specific date. If no price is found, returns 404."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Price found",
        content = @Content(schema = @Schema(implementation = PriceDto.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "Price not found"
    )
    public ResponseEntity<PriceDto> getPriceByDateProductBrand(
            @Parameter(description = "Date and time for price lookup", example = "2020-06-14T10:00:00+02:00", required = true)
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime date,
            @Parameter(description = "Product identifier", example = "35455", required = true)
            @RequestParam("productId") int productId,
            @Parameter(description = "Brand identifier", example = "1", required = true)
            @RequestParam("brandId") int brandId) {
        PriceDto priceDto = priceService.getPrice(productId, brandId, date);
        return ResponseEntity.ok(priceDto);
    }

    @ExceptionHandler(com.inditex.prices_service.application.PriceNotFoundException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
    public java.util.Map<String, String> handlePriceNotFoundException(Exception ex) {
        java.util.Map<String, String> error = new java.util.HashMap<>();
        error.put("error", ex.getMessage());
        return error;
    }
}
