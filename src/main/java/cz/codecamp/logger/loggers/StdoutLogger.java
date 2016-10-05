package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.util.Date;
import java.text.SimpleDateFormat;

public class StdoutLogger implements LoggerInterface, PragmaticLoggerInterface {

    @Override
    public void log(LogLevelEnum level, String message) {
        String ts = new SimpleDateFormat("yyyy-mm-dd hh:mm").format(new Date());
        System.out.printf("[%s] [%s]: %s\n", level.name(), ts, message);
    }
}
