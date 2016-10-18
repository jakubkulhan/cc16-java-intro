package cz.codecamp.logger.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by honzapua on 18.10.2016.
 */
public class CurrentDateHourTest extends CurrentHourTestSupport {

    @Override
    protected Calendar getTestMethodResult() {
        Date currentDateHour = DateUtils.currentDateHour();
        Calendar result = new GregorianCalendar();
        result.setTime(currentDateHour);
        return result;
    }
}
