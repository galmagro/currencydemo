package com.garsol.currency.provider;

import static java.lang.Double.parseDouble;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.garsol.currency.Candle;
import com.garsol.currency.CandleService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class StaticDataCandleProvider implements CandleService {

    private Duration durationBetweenElements = Duration.ofSeconds(1);

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Flux<Candle> getCandles() {
        Reader inReader = new InputStreamReader(getSampleDataInputStream());
        try {
            Iterable<CSVRecord> candleRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(inReader);
            return Flux.fromIterable(candleRecords)
                    .map(this::recordToCandle)
                    .delayElements(durationBetweenElements)
                    .map(this::assignDateTime);
        } catch (Exception e) {
            return Flux.error(e);
        }
    }

    private Candle assignDateTime(final Candle candle) {
        candle.setDate(DATE_TIME_FORMATTER.format(LocalDateTime.now()));
        return candle;
    }

    public void setDurationBetweenElements(final Duration durationBetweenElements) {
        this.durationBetweenElements = durationBetweenElements;
    }

    private InputStream getSampleDataInputStream() {
        return getClass().getClassLoader().getResourceAsStream("sample-data/gbp-usd.csv");
    }

    private Candle recordToCandle(CSVRecord csvRecord) {
        return new Candle(
                csvRecord.get("date"),
                parseDouble(csvRecord.get("open")),
                parseDouble(csvRecord.get("high")),
                parseDouble(csvRecord.get("close")),
                parseDouble(csvRecord.get("close"))
        );
    }
}
