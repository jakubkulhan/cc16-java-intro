package cz.codecamp.logger;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by honzapua on 16.10.2016.
 */
public class JsonFormatterTest {

    @Test
    public void testConversionToJson() throws Exception {
        JsonFormatter jf = new JsonFormatter();

        String actual = jf.format(LogLevelEnum.DEBUG, "Message");

        Assert.assertNotNull(actual);
    }

}
