package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;
import sun.reflect.generics.reflectiveObjects.LazyReflectiveObjectGenerator;

import java.util.List;

/**
 * Created by Lenovo on 08.10.2016.
 */
public class FunctionalMultiLogger implements LoggerInterface, PragmaticLoggerInterface {
    private List<LoggerInterface> list;

    public FunctionalMultiLogger(List<LoggerInterface> list) {
        this.list = list;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        list.forEach((loggerInterface) -> loggerInterface.log(level, message));
    }

    @Override
    public void debug(String message) {
        list.forEach((loggerInterface) -> loggerInterface.log(LogLevelEnum.DEBUG, message));
    }

    @Override
    public void info(String message) {
        list.forEach((loggerInterface) -> loggerInterface.log(LogLevelEnum.INFO, message));
    }

    @Override
    public void warning(String message) {
        list.forEach((loggerInterface) -> loggerInterface.log(LogLevelEnum.WARNING, message));
    }

    @Override
    public void error(String message) {
        list.forEach((loggerInterface) -> loggerInterface.log(LogLevelEnum.ERROR, message));
    }
}
