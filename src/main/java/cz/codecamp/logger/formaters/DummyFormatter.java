package cz.codecamp.logger.formaters;

import cz.codecamp.logger.FormatterInterface;
import cz.codecamp.logger.Message;

public class DummyFormatter implements FormatterInterface {

    public String format(Message message) {
        return message.getMessage();
    }
}
