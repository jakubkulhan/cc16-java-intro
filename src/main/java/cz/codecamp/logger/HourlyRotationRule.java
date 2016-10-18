package cz.codecamp.logger;

import cz.codecamp.logger.utils.DateUtils;

import java.util.Date;

/**
 * Created by honzapua on 16.10.2016.
 */
public class HourlyRotationRule implements RotationRule {

    @Override
    public boolean shouldRotate(Date lastWritten) {
        Date now = DateUtils.currentDateHour();
        return now.getTime() > lastWritten.getTime();
    }

}
