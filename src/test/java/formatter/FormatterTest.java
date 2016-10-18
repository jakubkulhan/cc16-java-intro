package formatter;

import cz.codecamp.logger.Formatter;
import cz.codecamp.logger.LogLevelEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FormatterTest {

    private Formatter formatter;

    @Before
    public void setup(){
        formatter = new Formatter();
    }

    @Test
    public void testLogLevel(){
        String message = formatter.format(LogLevelEnum.DEBUG, "TestMessage");
        Assert.assertTrue(message.contains("[" + LogLevelEnum.DEBUG.name() + "]"));
    }

    @Test
    public void testMessage(){
        String message = formatter.format(LogLevelEnum.DEBUG, "TestMessage");
        Assert.assertTrue(message.contains("TestMessage"));
    }

    @Test
    public void testStackTracePath(){
        String message = formatter.format(LogLevelEnum.DEBUG, "Test Message");
        Assert.assertTrue(message.contains("32:formatter.FormatterTest.testStackTracePath"));
    }

}
