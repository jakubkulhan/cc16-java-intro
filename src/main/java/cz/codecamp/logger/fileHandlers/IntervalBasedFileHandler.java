package cz.codecamp.logger.fileHandlers;

import cz.codecamp.logger.fileHandlers.FileHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;

/**
 * Created by Filip Ocelka on 16.10.2016, filip.ocelka@gmail.com
 */
public class IntervalBasedFileHandler extends FileHandler {

    private TemporalUnit timeUnit;
    private long intervalLength;

    public IntervalBasedFileHandler(String fileName, TemporalUnit timeUnit, long intervalLength) {
        super(fileName, DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss"));
        this.timeUnit = timeUnit;
        this.intervalLength = intervalLength;
    }

    @Override
    public boolean hasLogFileLifeExpired() {
        return lastLogFileDateTime.until(LocalDateTime.now(), timeUnit) >= intervalLength;
    }
}
