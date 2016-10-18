package cz.codecamp.logger.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by honzapua on 16.10.2016.
 */
public final class DateUtils {

    /**
     * Vraci aktualni hodinu podle JVM default.
     *
     * @return vraci aktualni hodinu
     */
    public static Calendar currentHour() {
        Calendar result = new GregorianCalendar();
        result.clear(Calendar.MINUTE);
        result.clear(Calendar.SECOND);
        result.clear(Calendar.MILLISECOND);
        return result;
    }

    public static Calendar currentDay() {
        Calendar result = currentHour();
        result.clear(Calendar.HOUR_OF_DAY);
        return result;
    }

    public static Date currentDateHour() {
        return currentHour().getTime();
    }

    public static Date currentDateDay() {
        return currentDay().getTime();
    }

    /**
     * Utility class.
     */
    private DateUtils() {
        // not instantiable
    }

}
