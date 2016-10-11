package cz.codecamp.logger.formatters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cz.codecamp.logger.LogLevelEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by blaha on 11.10.2016.
 */
@RunWith( Parameterized.class )
public class JsonFormatterTest {
    private static final String TEXT_MESSAGE = "Test message";
    private static final String TEXT_CLASS = "ClassTest";
    private static final int LINE_NUMBER = 11;

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
    JsonFormatter formatter;

    public JsonFormatterTest( LogLevelEnum level, String text, String cls, int line ) {
        this.level = level;
        this.text = text;
        this.cls = cls;
        this.line = line;
    }

    @Before
    public void setUp() throws Exception {
        formatter = new JsonFormatter();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void format() throws Exception {
        String actual = formatter.format( level, text );
//        assertThat( actual,
//                both( containsString( "\"lvl\":\"" + level.name() + "\"" ) ).and( containsString( "\"msg\":\"" + TEXT_MESSAGE + "\"" ) ) );
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse( actual );
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        assertEquals( text, jsonObject.get( "msg" ).getAsString() );
        assertEquals( level.name(), jsonObject.get( "lvl" ).getAsString() );
        assertTrue( jsonObject.get( "ts" ).getAsLong() <= System.currentTimeMillis() );
    }

    @Test
    public void format1() throws Exception {
        String actual = formatter.format( level, text, cls, line );
        JsonObject jsonObject = new JsonParser().parse( actual ).getAsJsonObject();
        assertEquals( text, jsonObject.get( "msg" ).getAsString() );
        assertEquals( level.name(), jsonObject.get( "lvl" ).getAsString() );
        assertTrue( jsonObject.get( "ts" ).getAsLong() <= System.currentTimeMillis() );
        assertEquals( cls, jsonObject.get( "cls" ).getAsString() );
        assertEquals( line, jsonObject.get( "line" ).getAsInt() );
    }

}