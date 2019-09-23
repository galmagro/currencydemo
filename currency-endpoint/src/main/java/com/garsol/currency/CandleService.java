package com.garsol.currency;

import reactor.core.publisher.Flux;

public interface CandleService {

    Flux<Candle> getCandles();

}
