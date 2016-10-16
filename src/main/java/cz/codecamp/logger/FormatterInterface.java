package cz.codecamp.logger;

import java.text.SimpleDateFormat;

public interface FormatterInterface {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String format(LogLevelEnum level, String message);
}
