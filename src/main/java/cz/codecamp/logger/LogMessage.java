package cz.codecamp.logger;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogMessage
{
    @SerializedName("lvl")
    private LogLevelEnum level;
    @SerializedName("ts")
    private long timestamp;
    private String msg;
    private transient String msgDate;
    private String className = "";
    private String methodName = "";
    private int lineNumber = 0;
    private transient boolean JSON = false;

    public LogMessage(LogLevelEnum level, String message, Date date, boolean JSON) {
        this.level = level;
        this.msg = message;
        this.JSON = JSON;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.msgDate = df.format(date);
        this.timestamp = date.getTime() / 1000L;

        for (int i = 1; i < Thread.currentThread().getStackTrace().length; i++) {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[i];
            this.className = ste.getClassName();
            if ((!this.className.startsWith("cz.codecamp.logger.loggers")) &&
                    (!this.className.startsWith("cz.codecamp.logger.LogMessage")))
            {
                this.methodName = ste.getMethodName();
                this.lineNumber = ste.getLineNumber();
                break;
            }
        }
    }

    @Override
    public String toString() {
        if (JSON) {
            Gson gson = new Gson();
            return (gson.toJson(this));
        }
        else {
            return "[" + this.level.name() + "] " + "[" + this.msgDate + "] " + this.msg + " [from: " + this.className + ":"
                    + this.methodName + ":" + this.lineNumber + "]";
        }
    }
}
