package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.Assert.*;

/**
 * Created by Lenovo on 15.10.2016.
 */
public class JSONLoggerTest {
    private JSONLogger jsnlgr;
    @Before
    public void setUp() throws Exception {
        jsnlgr = new JSONLogger();
    }

    @Test
    public void formatTest() throws Exception {
        LocalDateTime date = LocalDateTime.now();
        ZonedDateTime zdt = date.atZone(ZoneId.of("Europe/Paris"));
        long testTs = zdt.toInstant().toEpochMilli();
        System.out.println(testTs);
        String testMsg = "Saint Louis";
        String testJSON = jsnlgr.format(LogLevelEnum.DEBUG, date, testMsg);
        System.out.println(testJSON);
        assertEquals(testJSON, "{\"lvl\":\""+LogLevelEnum.DEBUG+"\",\"ts\":"+testTs+",\"msg\":\""+testMsg+"\"}");
    }
}