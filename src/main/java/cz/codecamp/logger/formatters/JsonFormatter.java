package cz.codecamp.logger.formatters;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import cz.codecamp.logger.LogMessage;
import cz.codecamp.logger.fragments.LogMessageFragment;
import cz.codecamp.logger.loggers.PrintableLogMessageConverter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class JsonFormatter implements PrintableLogMessageConverter {

    private final JsonFactory jsonFactory = new JsonFactory();

    @Override
    public String makePrintable(LogMessage message) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(byteArrayOutputStream, JsonEncoding.UTF8);
            jsonGenerator.writeStartObject();
            for (LogMessageFragment fragment : message.getFragments()) {
                jsonGenerator.writeStringField(fragment.getType(), fragment.getValue());
            }
            jsonGenerator.writeStringField("severity", message.getLevel().name());
            jsonGenerator.writeStringField("message", message.getMessage());
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString() + '\n';
    }


}
