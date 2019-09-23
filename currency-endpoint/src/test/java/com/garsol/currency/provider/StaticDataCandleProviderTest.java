package com.garsol.currency.provider;

import java.time.Duration;

import org.junit.Test;
import reactor.test.StepVerifier;

public class StaticDataCandleProviderTest {

    private StaticDataCandleProvider provider = new StaticDataCandleProvider();

    @Test
    public void testSubscribeToData(){
        provider.setDurationBetweenElements(Duration.ofMillis(10));
        StepVerifier.create(provider.getCandles()).
                consumeNextWith(System.out::println).
                expectNextCount(80).
                verifyComplete();
    }

}
