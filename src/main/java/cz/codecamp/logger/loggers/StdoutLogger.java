package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;

public class StdoutLogger extends AbstractLogger {

    private FormatterInterface formatter;
    public  StdoutLogger (FormatterInterface formatter){
        this.formatter = formatter;

    }

    @Override
    public void log(LogLevelEnum level, String message) {
//        System.out.printf("[%s]: %s\n", level.name(), message);
        System.out.println(formatter.format(level, message));
    }

}
