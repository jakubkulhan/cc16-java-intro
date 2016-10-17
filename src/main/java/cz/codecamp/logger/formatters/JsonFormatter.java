package cz.codecamp.logger.formatters;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;

import java.time.format.DateTimeFormatter;

/**
 * Created by lenka.salacova on 10/15/2016.
 */
 /*public class JsonFormatter implements FormatterInterface {

   private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public String format(LogLevelEnum level, String message) {

        JSONObject object = new JSONObject();
        object.put("lvl", level);
        object.put("ts", System.currentTimeMillis());
        object.put("msg", message);

        return object.toString();
       return "";
    }
}*/