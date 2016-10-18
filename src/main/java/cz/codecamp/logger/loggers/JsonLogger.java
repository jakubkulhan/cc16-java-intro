package cz.codecamp.logger.loggers;

import com.google.gson.JsonObject;
import com.sun.jmx.snmp.Timestamp;
import com.sun.tools.internal.ws.processor.model.ModelObject;
import cz.codecamp.logger.LogLevelEnum;
import com.google.gson.Gson;
import sun.awt.AWTAccessor;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;

import static javafx.scene.input.KeyCode.F;

/**
 * Created by filip on 18/10/16.
 */
public class JsonLogger <T extends AbstractLogger> extends AbstractLogger {
    private final T abslog;

    public JsonLogger(T abslog) {
        this.abslog = abslog;
    }

    @Override
    public void log(LogLevelEnum level, String message) {
        JsonObject jobj = new JsonObject();
        jobj.addProperty("lvl", level.toString());
        jobj.addProperty("ts", String.valueOf(System.currentTimeMillis() / 1000));
        jobj.addProperty("msg", message);
        abslog.write(jobj.toString());
    }

    @Override
    public void write(String msg) {
        abslog.write(msg);
    }

}
