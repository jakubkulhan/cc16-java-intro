package cz.codecamp.logger.fileHandlers;

import cz.codecamp.logger.fileHandlers.FileHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Created by Filip Ocelka on 16.10.2016, filip.ocelka@gmail.com
 */
public class DayBasedFileHandler extends FileHandler {

    public DayBasedFileHandler(String fileName) {
        super(fileName, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    protected LocalDateTime getDateTimeFromFileName(String fileName) {
        int indexOfFirstDot = fileName.indexOf('.');
        int indexOfLastDot = fileName.lastIndexOf('.');
        String dateString = fileName.substring(indexOfFirstDot+1, indexOfLastDot);
        LocalDate date = LocalDate.parse(dateString, formatter);
        return LocalDateTime.of(date, LocalTime.of(0,0,0));
    }

    @Override
    public boolean hasLogFileLifeExpired() {
        return lastLogFileDateTime.until(LocalDateTime.now(), ChronoUnit.DAYS) >= 1;
    }
}
