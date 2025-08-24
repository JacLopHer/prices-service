package com.inditex.prices_service.api;

import com.inditex.prices_service.application.PriceService;
import com.inditex.prices_service.domain.Price;
import com.inditex.prices_service.shared.PriceDto;
import com.inditex.prices_service.shared.PriceMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/prices")
public class PriceController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<?> getPrice(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam("productId") int productId,
            @RequestParam("brandId") int brandId) {
        try {
            Price price = priceService.getPrice(productId, brandId, date);
            PriceDto priceDto = PriceMapper.toDto(price);
            return ResponseEntity.ok(priceDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
