package cz.codecamp.logger;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by honzapua on 18.10.2016.
 */
public class TimeFormatterTest {

    @Test
    public void isTimeFormated() throws Exception {
        FormatterInterface formatter = new TimeFormatter();
        String actual = formatter.format(LogLevelEnum.DEBUG, "XXX");
        Assert.assertTrue(actual.matches("^\\[DEBUG\\] [-:0-9\\[\\]]*.*XXX$"));
    }

}
