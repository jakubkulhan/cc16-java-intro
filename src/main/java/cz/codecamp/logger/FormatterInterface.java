package cz.codecamp.logger;

import com.google.gson.Gson;

public interface FormatterInterface {
    //JSON
    default String format(LogLevelEnum level, String dateTime, String message, String className, int lineNumber) {
        ObjectToLog logMessage = new ObjectToLog(level, dateTime, message, className, lineNumber);
        Gson gson = new Gson();
        return gson.toJson(logMessage);
    }
}
