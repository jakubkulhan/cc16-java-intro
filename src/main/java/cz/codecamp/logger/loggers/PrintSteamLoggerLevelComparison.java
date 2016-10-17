package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;

import java.io.PrintStream;

/**
 * Created by lenka.salacova on 10/15/2016.
 */
public class PrintSteamLoggerLevelComparison extends PrintStreamLogger {

    private LogLevelEnum levelEnum;

    public PrintSteamLoggerLevelComparison(PrintStream printStream, FormatterInterface formatter, LogLevelEnum levelEnum) {
        super(printStream, formatter);
        this.levelEnum = levelEnum;
    }


    @Override
    public void log(LogLevelEnum level, String message) {
        if (level.ordinal() >= levelEnum.ordinal()) {
            super.log(level, message);
        }


    }
}
