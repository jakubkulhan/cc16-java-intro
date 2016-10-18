package cz.codecamp.logger.loggers;
import com.google.gson.*;
import cz.codecamp.logger.LogLevelEnum;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by Lenovo on 11.10.2016.
 */
public class JSONLogger {
    private Gson gson = new Gson();
    /**
     * Message class used for converting message to objects, that gonna be formatted to JSON String
     */
    private class Message {
        LogLevelEnum lvl;
        long ts;
        String msg;
        public Message(LogLevelEnum lvl, long ts, String msg) {
            this.lvl = lvl;
            this.ts = ts;
            this.msg = msg;
        }
    }

    /**
     *
     * @param lvl logging level
     * @param date current system time
     * @param msg log message
     * @return JSON String of object from Message
     * source : http://stackoverflow.com/questions/23944370/how-to-get-milliseconds-from-localdatetime-in-java-8
     */
    String format(LogLevelEnum lvl, LocalDateTime date, String msg) {
        ZonedDateTime zdt = date.atZone(ZoneId.of("Europe/Paris"));
        long ts = zdt.toInstant().toEpochMilli();
        Message message = new Message(lvl, ts, msg);
        String str = gson.toJson(message);
        return str;
    }
}
