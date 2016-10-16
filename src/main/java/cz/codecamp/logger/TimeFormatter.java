package cz.codecamp.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by honzapua on 16.10.2016.
 */
public class TimeFormatter implements FormatterInterface {

    @Override
    public String format(LogLevelEnum level, String message) {
        SimpleDateFormat sdf = new SimpleDateFormat("Y-M-d H:m:s"); // nastudovat JavaDoc
        String currentTime = sdf.format(new Date());

        String result = String.format("[%s] [%s] %s", level.name(), currentTime, message);

        return result;
    }
}
