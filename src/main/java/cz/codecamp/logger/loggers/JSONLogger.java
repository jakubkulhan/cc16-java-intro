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
     * Message class used for JSON formatting
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
     * @param lvl
     * @param ts
     * @param msg
     * @return JSON string for Message object
     */
    String format(LogLevelEnum lvl, LocalDateTime date, String msg) {
        ZonedDateTime zdt = date.atZone(ZoneId.of("Europe/Paris"));
        long ts = zdt.toInstant().toEpochMilli();
        Message message = new Message(lvl, ts, msg);
        String str = gson.toJson(message);
        return str;
    }
}
