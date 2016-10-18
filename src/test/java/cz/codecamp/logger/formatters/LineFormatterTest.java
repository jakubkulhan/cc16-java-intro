package cz.codecamp.logger.formatters;

import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.Message;
import cz.codecamp.logger.formaters.LineFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

public class LineFormatterTest {
    private LineFormatter formatter;
    private Message message;

    @Before
    public void initFormatter() {
        message = mock(Message.class);
        when(message.getLevel()).thenReturn(LogLevelEnum.INFO);
        when(message.getMessage()).thenReturn("Test message");
        // 1476810000000L = 18.10.2016 19:00:00
        when(message.getTimestamp()).thenReturn(new Date(1476810000000L));

        formatter = new LineFormatter();
    }

    @After
    public void uninitFormatter() {
        formatter = null;
        message = null;
    }

    @Test
    public void format() {
        assertEquals("[INFO] [2016-10-18 19:00:00] Test message", formatter.format(message));
    }
}
