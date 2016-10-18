package cz.codecamp.logger;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class MessageTest {
    private Message message;

    @Before
    public void initMessage() {
        message = new Message(LogLevelEnum.INFO, "test Message", this.getClass().getName());
    }

    @After
    public void uninitMessage() {
        message = null;
    }

    @Test
    public void getLevel() {
        assertEquals(LogLevelEnum.INFO, message.getLevel());
    }

    @Test
    public void getMessage() {
        assertEquals("test Message", message.getMessage());
    }

    @Test
    public void getTimestamp() {
        assertTrue(message.getTimestamp() instanceof Date);
    }
}
