package cz.codecamp.logger.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

/**
 * Created by honzapua on 18.10.2016.
 */
abstract class CurrentHourTestSupport {

    abstract protected Calendar getTestMethodResult();

    @Test
    public void minutesAreZero() throws Exception {
        Calendar actual = getTestMethodResult();
        Assert.assertEquals(0, actual.get(Calendar.MINUTE));
    }

    @Test
    public void secondsAreZero() throws Exception {
        Calendar actual = getTestMethodResult();
        Assert.assertEquals(0, actual.get(Calendar.SECOND));
    }

    @Test
    public void milisecondsAreZero() throws Exception {
        Calendar actual = getTestMethodResult();
        Assert.assertEquals(0, actual.get(Calendar.MILLISECOND));
    }

}
