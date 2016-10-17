package cz.codecamp.logger.formatters;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lenka.salacova on 10/16/2016.
 */
public class NoDateFormatterTest {

    NoDateFormatter noDateFormatter;
    String testMessage = "TestMessage";
    LogLevelEnum levelEnum = LogLevelEnum.DEBUG;

    @Before
    public void setUp() throws Exception {
    noDateFormatter = new NoDateFormatter();
    }

    @Test
    public void format() throws Exception {
    String output = noDateFormatter.format(levelEnum, testMessage);
        assertEquals("[" + levelEnum + "] " + testMessage, output);
    }



}