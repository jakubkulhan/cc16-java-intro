package cz.codecamp.logger.formatters;

import cz.codecamp.logger.LogLevelEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.LocalDateTime;
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
    private static final int LINE_NUMBER = 11;
    private static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.stream( LogLevelEnum.values() )
                .map( logLevelEnum -> new Object[]{ logLevelEnum, TEXT_MESSAGE, TEXT_CLASS, LINE_NUMBER } )
                .collect( Collectors.toList() );
    }

    final LogLevelEnum level;
    final String text;
    final String cls;
    final int line;
    SimpleFormatter formatter;

    public SimpleFormatterTest( LogLevelEnum level, String text, String cls, int line ) {
        this.level = level;
        this.text = text;
        this.cls = cls;
        this.line = line;
    }

    @Before
    public void setUp() throws Exception {
        formatter = new SimpleFormatter();
    }

    @Test
    public void format() throws Exception {
        String actual = formatter.format( level, text );
        // #1 jUnit4
//        assertThat( actual, both( containsString( "[" + level.name() + "]" ) ).and( containsString( text ) ) );
        // #2 regexp match
        Pattern pattern = Pattern.compile( String.format( "\\[%s\\] \\[[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{1,2}:[0-9]{2}:[0-9]{2}\\] %s", level.name(), text ) );
        Matcher matcher = pattern.matcher( actual );
        assertTrue( matcher.matches() );
        // #3 equals - time dependent - what if during the test the time changes enough to make a difference?
        assertEquals( actual, String.format( "[%s] [%s] %s", level.name(), LocalDateTime.now().format( DateTimeFormatter.ofPattern( FORMAT_DATE_TIME ) ), text ) );
    }

    @Test
    public void format1() throws Exception {
        String actual = formatter.format( level, text, cls, line );
        assertEquals( actual, String.format( "[%s] [%s] [%s:%d] %s", level.name(), LocalDateTime.now().format( DateTimeFormatter.ofPattern( FORMAT_DATE_TIME ) ), cls, line, text ) );
    }

}