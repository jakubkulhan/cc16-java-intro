package cz.codecamp.logger;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by honzapua on 16.10.2016.
 */
public class CalleeFormatterTest {

    @Test
    public void isSelfInLogMessage() throws Exception {
        CalleeFormatter cf = new CalleeFormatter();

        String actual = cf.format(LogLevelEnum.DEBUG, "Message");
        Assert.assertTrue(actual.contains(CalleeFormatterTest.class.getName()));

    }

}
