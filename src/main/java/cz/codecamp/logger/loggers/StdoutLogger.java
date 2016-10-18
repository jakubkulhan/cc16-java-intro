package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.LoggerInterface;
import cz.codecamp.logger.PragmaticLoggerInterface;

import java.util.Date;

public class StdoutLogger implements LoggerInterface, PragmaticLoggerInterface {

    public StdoutLogger(){
        initMapOfLevels();
    }

    @Override
    public void log(LogLevelEnum level, String message, int minimumLevel) {
        System.out.println("Called from: " +  Thread.currentThread().getStackTrace()[1].getClassName() + ": " + Thread.currentThread().getStackTrace()[1].getLineNumber());

        if (mapOfLevels.get(level) >= minimumLevel){
            createLog(level, message);
        }
    }

    public boolean createLog(LogLevelEnum level, String message){
        System.out.printf("[" + level.name() + "] [" + formatTime.format(new Date()) + "] " + message + "\n");
        return true;
    }


    public void initMapOfLevels(){
        mapOfLevels.put(LogLevelEnum.DEBUG,1);
        mapOfLevels.put(LogLevelEnum.INFO,2);
        mapOfLevels.put(LogLevelEnum.WARNING,3);
        mapOfLevels.put(LogLevelEnum.ERROR,4);
    }
}
