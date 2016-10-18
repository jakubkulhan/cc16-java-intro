package cz.codecamp.logger;

import java.util.Date;

public class Message {
    private String message;
    private Date timestamp;
    private String loggingContext;
    private String creatorClassName;
    private LogLevelEnum level;

    public Message(LogLevelEnum level, String message, String creatorClassName ) {
        this.level = level;
        this.message = message;
        this.creatorClassName = creatorClassName;
        this.timestamp = new Date();
    }

    private void initialize() {



    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public LogLevelEnum getLevel() {
        return level;
    }

    public String getLoggingContext() {
        return loggingContext;
    }
}
