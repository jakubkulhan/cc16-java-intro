package cz.codecamp.logger;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public interface LoggerInterface {

    public final String STRING_FORMATER_DAY = "YYYY-MM-DD";
    public final SimpleDateFormat format = new SimpleDateFormat(STRING_FORMATER_DAY);

    public final String STRING_FORMATER_TIME = "YYYY-MM-DD HH:mm:ss";
    public final SimpleDateFormat formatTime = new SimpleDateFormat(STRING_FORMATER_TIME);

    public Map<LogLevelEnum,Integer> mapOfLevels = new HashMap<>();

    public void log(LogLevelEnum level, String message, int minimumLevel);

}

