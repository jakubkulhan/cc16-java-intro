package cz.codecamp.logger.formatters;

import com.google.gson.Gson;
import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;

import java.util.Date;

public class LogJSONFormatter extends Formatter implements FormatterInterface {

    private class LogObject {

        private LogLevelEnum lvl;
        private long ts;
        private String msg;
        private String className;
        private int lineNumber;

        public LogObject(LogLevelEnum lvl, String msg) {
            this.lvl = lvl;
            this.ts = new Date().getTime();
            this.msg = msg;

            StackTraceElement s = findCallerClass();
            className = s.getClassName();
            lineNumber = s.getLineNumber();
        }
    }

    @Override
    public String format(LogLevelEnum level, String message) {
        LogObject logObject = new LogObject(level, message);
        Gson gson = new Gson();
        return gson.toJson(logObject);
    }
}
