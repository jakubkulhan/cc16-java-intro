package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.Before;
import org.junit.Test;

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
    public void format() throws Exception {
        long testTs = 123456;
        String testMsg = "Saint Louis";
        String testJSON = jsnlgr.format(LogLevelEnum.DEBUG, testTs, testMsg);
        assertEquals("",testJSON, "{\"lvl\":\""+LogLevelEnum.DEBUG+"\",\"ts\":"+testTs+",\"msg\":\""+testMsg+"\"}");
    }
}