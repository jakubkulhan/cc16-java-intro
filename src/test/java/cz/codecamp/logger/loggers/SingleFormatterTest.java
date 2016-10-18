package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.SingleFormatter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by honzapua on 18.10.2016.
 */
public class SingleFormatterTest {

    @Test
    public void isFormatingProperly() throws Exception {
        FormatterInterface formatter = new SingleFormatter();
        String actual = formatter.format(LogLevelEnum.DEBUG, "XXX");
        String expected = "[DEBUG] XXX";
        Assert.assertEquals(expected, actual);
    }

}
