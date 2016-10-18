package cz.codecamp.logger.loggers;

import java.text.SimpleDateFormat;




/**
 * Created by jbares on 18.10.2016.
 */
public interface LoggerTestInterface {
    public final String STRING_FORMATER_DAY = "YYYY-MM-DD";
    public final SimpleDateFormat format = new SimpleDateFormat(STRING_FORMATER_DAY);

    public final String STRING_FORMATER_TIME = "YYYY-MM-DD HH:mm:ss";
    public final SimpleDateFormat formatTime = new SimpleDateFormat(STRING_FORMATER_TIME);

}
