package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StdoutLogger implements LoggerInterface, PragmaticLoggerInterface {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // pozor na velikost pismen

    @Override
    public void log(LogLevelEnum level, String message, int minLogLevel) {
        System.out.println("Volano z: " +  Thread.currentThread().getStackTrace()[1].getClassName() + ": " + Thread.currentThread().getStackTrace()[1].getLineNumber());
        System.out.println("Volano z: " +  Thread.currentThread().getStackTrace()[2].getClassName() + ": " + Thread.currentThread().getStackTrace()[2].getLineNumber());


        if (minLogLevel == 1) { printLog(level, message); }
        else if (minLogLevel == 2 && level != LogLevelEnum.DEBUG) { printLog(level, message); }
        else if (minLogLevel == 3 && level != LogLevelEnum.DEBUG && level != LogLevelEnum.INFO) { printLog(level, message); }
        else if (minLogLevel == 4 && level != LogLevelEnum.DEBUG && level != LogLevelEnum.INFO && level != LogLevelEnum.WARNING) { printLog(level, message); }
        else { System.out.println("Nezalogovano."); }
    }

    public boolean printLog(LogLevelEnum level, String message){
        // format: [INFO] [2016-10-05 16:34:00] message
        System.out.printf("[" + level.name() + "] [" + LocalDateTime.now().format(formatter) + "] " + message + "%n");
        return true;
    }
    
}