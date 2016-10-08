package cz.codecamp.logger.formatters;

import com.google.gson.Gson;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.formatters.json.LogEntity;

/**
 * Created by vkorecky on 7.10.16.
 */
public class JsonFormatter extends AbstractFormatter {

    private final Gson gson = new Gson();

    public JsonFormatter() {
    }

    @Override
    public String format(LogLevelEnum level, String message, String callersClassName, String callersMethodName, int callersLineNumber) {
        LogEntity log = new LogEntity(level, timeFormat.format(System.currentTimeMillis()), message, callersClassName, callersMethodName, callersLineNumber);
        return gson.toJson(log);
    }
}
