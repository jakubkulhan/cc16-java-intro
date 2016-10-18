package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;

import java.io.PrintStream;
import cz.codecamp.logger.LogLevelEnum;

/**
 * Created by filip on 15/10/16.
 */
public class BunnyLogger <T extends AbstractLogger> extends AbstractLogger{
    private LogLevelEnum lv = LogLevelEnum.DEBUG;
    private final T abslog;

    public BunnyLogger(T abslog, LogLevelEnum reportingLevel) {
        lv = reportingLevel;
        this.abslog = abslog;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        if ( lv == LogLevelEnum.INFO && level != LogLevelEnum.DEBUG) {
             abslog.log(level, message);
        }

        if (lv == LogLevelEnum.DEBUG) {
            abslog.log(level, message);
        }

        if (lv == LogLevelEnum.WARNING && (level != LogLevelEnum.DEBUG && level != LogLevelEnum.INFO)) {
            abslog.log(level, message);
        }

        //if (LogLevelEnum.valueOf(level) > LogLevelEnum.valueOf(lv)) {

        //}
    }

    @Override
    public void write(String msg) {
        abslog.write(msg);
    }
}
