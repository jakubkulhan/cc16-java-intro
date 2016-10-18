package cz.codecamp.logger;

/**
 * Created by Bulgn Mandzhieva
 */
public class ObjectToLog {
    private LogLevelEnum level;
    private String dateTime;
    private String message;
    private String className;
    private int lineNumber;

    public ObjectToLog(LogLevelEnum level, String dateTime, String message, String className, int lineNumber) {
        this.level = level;
        this.dateTime = dateTime;
        this.message = message;
        this.className = className;
        this.lineNumber = lineNumber;
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

    public String getClassName() {
        return className;
    }

    public int getLineNumber() {
        return lineNumber;
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

    public void setClassName(String className) {
        this.className = className;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
