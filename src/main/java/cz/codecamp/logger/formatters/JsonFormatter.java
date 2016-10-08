package cz.codecamp.logger.formatters;

import cz.codecamp.logger.LogLevelEnum;
import com.google.gson.Gson;
import cz.codecamp.logger.entity.LogEntity;

/**
 * Created by vkorecky on 7.10.16.
 */
public class JsonFormatter extends AbstractFormatter {
    private final Gson gson = new Gson();

    public JsonFormatter() {
    }

    @Override
    public String format(LogLevelEnum level, String message) {
        LogEntity log = new LogEntity(level, timeFormat.format(System.currentTimeMillis()), message);
        return gson.toJson(log);
    }
}
