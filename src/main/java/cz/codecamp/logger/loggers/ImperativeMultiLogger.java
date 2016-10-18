package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

import java.io.File;
import java.io.PrintStream;
import java.util.List;
import java.util.logging.Logger;

public class ImperativeMultiLogger implements LoggerInterface {

    List<LoggerInterface> listOfLoggers;

    public ImperativeMultiLogger(List<LoggerInterface> listOfLoggers) {
        this.listOfLoggers = listOfLoggers;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        for (int i = 0; i < listOfLoggers.size(); i++){
            listOfLoggers.get(i).log(level,message);
        }
    }
}

