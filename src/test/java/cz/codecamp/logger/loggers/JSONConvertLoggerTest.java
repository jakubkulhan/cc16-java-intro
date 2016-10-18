package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dasklar on 18. 10. 2016.
 */
public class JSONConvertLoggerTest {
    String message;
    long currentTimeInMillis;
    String jsonText;

    @Before
    public void setUp() throws Exception {
        currentTimeInMillis = System.currentTimeMillis();
        message = "debug test";
        jsonText = new JSONConvertLogger().formatToJson(LogLevelEnum.DEBUG, currentTimeInMillis, message);
    }

    @Test
    public void formatToJson() throws Exception {
        assertEquals(jsonText, "{\"lvl\":\"DEBUG\",\"ts\":" + currentTimeInMillis +",\"msg\":\"" + message + "\"}");
    }

}