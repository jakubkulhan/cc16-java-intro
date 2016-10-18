package cz.codecamp.logger;

/**
 * Created by Bulgn Mandzhieva
 */
public class ObjectToLog {
    private LogLevelEnum level;
    private String dateTime;
    private String message;

    public ObjectToLog(LogLevelEnum level, String dateTime, String message) {
        this.level = level;
        this.dateTime = dateTime;
        this.message = message;
    }

    public LogLevelEnum getLevel() {
        return level;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setLevel(LogLevelEnum level) {
        this.level = level;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
