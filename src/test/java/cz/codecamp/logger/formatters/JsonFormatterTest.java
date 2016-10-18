package cz.codecamp.logger.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.codecamp.logger.LogLevelEnum;
import cz.codecamp.logger.Message;
import cz.codecamp.logger.formaters.JsonFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JsonFormatterTest {
    private JsonFormatter formatter;
    private Message message;

    @Before
    public void setUp() {
        message = mock(Message.class);
        when(message.getLevel()).thenReturn(LogLevelEnum.INFO);
        when(message.getMessage()).thenReturn("Test message");
        // 1476810000000L = 18.10.2016 19:00:00
        when(message.getTimestamp()).thenReturn(new Date(1476810000000L));

        formatter = new JsonFormatter(new ObjectMapper());
    }

    @After
    public void cleanUp() {
        formatter = null;
        message = null;
    }

    @Test
    public void format() {
        assertEquals("{\"lvl\":\"INFO\",\"ts\":\"2016-00-18 07:00:00\",\"msg\":\"Test message\"}", formatter.format(message));
    }

    @Test(expected = RuntimeException.class)
    public void formatFails() throws JsonProcessingException {
        ObjectMapper mapper = mock(ObjectMapper.class);
        JsonProcessingException exception = mock(JsonProcessingException.class);
        when(mapper.writeValueAsString(any())).thenThrow(exception);
        JsonFormatter form = new JsonFormatter(mapper);
        form.format(message);
    }
}
