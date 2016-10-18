package cz.codecamp.logger;

import cz.codecamp.logger.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by honzapua on 16.10.2016.
 */
public class DailyRotationRule implements RotationRule {

    @Override
    public boolean shouldRotate(Date lastWritten) {
        Date now = DateUtils.currentDateDay();
        Calendar lastWrittenCalendar = new GregorianCalendar();
        lastWrittenCalendar.setTime(lastWritten);
        lastWrittenCalendar.clear(Calendar.HOUR_OF_DAY);
        return now.getTime() > lastWrittenCalendar.getTime().getTime();
    }

}
