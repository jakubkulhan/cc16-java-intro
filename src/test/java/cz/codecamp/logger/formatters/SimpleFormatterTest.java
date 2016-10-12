package cz.codecamp.logger.formatters;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by blaha on 11.10.2016.
 */
@RunWith( Parameterized.class )
public class SimpleFormatterTest {
    private static final String TEXT_MESSAGE = "Test message";
    private static final String TEXT_CLASS = "ClassTest";
    private static final long TIME_IN_MILLIS = 1000000L;
    private static final int LINE_NUMBER = 11;
    private static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.stream( LogLevelEnum.values() )
                .map( logLevelEnum -> new Object[]{ logLevelEnum, TEXT_MESSAGE, TIME_IN_MILLIS, TEXT_CLASS, LINE_NUMBER } )
                .collect( Collectors.toList() );
    }

    final LogLevelEnum level;
    final String text;
    final long timeInMillis;
    final String cls;
    final int line;
    SimpleFormatter formatter;

    public SimpleFormatterTest( LogLevelEnum level, String text, long timeInMillis, String cls, int line ) {
        this.level = level;
        this.text = text;
        this.timeInMillis = timeInMillis;
        this.cls = cls;
        this.line = line;
    }

    @Before
    public void setUp() throws Exception {
        formatter = new SimpleFormatter();
    }

    @Test
    public void format() throws Exception {
        String actual = formatter.format( level, text, timeInMillis, cls, line );
        String time = LocalDateTime.ofInstant( Instant.ofEpochMilli( timeInMillis ), ZoneId.systemDefault() ).format( DateTimeFormatter.ofPattern( FORMAT_DATE_TIME ) );
        assertEquals( actual, String.format( "[%s] [%s] [%s:%d] %s", level.name(), time, cls, line, text ) );
    }

}