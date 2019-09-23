package com.garsol.currency.rest;

import java.util.UUID;

import com.garsol.currency.Candle;
import com.garsol.currency.CandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class CandleEndpoint {

    private CandleService candleService;

    @GetMapping("/candles")
    public Flux<ServerSentEvent<Candle>> getCandles(){
        return candleService.getCandles().map(candle -> ServerSentEvent.<Candle>builder()
                .id(UUID.randomUUID().toString())
                .event("candle")
                .data(candle)
                .build());
    }

    @Autowired
    public void setCandleService(final CandleService candleService) {
        this.candleService = candleService;
    }
}
