package cz.codecamp.logger.formatters;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by Filip Ocelka on 16.10.2016, filip.ocelka@gmail.com
 */
/*
@PrepareForTest(LogJSONFormatterTest.class)
@RunWith(PowerMockRunner.class)
public class LogJSONFormatterTest {

    @Before
    public void setUp() throws Exception {
        Date now = new Date();
        now.setTime(1476640850000L);
        //whenNew(Date.class).withNoArguments().thenReturn(now);

        //long time = now.getTime();
        PowerMock.expectNew(Date.class).andReturn(now);
    }

    @Test
    public void name() throws Exception {

        LogJSONFormatter formatter = new LogJSONFormatter();
        String actual = formatter.format(LogLevelEnum.INFO, "test");
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        String expected = "{\"lvl\":\"INFO\",\"ts\":1476640850000,\"msg\":\"test\",\"className\":\"" + ste[1].getClassName() + "\",\"lineNumber\":" + (ste[1].getLineNumber() - 1) + "}";
        assertEquals(expected, actual);
    }

}
*/