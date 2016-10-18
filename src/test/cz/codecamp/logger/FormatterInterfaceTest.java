package cz.codecamp.logger;

import cz.codecamp.logger.loggers.StdoutLogger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by bmandzhi o
 */
public class FormatterInterfaceTest {

    String message;

    FormatterInterface formatterInterface;

    @Before
    public void setUp(){
        formatterInterface = new StdoutLogger();
        message = "log";
    }

    @Test
    public void format() throws Exception {
        String string = formatterInterface.format(LogLevelEnum.WARNING, "a", message, "a", 1);
        assertTrue(string.contains(message));
    }
}