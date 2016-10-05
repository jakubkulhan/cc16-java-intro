package cz.codecamp.logger.formatters;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import com.google.code.gson.*;

import java.time.LocalDateTime;

/**
 * Created by micha on 05.10.2016.
 */
public class JsonFormatter implements FormatterInterface {
    @Override
    public String format(LogLevelEnum level, String message) {
        Gson gson = new Gson();
        Row row = new Row(level.name(), "" + System.currentTimeMillis(), message);
        return gson.toJson(row);
    }

    private class Row {
        String lvl;
        String ts;
        String msg;

        public Row(String lvl, String ts, String msg) {
            this.lvl = lvl;
            this.ts = ts;
            this.msg = msg;
        }
    }
}
