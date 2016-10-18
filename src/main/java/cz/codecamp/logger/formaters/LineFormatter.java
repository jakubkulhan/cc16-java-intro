package cz.codecamp.logger.formaters;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.Message;

import java.text.SimpleDateFormat;


public class LineFormatter implements FormatterInterface {

    public String format(Message message) {
        String ts = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(message.getTimestamp());
        return String.format("[%s] [%s] %s", message.getLevel().name(), ts, message.getMessage());
    }
}
