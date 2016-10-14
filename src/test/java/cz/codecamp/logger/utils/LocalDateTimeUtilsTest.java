package cz.codecamp.logger.utils;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.Assert.*;

/**
 * Created by blaha on 14.10.2016.
 */
public class LocalDateTimeUtilsTest {
    @Test
    public void testConstructorIsPrivate() throws Exception {
        // give me full coverage
        Constructor<LocalDateTimeUtils> constructor = LocalDateTimeUtils.class.getDeclaredConstructor();
        assertTrue( Modifier.isPrivate( constructor.getModifiers() ) );
        constructor.setAccessible( true );
        constructor.newInstance();
    }

    @Test
    public void fromMillis() throws Exception {
        long millis = 1000000;
        LocalDateTime expected = LocalDateTime.ofInstant( Instant.ofEpochMilli( millis ), ZoneId.systemDefault() );
        LocalDateTime actual = LocalDateTimeUtils.fromMillis( millis );
        assertEquals( expected, actual );
    }
}