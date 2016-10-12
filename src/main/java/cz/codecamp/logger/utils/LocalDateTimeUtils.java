package cz.codecamp.logger.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by micha on 12.10.2016.
 */
public class LocalDateTimeUtils {
    public static LocalDateTime fromMillis( long millis ) {
        return LocalDateTime.ofInstant( Instant.ofEpochMilli( millis ), ZoneId.systemDefault() );
    }
}
