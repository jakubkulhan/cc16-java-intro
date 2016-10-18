package cz.codecamp.logger;

import com.google.gson.Gson;

public interface FormatterInterface {
    default String format(LogLevelEnum level, String dateTime, String message) {
        ObjectToLog logMessage = new ObjectToLog(level, dateTime, message);
        Gson gson = new Gson();
        return gson.toJson(logMessage);
    }
}
