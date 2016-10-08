package cz.codecamp.logger.formatters;

import cz.codecamp.logger.FormatterInterface;

import java.text.SimpleDateFormat;

public abstract class AbstractFormatter implements FormatterInterface {

    final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
}
