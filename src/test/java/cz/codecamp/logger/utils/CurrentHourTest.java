package cz.codecamp.logger.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by honzapua on 18.10.2016.
 */
public class CurrentHourTest extends CurrentHourTestSupport {

    @Override
    protected Calendar getTestMethodResult(){
        Date time = DateUtils.currentHour().getTime();
        Calendar result = new GregorianCalendar();
        result.setTime(time);
        return result;
    }


}
