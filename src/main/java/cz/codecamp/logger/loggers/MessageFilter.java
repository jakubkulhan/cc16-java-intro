package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import sun.rmi.runtime.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * This class filters messages that are pending to be logged.
 * Unit-tested by MessageFilterTest
 * Created by Lenovo on 11.10.2016.
 */
public class MessageFilter {
    private LogLevelEnum level;
    private int limit;

    public MessageFilter(LogLevelEnum level) {
        this.level = level;
        limit = level.ordinal();
    }

    /** SEND - head function of message filter
     * @param level
     * @return @true if received level is more or same important than adjusted limit, @false if received level is less important
     * than adjusted limit level.
     */
    public boolean send(LogLevelEnum level) {
        if(level.ordinal() >= limit) {
            return true;
        }
        return false;
    }
}
