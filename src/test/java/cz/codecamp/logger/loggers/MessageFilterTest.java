package cz.codecamp.logger.loggers;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lenovo on 13.10.2016.
 */
public class MessageFilterTest {
    private MessageFilter msgFltr;
    @Test
    public void sendDebug() throws Exception {
        msgFltr = new MessageFilter(LogLevelEnum.DEBUG);
        assertTrue(msgFltr.send(LogLevelEnum.DEBUG));
        assertTrue(msgFltr.send(LogLevelEnum.INFO));
        assertTrue(msgFltr.send(LogLevelEnum.WARNING));
        assertTrue(msgFltr.send(LogLevelEnum.ERROR));
    }
    @Test
    public void sendInfo() throws Exception {
        msgFltr = new MessageFilter(LogLevelEnum.INFO);
        assertFalse(msgFltr.send(LogLevelEnum.DEBUG));
        assertTrue(msgFltr.send(LogLevelEnum.INFO));
        assertTrue(msgFltr.send(LogLevelEnum.WARNING));
        assertTrue(msgFltr.send(LogLevelEnum.ERROR));
    }
    @Test
    public void sendWarning() throws Exception {
        msgFltr = new MessageFilter(LogLevelEnum.WARNING);
        assertFalse(msgFltr.send(LogLevelEnum.DEBUG));
        assertFalse(msgFltr.send(LogLevelEnum.INFO));
        assertTrue(msgFltr.send(LogLevelEnum.WARNING));
        assertTrue(msgFltr.send(LogLevelEnum.ERROR));
    }
    @Test
    public void sendError() throws Exception {
        msgFltr = new MessageFilter(LogLevelEnum.ERROR);
        assertFalse(msgFltr.send(LogLevelEnum.DEBUG));
        assertFalse(msgFltr.send(LogLevelEnum.INFO));
        assertFalse(msgFltr.send(LogLevelEnum.WARNING));
        assertTrue(msgFltr.send(LogLevelEnum.ERROR));
    }

}