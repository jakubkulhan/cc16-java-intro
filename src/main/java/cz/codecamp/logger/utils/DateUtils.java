package cz.codecamp.logger.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by honzapua on 16.10.2016.
 */
public final class DateUtils {


    public static Calendar currentHour() {
        Calendar result = new GregorianCalendar();
        result.clear(Calendar.MINUTE);
        result.clear(Calendar.SECOND);
        result.clear(Calendar.MILLISECOND);
        return result;
    }

    public static Date currentDateHour() {
        return currentHour().getTime();
    }

    /**
     * Utility class.
     */
    private DateUtils() {
        // not instantiable
    }

}
