package org.example.api;

import lombok.RequiredArgsConstructor;
import org.example.domain.PriceService;

//@RestResource
//@Path("\instrument-price")
@RequiredArgsConstructor
public class PriceRestResource {

    private final PriceService priceService;

    //@GET
    //@Path("\{instrumentName}")
//    public PriceResponse getPrice(@PathVariable String instrumentName) {
//        priceService.getPrice....
//    }
}
