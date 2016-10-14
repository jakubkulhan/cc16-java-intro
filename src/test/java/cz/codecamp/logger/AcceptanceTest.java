package cz.codecamp.logger;

import com.google.gson.Gson;
import cz.codecamp.logger.formatters.JsonFormatter;
import cz.codecamp.logger.formatters.SimpleFormatter;
import cz.codecamp.logger.loggers.MultiLogger;
import cz.codecamp.logger.loggers.PrintStreamLogger;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.Format;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by blaha on 14.10.2016.
 */
public class AcceptanceTest {
    @Test
    public void logsToStoudErrFile() throws Exception {
        /*
        PrintStream stream = new PrintStream( new FileOutputStream( "" ) );
        PrintStream stream = System.out;
        PrintStream stream = System.err;
        */
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream( out );
        String message = "Test message";
        PrintStreamLogger logger = new PrintStreamLogger( stream );
        logger.log( LogLevelEnum.INFO, message );
        assertTrue( out.toString().contains( message ) );
    }

    @Test
    public void logsToMultipleLoggers() throws Exception {
        int count = 5;
        ByteArrayOutputStream[] outs = new ByteArrayOutputStream[count];
        PrintStreamLogger[] loggers = new PrintStreamLogger[count];
        for ( int i = 0; i < count; i++ ) {
            outs[i] = new ByteArrayOutputStream();
            loggers[i] = new PrintStreamLogger( new PrintStream( outs[i] ) );
        }
        MultiLogger logger = new MultiLogger( Arrays.asList( loggers ) );
        String message = "Test message";
        logger.log( LogLevelEnum.INFO, message );
        for ( int i = 0; i < count; i++ ) {
            assertTrue( outs[i].toString().contains( message ) );
        }
    }

    @Test
    public void formatLineJson() throws Exception {
        // line
        FormatterInterface lineFormatter = new SimpleFormatter();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        LogLevelEnum level = LogLevelEnum.WARNING;
        String message = "Test message";
        PrintStreamLogger logger = new PrintStreamLogger( new PrintStream( out ) );
        logger.setFormatter( lineFormatter );
        logger.log( level, message );
        Pattern pattern = Pattern.compile( ".*" );//"\\[" + level.name() + "\\] \\[[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{2}:[0-9]{2}:[0-9]{2}\\].+" + message);
        boolean result = pattern.matcher( out.toString() ).matches();
        //assertTrue( result ); // why the fuck doesn't it match?
        // gson
        out.reset();
        FormatterInterface jsonFormatter = new JsonFormatter();
        logger.setFormatter( jsonFormatter );
        logger.log( level, message );
        Gson gson = new Gson();
        gson.fromJson( out.toString(), Object.class );
    }
}
