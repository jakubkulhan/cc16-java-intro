package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Created by Lenovo on 18.10.2016.
 */
public class StringFormatterTest {
    StringFormatter sf;
    /* IDK how to test this :(
    @Test
    public void formatTestWDate() throws Exception {
        sf = new StringFormatter(true);
        LogLevelEnum level = LogLevelEnum.DEBUG;
        LocalDateTime time = LocalDateTime.now();
        String message = "test message";
        assertEquals("[DEBUG] [somedate] "+message,sf.format(level, message));

    }*/
    @Test
    public void formatTestWoutDate() throws Exception {
        sf = new StringFormatter(false);
        LogLevelEnum level = LogLevelEnum.DEBUG;
        String message = "test message";
        assertEquals("[DEBUG]: "+message,sf.format(level, message));
    }

}