package cz.codecamp.logger.loggers;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.formatters.JsonFormatter;
import cz.codecamp.logger.formatters.SimpleFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by blaha on 11.10.2016.
 */
@RunWith( Parameterized.class )
public class FileLoggerTest {
    private static final String TEXT_MESSAGE = "Test message";
    private static final FormatterInterface[] formatters = new FormatterInterface[]{ new SimpleFormatter(), new JsonFormatter() };

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.stream( LogLevelEnum.values() )
                .flatMap( logLevelEnum -> Arrays.stream( formatters )
                        .map( formatter -> new Object[]{ logLevelEnum, TEXT_MESSAGE, formatter } ) )
                .collect( Collectors.toList() );
    }

    private static final String FORMAT_DATE = "yyyy-MM-dd";

    final LogLevelEnum level;
    final String message;
    final FormatterInterface formatter;
    String formatted;
    FileLogger logger;
    File logFile;

    public FileLoggerTest( LogLevelEnum level, String message, FormatterInterface formatter ) {
        this.level = level;
        this.message = message;
        this.formatter = formatter;
    }

    @Before
    public void setUp() throws Exception {
        logger = new FileLogger();
        logFile = new File( "application_" + LocalDateTime.now().format( DateTimeFormatter.ofPattern( FORMAT_DATE ) ) + ".log" );
        try ( Writer writer = new FileWriter( logFile ) ) {
            writer.write( "" );
        }
        formatted = formatter.format( level, message );
    }

    @Test
    public void logFormatted() throws Exception {
        logger.logFormatted( LogLevelEnum.INFO, message, formatted );
        logger.close();
        Scanner sc = new Scanner( new FileInputStream( logFile ) );
        String line = sc.nextLine();
        assertEquals( formatted, line );
    }

    @Test
    public void close() throws Exception {
        logger.logFormatted( LogLevelEnum.INFO, message, formatted );
        Scanner sc = new Scanner( new FileInputStream( logFile ) );
        // the log is empty before closing
        assertFalse( sc.hasNext() );
        logger.close();
        sc = new Scanner( new FileInputStream( logFile ) );
        // the log is not empty after closing
        assertTrue( sc.hasNext() );
    }

    @After
    public void tearDown() throws Exception {
        logFile.delete();
    }
}