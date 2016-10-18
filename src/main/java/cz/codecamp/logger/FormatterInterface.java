package cz.codecamp.logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public interface FormatterInterface {

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    String format(LogLevelEnum level, String message);

}

