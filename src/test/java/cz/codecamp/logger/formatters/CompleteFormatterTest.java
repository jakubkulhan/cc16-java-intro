package cz.codecamp.logger.formatters;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

/**
 * Created by lenka.salacova on 10/16/2016.
 */
public class CompleteFormatterTest {

    CompleteFormatter completeFormatter;
    String testMessage = "TestMessage";
    LogLevelEnum levelEnum = LogLevelEnum.DEBUG;
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    @Before
    public void setUp() throws Exception {
        completeFormatter = new CompleteFormatter();
    }

    @Test
    public void format() throws Exception {
        String output = completeFormatter.format(levelEnum, testMessage);
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];

        assertEquals("[" + levelEnum + "] [" + LocalDateTime.now().format(dateFormat) + "] [" + stackTraceElement.getClassName() + "] [Line: " + stackTraceElement.getLineNumber() + "] " + testMessage, output);
    }


}