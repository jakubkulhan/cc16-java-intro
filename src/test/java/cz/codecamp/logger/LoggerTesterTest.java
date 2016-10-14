package cz.codecamp.logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by blaha on 14.10.2016.
 */
public class LoggerTesterTest {
    ByteArrayOutputStream outContent;
    ByteArrayOutputStream errContent;
    ByteArrayInputStream inContent;
    Consumer<String[]> main;
    String message;

    @Before
    public void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        System.setOut( new PrintStream( outContent ) );
        errContent = new ByteArrayOutputStream();
        System.setErr( new PrintStream( errContent ) );
        message = "Test message";
        main = LoggerTester::main;
    }
    @Test
    public void testConstructorIsPrivate() throws Exception {
        // give me full coverage
        Constructor<LoggerTester> constructor = LoggerTester.class.getDeclaredConstructor();
        assertTrue( Modifier.isPrivate( constructor.getModifiers() ) );
        constructor.setAccessible( true );
        constructor.newInstance();
    }

    @Test
    public void main() throws Exception {
        String input = "d " + message + System.lineSeparator();
        inContent = new ByteArrayInputStream( input.getBytes() );
        System.setIn( inContent );
        main.accept( new String[]{} );
        Pattern pattern = Pattern.compile( "> \\[DEBUG\\] \\[[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{2}:[0-9]{2}:[0-9]{2}\\] \\[.+:[0-9]+\\] " + message + System.lineSeparator() + "> " );
        Matcher matcher = pattern.matcher( outContent.toString() );
        assertTrue( matcher.matches() );
        System.setIn( null );
    }

    @Test
    public void mainNotEnoughArguments() throws Exception {
        String input = "d"; // "work" for "d ", which is wrong.
        inContent = new ByteArrayInputStream( input.getBytes() );
        System.setIn( inContent );
        main.accept( new String[]{} );
        assertEquals( "not enough arguments" + System.lineSeparator(), errContent.toString() );
        System.setIn( null );
    }

    @Test
    public void mainUnknownLevel() throws Exception {
        String input = message;
        inContent = new ByteArrayInputStream( input.getBytes() );
        System.setIn( inContent );
        main.accept( new String[]{} );
        assertEquals( "unknown level [" + message.split( " " )[0] + "]" + System.lineSeparator(), errContent.toString() );
        System.setIn( null );
    }

    @After
    public void tearDown() throws Exception {
        System.setOut( null );
        System.setErr( null );
    }
}