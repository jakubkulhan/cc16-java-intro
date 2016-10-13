package cz.codecamp.logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface FormatterInterface {

    DateFormat df = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");

    String format(LogLevelEnum level, String message);

}
